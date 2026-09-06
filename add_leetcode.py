import os
import re
import subprocess
import requests


# ============================================================
# CONFIGURATION
# ============================================================

REPO_PATH = os.path.dirname(os.path.abspath(__file__))

LEETCODE_API = "https://leetcode.com/graphql"


# ============================================================
# Helper functions
# ============================================================

def run_command(command):
    """Run a Git command."""

    result = subprocess.run(
        command,
        cwd=REPO_PATH,
        shell=True,
        text=True,
        capture_output=True
    )

    if result.returncode != 0:
        print("\n❌ Git error:")
        print(result.stderr)
        return False

    if result.stdout.strip():
        print(result.stdout)

    return True


def slugify(name):
    """Convert problem name into LeetCode-style slug."""

    name = name.lower()

    name = name.replace("'", "")

    name = re.sub(r"[^a-z0-9]+", "-", name)

    return name.strip("-")


def extract_slug(url):
    """Extract problem slug from a LeetCode URL."""

    url = url.strip().rstrip("/")

    match = re.search(
        r"leetcode\.com/problems/([^/?#]+)",
        url
    )

    if not match:
        return None

    return match.group(1)


def get_solution():
    """Read Java solution from terminal."""

    print("\nPaste your Java solution below.")
    print("Type END on a new line when finished.\n")

    lines = []

    while True:

        line = input()

        if line.strip() == "END":
            break

        lines.append(line)

    return "\n".join(lines)


# ============================================================
# Fetch LeetCode problem
# ============================================================

def fetch_problem(slug):

    query = """
    query questionData($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
            questionId
            questionFrontendId
            title
            titleSlug
            difficulty
            content
            isPaidOnly
            topicTags {
                name
                slug
            }
        }
    }
    """

    variables = {
        "titleSlug": slug
    }

    headers = {
        "Content-Type": "application/json",
        "User-Agent": "Mozilla/5.0"
    }

    try:

        response = requests.post(
            LEETCODE_API,
            json={
                "query": query,
                "variables": variables
            },
            headers=headers,
            timeout=15
        )

        if response.status_code != 200:

            print(
                f"\n❌ LeetCode returned HTTP "
                f"{response.status_code}"
            )

            return None

        data = response.json()

        if "errors" in data:

            print("\n❌ LeetCode API error:")
            print(data["errors"])

            return None

        question = data.get("data", {}).get("question")

        if not question:

            print("\n❌ Problem not found.")

            return None

        return question

    except requests.RequestException as e:

        print("\n❌ Could not connect to LeetCode.")
        print(e)

        return None


# ============================================================
# Main
# ============================================================

print("=" * 65)
print("          LeetCode → GitHub Manual Uploader")
print("=" * 65)


# ------------------------------------------------------------
# Check repository
# ------------------------------------------------------------

if not os.path.exists(
    os.path.join(REPO_PATH, ".git")
):

    print("\n❌ This script must be inside your Git repository.")

    print("\nPut it here:")

    print(
        "LeetCodeProblems/"
        "add_leetcode.py"
    )

    exit()


# ------------------------------------------------------------
# Get LeetCode URL
# ------------------------------------------------------------

url = input(
    "\nLeetCode problem URL: "
).strip()


slug = extract_slug(url)


if not slug:

    print(
        "\n❌ Invalid LeetCode URL."
    )

    print(
        "Example:"
    )

    print(
        "https://leetcode.com/problems/two-sum/"
    )

    exit()


print(
    f"\n🔎 Fetching LeetCode problem: {slug}"
)


# ------------------------------------------------------------
# Fetch problem
# ------------------------------------------------------------

problem = fetch_problem(slug)


if not problem:
    exit()


# ------------------------------------------------------------
# Problem information
# ------------------------------------------------------------

number = int(problem["questionFrontendId"])

title = problem["title"]

difficulty = problem["difficulty"]

content = problem["content"]

topics = problem["topicTags"]


folder_name = f"{number:04d}-{slug}"

folder_path = os.path.join(
    REPO_PATH,
    folder_name
)

java_file = f"{folder_name}.java"

java_path = os.path.join(
    folder_path,
    java_file
)

readme_path = os.path.join(
    folder_path,
    "README.md"
)


# ------------------------------------------------------------
# Display information
# ------------------------------------------------------------

print("\n" + "=" * 65)

