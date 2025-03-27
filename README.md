# Mintos_Task
This repository consists of two projects:

**localhost_server** – A JSON server that acts as a mock API, allowing **CRUD** operations on user data with custom validation.

**api_automation** – An API automation framework using Java, Maven, and Cucumber to test the user management API.

It is recommended to start with **localhost_server** to set up server for API before proceeding with **api_automation** for automated testing.


## JSON server localhost setup (localhost_server)
This project sets up a localhost API server using `json-server` (*version 0.17.4*) and `express` (*version 4.21.2*) dependencies, allowing CRUD operations on user data with custom validation logic. The server runs on localhost:8080/api and provides meaningful response messages for different HTTP methods.

### Prerequisites

1) Before running this project, ensure you have **Node.js** installed:

    [Download Node.js](https://nodejs.org/en/download) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Node.js version 22.14.0 was used during testing*

    Verify installation by running:
   ```sh
   node -v 
   ```

2) Run the following command in the project root to install required dependencies:

    `npm install` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*npm version 11.2.0 was used during testing*

     Verify installation by running:
   ```sh
   npm -v 
   ```

3) Start the JSON Server from root directory:

    `node server.js`

This will start the server on http://localhost:8080/api

Dependency versions used during testing:

**json-server** - `0.17.4`


## API Automation setup (api_automation)

This project sets up an API automation framework using **Java, Maven, and Cucumber**, enabling automated testing of a user management API. It includes test scenarios for creating, retrieving, updating, and deleting user data while ensuring validation through meaningful response messages.

### Dependencies in use

**Java + Maven:** Manages dependencies and builds the project.

**Cucumber:** Provides BDD-style test scenarios.

**Rest-Assured:** Facilitates API testing.

**JUnit:** Handles test execution.

**Jackson Databind:** Manages JSON serialization and deserialization.

**Maven Surefire Plugin:** Runs tests.

**Maven Compiler Plugin:** Manages Java compilation.

### Prerequisites

1) Ensure you have **Java** and **Maven** installed on your system:
   - [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Java version 23.0.2 was used during testing*
   - [Download Maven](https://maven.apache.org/download.cgi) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*Maven version 4.0.0-rc-2 was used during testing*

2) Verify installation by running:
   ```sh
   java -version
   mvn --version
   ```

3) Install required dependencies:
   ```sh
   mvn clean install
   ```

4) Execute all tests using Maven:
   ```sh
   mvn test
   ```


