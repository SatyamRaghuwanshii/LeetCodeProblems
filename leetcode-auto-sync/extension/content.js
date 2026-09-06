// ============================================================
// Inject page-level network interceptor
// ============================================================

const script = document.createElement("script");

script.src = chrome.runtime.getURL("page.js");

script.onload = () => {
    script.remove();
};

(document.head || document.documentElement).appendChild(script);


// ============================================================
// Receive submission from page.js
// ============================================================

window.addEventListener(
    "leetcodeSubmission",
    async (event) => {

        const data = event.detail;

        if (!data) {
            return;
        }

        console.log(
            "[LeetCode Auto Sync]",
            "Submission detected:",
            data
        );

        // Only accepted submissions
        if (data.status !== "Accepted") {
            console.log(
                "[LeetCode Auto Sync]",
                "Not accepted. Ignoring."
            );

            return;
        }

        try {

            const response = await fetch(
                "http://127.0.0.1:8763/submit",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(data)
                }
            );

            const result = await response.json();

            console.log(
                "[LeetCode Auto Sync]",
                "Server response:",
                result
            );

        } catch (error) {

            console.error(
                "[LeetCode Auto Sync]",
                "Could not connect to local server.",
                error
            );

            alert(
                "LeetCode Auto Sync: " +
                "Could not connect to local GitHub server.\n\n" +
                "Make sure server.py is running."
            );
        }
    }
);