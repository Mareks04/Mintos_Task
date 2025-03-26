const jsonServer = require("json-server");
const express = require("express");
const userValidationMiddleware = require("./middleware");

const server = jsonServer.create();
const router = jsonServer.router("db.json");
const middlewares = jsonServer.defaults();

server.use(express.json());
server.use(middlewares);
server.use(userValidationMiddleware);

const responseMessages = new Map([
    ["POST", new Map([
        [201, "User created successfully"],
        [400, "Invalid input"]
    ])],
    ["GET", new Map([
        [404, "User not found"]
    ])],
    ["PATCH", new Map([
        [200, "User updated successfully"],
        [400, "Invalid input"],
        [404, "User not found"]
    ])],
    ["DELETE", new Map([
        [404, "User not found"]
    ])]
]);


router.render = (req, res) => {
    const method = req.method;
    const statusCode = res.statusCode;
    
    const message = responseMessages.get(method)?.get(statusCode);
    
    if (message) {
        res.json({ title: message });
    } else {
        res.json(res.locals.data);
    }
};

server.use("/api/users/:id", (req, res, next) => {
    if (req.method === "DELETE") {
        const { id } = req.params;
        
        const users = router.db.get("users").value();
        const userIndex = users.findIndex(user => user.id === parseInt(id));
        
        if (userIndex !== -1) {
            router.db.get("users").remove({ id: parseInt(id) }).write();
            res.status(204).end();
        } else {
            res.status(404).json({ title: "User not found" }); // User not found
        }
    } else {
        next();
    }
});

server.use("/api", router);

server.listen(8080, () => {
    console.log("JSON Server is running on http://localhost:8080/api");
});
