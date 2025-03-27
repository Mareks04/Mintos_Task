const USERNAME = "mintos_admin";
const PASSWORD = "M%S@X92XZPP290"; 

module.exports = (req, res, next) => {
    const authHeader = req.headers.authorization;

    if (!authHeader || !authHeader.startsWith("Basic ")) {
        return res.status(401).json({
            title: "Unauthorized",
            description: "Missing or invalid authentication credentials",
        });
    }

    const base64Credentials = authHeader.split(" ")[1];
    const decodedCredentials = Buffer.from(base64Credentials, "base64").toString("utf-8");
    const [username, password] = decodedCredentials.split(":");

    if (username !== USERNAME || password !== PASSWORD) {
        return res.status(403).json({
            title: "Forbidden",
            description: "Invalid username or password",
        });
    }

    if (req.method === "POST") {
        const { firstName, lastName, email, dateOfBirth, personalIdDocument } = req.body;

        if (!personalIdDocument || typeof personalIdDocument !== "object") {
            return res.status(400).json({
                title: "Invalid input",
                type: "http://localhost:8080/api/users",
                description: "personalIdDocument is missing or invalid",
                instance: "/users"
            });
        }

        if (!firstName) return sendError(res, "firstName is missing");
        if (!lastName) return sendError(res, "lastName is missing");
        if (!email) return sendError(res, "email is missing");
        if (!dateOfBirth) return sendError(res, "dateOfBirth is missing.");
        if (!personalIdDocument.documentId) return sendError(res, "documentId is missing");
        if (!personalIdDocument.validUntil) return sendError(res, "validUntil date is missing");
        if (!personalIdDocument.countryOfIssue) return sendError(res, "countryOfIssue is missing");

        if (firstName.length < 2 || firstName.length > 50) {
            return sendError(res, "firstName must be between 2 and 50 characters");
        }
        if (lastName.length < 2 || lastName.length > 50) {
            return sendError(res, "lastName must be between 2 and 50 characters");
        }
        if (personalIdDocument.documentId.length < 5 || personalIdDocument.documentId.length > 20) {
            return sendError(res, "documentId must be between 5 and 20 characters");
        }

        const countryRegex = /^[A-Z]{2}$/;
        if (!countryRegex.test(personalIdDocument.countryOfIssue)) {
            return sendError(res, "countryOfIssue must be an ISO 3166-1 alpha-2 code");
        }
    } 
    next();
};

function sendError(res, message) {
    return res.status(400).json({
        title: "Invalid input",
        type: "http://localhost:8080/api/users",
        description: message,
        instance: "/users"
    });
}
