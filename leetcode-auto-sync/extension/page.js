(function () {

    console.log(
        "[LeetCode Auto Sync] Network interceptor loaded"
    );


    let lastSubmissionId = null;


    // ============================================================
    // Get current problem slug
    // ============================================================

    function getSlug() {

        const match =
            window.location.pathname.match(
                /\/problems\/([^/]+)/
            );

        return match ? match[1] : null;
    }


    // ============================================================
    // Sleep helper
    // ============================================================

    function sleep(ms) {

        return new Promise(
            resolve => setTimeout(resolve, ms)
        );
    }


    // ============================================================
    // Convert memory bytes → MB
    // ============================================================

    function formatMemory(bytes) {

        if (
            bytes === null ||
            bytes === undefined
        ) {
            return "";
        }

        const mb =
            Number(bytes) / (1024 * 1024);

        return mb.toFixed(2) + " MB";
    }


    // ============================================================
    // Format runtime
    // ============================================================

    function formatRuntime(runtime) {

        if (
            runtime === null ||
            runtime === undefined
        ) {
            return "";
        }

        return runtime + " ms";
    }


    // ============================================================
    // Format percentile
    // ============================================================

    function formatPercentile(percentile) {

        if (
            percentile === null ||
            percentile === undefined
        ) {
            return "";
        }

        return Number(percentile).toFixed(2) + "%";
    }


    // ============================================================
    // Send final submission to Flask server
    // ============================================================

    async function sendToServer(data) {

        console.log(
            "[LeetCode Auto Sync] Sending submission to local server..."
        );

        console.log(
            "[LeetCode Auto Sync] Payload:",
            data
        );


        try {

            const response =
                await fetch(
                    "https://leetcode-auto-sync.onrender.com/submit",
                    {
                        method: "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body: JSON.stringify(data)
                    }
                );


            const text =
                await response.text();


            console.log(
                "[LeetCode Auto Sync] Server response:",
                response.status,
                text
            );


            if (!response.ok) {

                console.error(
                    "[LeetCode Auto Sync] Server returned an error."
                );

                return;
            }


            console.log(
                "[LeetCode Auto Sync] Successfully sent to server."
            );

        } catch (error) {

            console.error(
                "[LeetCode Auto Sync] Could not connect to local server:",
                error
            );

            console.log(
                "[LeetCode Auto Sync] Make sure server.py is running on port 8763."
            );
        }
    }


    // ============================================================
    // Dispatch event for existing extension code
    // ============================================================

    function dispatchSubmission(data) {

        console.log(
            "[LeetCode Auto Sync] Submission detected:",
            data
        );


        window.dispatchEvent(
            new CustomEvent(
                "leetcodeSubmission",
                {
                    detail: data
                }
            )
        );
    }


    // ============================================================
    // Get submission details
    // ============================================================

    async function getSubmissionDetails(
        submissionId,
        submittedCode
    ) {

        console.log(
            "[LeetCode Auto Sync] Checking submission:",
            submissionId
        );


        const query = `
            query submissionDetails(
                $submissionId: Int!
            ) {
                submissionDetails(
                    submissionId: $submissionId
                ) {
                    runtime
                    runtimeDisplay
                    runtimePercentile
                    memory
                    memoryDisplay
                    memoryPercentile
                    code
                    statusDisplay
                }
            }
        `;


        // ========================================================
        // Poll LeetCode
        // ========================================================

        for (
            let attempt = 0;
            attempt < 40;
            attempt++
        ) {

            try {

                const response =
                    await fetch(
                        "https://leetcode.com/graphql/",
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            credentials: "include",

                            body: JSON.stringify({

                                query: query,

                                variables: {
                                    submissionId:
                                        Number(
                                            submissionId
                                        )
                                }
                            })
                        }
                    );


                const result =
                    await response.json();


                console.log(
                    "[LeetCode Auto Sync] Submission details JSON:",
                    JSON.stringify(
                        result,
                        null,
                        2
                    )
                );


                const details =
                    result?.data?.submissionDetails;


                if (!details) {

                    console.log(
                        "[LeetCode Auto Sync] Submission details not available yet."
                    );

                    await sleep(1500);

                    continue;
                }


                const status =
                    details.statusDisplay;


                console.log(
                    "[LeetCode Auto Sync] Status:",
                    status
                );


                // =================================================
                // IMPORTANT:
                // LeetCode may temporarily return Internal Error
                // while the judge is still processing.
                //
                // Runtime and memory being non-null is our
                // reliable indication that the result is ready.
                // =================================================

                const hasResult =
                    details.runtime !== null &&
                    details.memory !== null;


                if (!hasResult) {

                    console.log(
                        "[LeetCode Auto Sync] Result not ready. Retrying..."
                    );

                    await sleep(1500);

                    continue;
                }


                // =================================================
                // Format result
                // =================================================

                const runtime =
                    details.runtimeDisplay ||
                    formatRuntime(details.runtime);

                const runtimePercentile =
                    formatPercentile(
                        details.runtimePercentile
                    );

                const memory =
                    details.memoryDisplay ||
                    formatMemory(details.memory);

                const memoryPercentile =
                    formatPercentile(
                        details.memoryPercentile
                    );


                const slug =
                    getSlug();


                const code =
                    details.code ||
                    submittedCode;


                // =================================================
                // Final payload
                // =================================================

                const data = {

                    slug: slug,

                    questionId:
                        null,

                    language:
                        "java",

                    code: code,

                    status:
                        status,

                    runtime:
                        runtime,

                    runtimePercentile:
                        runtimePercentile,

                    memory:
                        memory,

                    memoryPercentile:
                        memoryPercentile,

                    submissionId:
                        submissionId,

                    timeText:
                        runtime +
                        " (" +
                        runtimePercentile +
                        ")",

                    spaceText:
                        memory +
                        " (" +
                        memoryPercentile +
                        ")"
                };


                console.log(
                    "[LeetCode Auto Sync] ================================="
                );

                console.log(
                    "[LeetCode Auto Sync] FINAL RESULT"
                );

                console.log(
                    "[LeetCode Auto Sync] Status:",
                    data.status
                );

                console.log(
                    "[LeetCode Auto Sync] Runtime:",
                    data.timeText
                );

                console.log(
                    "[LeetCode Auto Sync] Memory:",
                    data.spaceText
                );

                console.log(
                    "[LeetCode Auto Sync] Submission ID:",
                    data.submissionId
                );


                // =================================================
                // Send to existing extension listener
                // =================================================



                // =================================================
                // Send directly to Flask
                // =================================================

                if (
                    status === "Accepted"
                ) {

                    await sendToServer(data);

                } else {

                    console.log(
                        "[LeetCode Auto Sync] Submission was not accepted. Not syncing to GitHub."
                    );
                }


                return;


            } catch (error) {

                console.error(
                    "[LeetCode Auto Sync] Submission details error:",
                    error
                );


                await sleep(1500);
            }
        }


        console.log(
            "[LeetCode Auto Sync] Submission result timeout."
        );
    }


    // ============================================================
    // Process actual submit request
    // ============================================================

    async function processSubmission(
        body,
        responseText
    ) {

        let parsedBody;


        // ========================================================
        // Parse request body
        // ========================================================

        try {

            parsedBody =
                typeof body === "string"
                    ? JSON.parse(body)
                    : body;

        } catch (error) {

            console.error(
                "[LeetCode Auto Sync] Could not parse submit request body:",
                error
            );

            return;
        }


        if (!parsedBody) {
            return;
        }


        // ========================================================
        // Validate submission
        // ========================================================

        if (
            !parsedBody.question_id ||
            !parsedBody.typed_code ||
            !parsedBody.lang
        ) {

            return;
        }


        console.log(
            "[LeetCode Auto Sync] ================================="
        );

        console.log(
            "[LeetCode Auto Sync] SUBMISSION REQUEST FOUND"
        );

        console.log(
            "[LeetCode Auto Sync] Language:",
            parsedBody.lang
        );

        console.log(
            "[LeetCode Auto Sync] Question:",
            parsedBody.question_id
        );


        const submittedCode =
            parsedBody.typed_code;


        // ========================================================
        // Currently Java
        // ========================================================

        if (
            parsedBody.lang !== "java"
        ) {

            console.log(
                "[LeetCode Auto Sync] Not Java. Ignoring."
            );

            return;
        }


        // ========================================================
        // Parse submit response
        // ========================================================

        let result;


        try {

            result =
                typeof responseText === "string"
                    ? JSON.parse(responseText)
                    : responseText;

        } catch (error) {

            console.error(
                "[LeetCode Auto Sync] Could not parse submit response:",
                error
            );

            return;
        }


        console.log(
            "[LeetCode Auto Sync] Submit response:",
            result
        );


        const submissionId =
            result?.submission_id;


        if (!submissionId) {

            console.log(
                "[LeetCode Auto Sync] No submission ID found."
            );

            return;
        }


        // ========================================================
        // Prevent duplicate processing
        // ========================================================

        if (
            String(submissionId) ===
            String(lastSubmissionId)
        ) {

            console.log(
                "[LeetCode Auto Sync] Duplicate submission. Ignoring."
            );

            return;
        }


        lastSubmissionId =
            submissionId;


        console.log(
            "[LeetCode Auto Sync] Submission ID:",
            submissionId
        );


        // ========================================================
        // Wait for final judge result
        // ========================================================

        getSubmissionDetails(
            submissionId,
            submittedCode
        );
    }


    // ============================================================
    // FETCH INTERCEPTOR
    // ============================================================

    const originalFetch =
        window.fetch;


    window.fetch =
        async function (...args) {

            const response =
                await originalFetch.apply(
                    this,
                    args
                );


            try {

                const url =
                    typeof args[0] === "string"
                        ? args[0]
                        : args[0]?.url;


                // =================================================
                // Actual LeetCode submission endpoint
                // =================================================

                if (
                    url &&
                    /\/problems\/[^/]+\/submit\/?$/.test(
                        url
                    )
                ) {

                    console.log(
                        "[LeetCode Auto Sync] Submit endpoint detected"
                    );


                    const body =
                        typeof args[1]?.body === "string"
                            ? args[1].body
                            : "";


                    // Clone response so the original
                    // LeetCode response remains untouched.

                    const clone =
                        response.clone();


                    const responseText =
                        await clone.text();


                    processSubmission(
                        body,
                        responseText
                    );
                }

            } catch (error) {

                console.error(
                    "[LeetCode Auto Sync] Fetch interception error:",
                    error
                );
            }


            return response;
        };


    // ============================================================
    // XHR INTERCEPTOR
    // ============================================================

    const originalOpen =
        XMLHttpRequest.prototype.open;


    const originalSend =
        XMLHttpRequest.prototype.send;


    XMLHttpRequest.prototype.open =
        function (
            method,
            url,
            ...rest
        ) {

            this._leetcodeUrl =
                String(url);


            return originalOpen.call(
                this,
                method,
                url,
                ...rest
            );
        };


    XMLHttpRequest.prototype.send =
        function (body) {

            this._leetcodeBody =
                body;


            const url =
                this._leetcodeUrl || "";


            // =====================================================
            // Actual submission endpoint
            // =====================================================

            if (
                /\/problems\/[^/]+\/submit\/?$/.test(
                    url
                )
            ) {

                console.log(
                    "[LeetCode Auto Sync] XHR submit endpoint detected"
                );


                this.addEventListener(
                    "load",
                    function () {

                        try {

                            // =================================================
                            // IMPORTANT:
                            // Do not read responseText from blob responses.
                            // This avoids the InvalidStateError you previously
                            // encountered.
                            // =================================================

                            if (
                                this.responseType !== "" &&
                                this.responseType !== "text"
                            ) {

                                console.log(
                                    "[LeetCode Auto Sync] Submit response is not text. Skipping XHR response."
                                );

                                return;
                            }


                            processSubmission(
                                this._leetcodeBody,
                                this.responseText
                            );

                        } catch (error) {

                            console.error(
                                "[LeetCode Auto Sync] XHR submit error:",
                                error
                            );
                        }
                    }
                );
            }


            return originalSend.call(
                this,
                body
            );
        };


    console.log(
        "[LeetCode Auto Sync] Submit interception ready"
    );

})();