from flask import Flask, request, jsonify
from flask_cors import CORS
import os
import re
import subprocess
import html
import requests

app = Flask(__name__)

CORS(app, resources={
    r"/*": {
        "origins": ["https://leetcode.com"]
    }
})

@app.route("/sync", methods=["POST"])
def sync():

    data = request.get_json()

    print("\n============================================")
    print("   LEETCODE SUBMISSION RECEIVED")
    print("============================================")
    print("Slug:", data.get("slug"))
    print("Language:", data.get("language"))
    print("Status:", data.get("status"))
    print("Runtime:", data.get("timeText"))
    print("Memory:", data.get("spaceText"))
    print("Submission ID:", data.get("submissionId"))

    return jsonify({
        "success": True,
        "message": "Submission received"
    })

# ============================================================
# CONFIG
# ============================================================

REPO_PATH = os.path.abspath(
    os.path.join(
        os.path.dirname(__file__),
        "..",
        ".."
    )
)

LEETCODE_GRAPHQL = "https://leetcode.com/graphql"


# ============================================================
# Helpers
# ============================================================

def run_git(command):
    result = subprocess.run(
        command,
        cwd=REPO_PATH,
        shell=True,
        text=True,
        capture_output=True
    )

    print(result.stdout)

    if result.returncode != 0:
        print(result.stderr)
        return False

    return True


def slugify(name):
    name = name.lower()
    name = name.replace("'", "")
    name = re.sub(r"[^a-z0-9]+", "-", name)
    return name.strip("-")


def fetch_question(slug):
    query = """
    query questionData($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
            questionFrontendId
            title
            titleSlug
            difficulty
            content
            isPaidOnly
        }
    }
    """

    response = requests.post(
        LEETCODE_GRAPHQL,
        json={
            "query": query,
            "variables": {
                "titleSlug": slug
            }
        },
        headers={
            "Content-Type": "application/json",
            "User-Agent": "Mozilla/5.0"
        },
        timeout=15
    )

    response.raise_for_status()

    data = response.json()

    return data["data"]["question"]


def create_problem(data):
    number = int(data["questionFrontendId"])
    title = data["title"]
    slug = data["titleSlug"]
    difficulty = data["difficulty"]
    content = data["content"]

    folder = f"{number:04d}-{slug}"

    folder_path = os.path.join(
        REPO_PATH,
        folder
    )

    os.makedirs(
        folder_path,
        exist_ok=True
    )

    java_file = f"{folder}.java"

    java_path = os.path.join(
        folder_path,
        java_file
    )

    readme_path = os.path.join(
        folder_path,
        "README.md"
    )

    return {
        "number": number,
        "title": title,
        "slug": slug,
        "difficulty": difficulty,
        "content": content,
        "folder": folder,
        "folder_path": folder_path,
        "java_path": java_path,
        "readme_path": readme_path
    }


# ============================================================
# Submission endpoint
# ============================================================

@app.route("/submit", methods=["POST"])
def submit():

    data = request.get_json()

    if not data:
        return jsonify({
            "success": False,
            "error": "No JSON data received"
        }), 400

    print("\n========================================")
    print("New LeetCode submission")
    print("========================================")

    print(data)

    # --------------------------------------------------------
    # Only save accepted submissions
    # --------------------------------------------------------

    if data.get("status") != "Accepted":

        print(
            f"Submission status: {data.get('status')}"
        )

        return jsonify({
            "success": True,
            "message": "Submission not accepted. Nothing pushed."
        })

    # --------------------------------------------------------
    # Required fields
    # --------------------------------------------------------

    slug = data.get("slug")
    code = data.get("code")

    runtime = data.get("runtime", "")
    runtime_percentile = data.get(
        "runtimePercentile",
        ""
    )

    memory = data.get("memory", "")
    memory_percentile = data.get(
        "memoryPercentile",
        ""
    )

    if not slug or not code:

        return jsonify({
            "success": False,
            "error": "Missing slug or code"
        }), 400

    # --------------------------------------------------------
    # Get problem information
    # --------------------------------------------------------

    try:

        question = fetch_question(slug)

    except Exception as e:

        print("LeetCode API error:", e)

        return jsonify({
            "success": False,
            "error": str(e)
        }), 500

    problem = create_problem(question)

    # --------------------------------------------------------
    # Don't overwrite existing solution
    # --------------------------------------------------------

    if os.path.exists(problem["java_path"]):

        print(
            "\n⚠️ Solution already exists:"
        )

        print(problem["java_path"])

        return jsonify({
            "success": False,
            "error": "Solution already exists"
        }), 409

    # --------------------------------------------------------
    # Write Java solution
    # --------------------------------------------------------

    with open(
        problem["java_path"],
        "w",
        encoding="utf-8"
    ) as file:

        file.write(code)

    # --------------------------------------------------------
    # Create LeetHub-style README
    # --------------------------------------------------------

    readme = (
        f'<h2><a href="https://leetcode.com/problems/'
        f'{problem["slug"]}">'
        f'{problem["number"]}. '
        f'{html.escape(problem["title"])}'
        f'</a></h2>'
        f'<h3>{problem["difficulty"]}</h3>'
        f'<hr>'
        f'{problem["content"]}\n'
    )

    with open(
        problem["readme_path"],
        "w",
        encoding="utf-8"
    ) as file:

        file.write(readme)

    # --------------------------------------------------------
    # Git
    # --------------------------------------------------------

    print("\n⬇️ Pulling latest GitHub changes...")

    if not run_git(
        "git pull --rebase origin main"
    ):

        return jsonify({
            "success": False,
            "error": "git pull failed"
        }), 500

    # --------------------------------------------------------
    # Add
    # --------------------------------------------------------

    if not run_git(
        f'git add "{problem["folder"]}"'
    ):

        return jsonify({
            "success": False,
            "error": "git add failed"
        }), 500

    # --------------------------------------------------------
    # Commit
    # --------------------------------------------------------

    commit_message = (
        f"Time: {runtime} "
        f"({runtime_percentile}), "
        f"Space: {memory} "
        f"({memory_percentile}) - Satyam's leet extention"
    )

    print(
        "\n📝 Commit:"
    )

    print(commit_message)

    if not run_git(
        f'git commit -m "{commit_message}"'
    ):

        return jsonify({
            "success": False,
            "error": "git commit failed"
        }), 500

    # --------------------------------------------------------
    # Push
    # --------------------------------------------------------

    print("\n⬆️ Pushing to GitHub...")

    if not run_git(
        "git push origin main"
    ):

        return jsonify({
            "success": False,
            "error": "git push failed"
        }), 500

    print("\n🎉 Successfully pushed!")

    return jsonify({
        "success": True,
        "folder": problem["folder"],
        "commit": commit_message
    })


# ============================================================
# Server
# ============================================================

if __name__ == "__main__":

    print(
        "============================================"
    )

    print(
        "   LeetCode → GitHub Local Sync Server"
    )

    print(
        "============================================"
    )

    print(
        f"\nRepository:"
    )

    print(REPO_PATH)

    print(
        "\nListening on:"
    )

    print(
        "http://127.0.0.1:8763"
    )

    app.run(
        host="127.0.0.1",
        port=8763,
        debug=False
    )