print(f"Problem    : {number}. {title}")

print(f"Difficulty : {difficulty}")

print(f"Folder     : {folder_name}")

if topics:

    print(
        "Topics     : "
        + ", ".join(
            topic["name"]
            for topic in topics
        )
    )

print("=" * 65)


# ------------------------------------------------------------
# Paid problem check
# ------------------------------------------------------------

if problem["isPaidOnly"]:

    print(
        "\n⚠️ This is a LeetCode Premium problem."
    )

    print(
        "The problem description may not be publicly available."
    )


# ------------------------------------------------------------
# Check existing folder
# ------------------------------------------------------------

if os.path.exists(folder_path):

    print(
        f"\n⚠️ Folder already exists:"
        f"\n{folder_name}"
    )

    choice = input(
        "\nOverwrite existing files? (y/n): "
    ).lower()

    if choice != "y":

        print("\nCancelled.")

        exit()


# ------------------------------------------------------------
# Get solution
# ------------------------------------------------------------

solution = get_solution()


if not solution.strip():

    print(
        "\n❌ Solution cannot be empty."
    )

    exit()


# ------------------------------------------------------------
# Create folder
# ------------------------------------------------------------

os.makedirs(
    folder_path,
    exist_ok=True
)


# ------------------------------------------------------------
# Create README
#
# IMPORTANT:
# LeetHub stores the original LeetCode HTML.
# We preserve that style instead of converting it
# to Markdown.
# ------------------------------------------------------------

readme_content = (
    f'<h2><a href="https://leetcode.com/problems/'
    f'{slug}">{number}. {title}</a></h2>'
    f'<h3>{difficulty}</h3><hr>'
    f'{content}\n'
)


with open(
    readme_path,
    "w",
    encoding="utf-8"
) as file:

    file.write(readme_content)


# ------------------------------------------------------------
# Create Java file
# ------------------------------------------------------------

with open(
    java_path,
    "w",
    encoding="utf-8"
) as file:

    file.write(solution)


print("\n✅ Files created successfully!")

print(
    f"\n📁 {folder_name}/"
)

print(
    f"   ├── {java_file}"
)

print(
    "   └── README.md"
)


# ============================================================
# Git operations
# ============================================================

print("\n" + "=" * 65)
print("                Updating GitHub")
print("=" * 65)


# ------------------------------------------------------------
# Pull latest changes
# ------------------------------------------------------------

print("\n⬇️ Pulling latest changes...")

if not run_command(
    "git pull --rebase origin main"
):

    print(
        "\n❌ Could not pull latest changes."
    )

    print(
        "Your files were created locally."
    )

    print(
        "Nothing was pushed."
    )

    exit()


# ------------------------------------------------------------
# Git add
# ------------------------------------------------------------

print("\n📦 Adding files...")


if not run_command(
    f'git add "{folder_name}"'
):

    exit()


# ------------------------------------------------------------
# Git commit
# ------------------------------------------------------------

runtime = input("\nRuntime (ms): ").strip()
runtime_percentile = input("Runtime percentile (%): ").strip()

memory = input("Memory (MB): ").strip()
memory_percentile = input("Memory percentile (%): ").strip()

commit_message = (
    f"Time: {runtime} ms ({runtime_percentile}%), "
    f"Space: {memory} MB ({memory_percentile}%) - SatyamRaghuwanshii"
)

print("\n📝 Creating commit...")


if not run_command(
    f'git commit -m "{commit_message}"'
):

    print(
        "\n⚠️ Nothing to commit."
    )

    exit()


# ------------------------------------------------------------
# Git push
# ------------------------------------------------------------

print("\n⬆️ Pushing to GitHub...")


if not run_command(
    "git push origin main"
):

    print(
        "\n❌ Push failed."
    )

    print(
        "\nYour commit exists locally."
    )

    print(
        "Try:"
    )

    print(
        "git push origin main"
    )

    exit()


# ============================================================
# Done
# ============================================================

print("\n" + "=" * 65)

print(
    "🎉 Successfully pushed to GitHub!"
)

print("=" * 65)

print(
    f"\n{number}. {title}"
)

print(
    f"https://leetcode.com/problems/{slug}/"
)

print(
    f"\nGitHub:"
)

print(
    "https://github.com/"
    "SatyamRaghuwanshii/"
    "LeetCodeProblems"
)
