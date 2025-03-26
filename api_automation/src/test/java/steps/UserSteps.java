package steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import models.PersonalIdDocument;
import models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserSteps {
    private Response response;
    private static final String BASE_URL = "http://localhost:8080/api/users";
    private final Map<String, Object> requestBody = new HashMap<>();

    @Given("create new users with details:")
    public void createUser(io.cucumber.datatable.DataTable table) {
        table.asMaps(String.class, String.class).forEach(userData -> {
            PersonalIdDocument personalIdDocument = new PersonalIdDocument(
                    userData.get("documentId"),
                    userData.get("countryOfIssue"),
                    userData.get("validUntil")
            );

            User user = new User(
                    userData.get("firstName"),
                    userData.get("lastName"),
                    userData.get("email"),
                    userData.get("dateOfBirth"),
                    personalIdDocument
            );

            response = given()
                    .contentType("application/json")
                    .body(user)
                    .when()
                    .post(BASE_URL);
        });
    }

    @Given("retrieve user with ID {string}")
    public void getUserById(String id) {
        response = given()
                .contentType("application/json")
                .pathParam("id", id)
                .when()
                .get(BASE_URL + "/{id}");
    }

    @Given("update user {string} field {string} to {string}")
    public void updateUser(String id, String field, String newValue) {
        requestBody.put(field, newValue);
        response = given()
                .contentType("application/json")
                .body(requestBody)
                .pathParam("id", id)
                .when()
                .patch(BASE_URL + "/{id}");
    }

    @Given("delete user with ID {string}")
    public void deleteUser(String id) {
        response = given()
                .pathParam("id", id)
                .when()
                .delete(BASE_URL + "/{id}");
    }

    @Then("^user checks response status for (POST|GET|PATCH|DELETE) request is (\\d+)$")
    public void validateStatusCode(String requestType, int statusCode) {
        response.then().statusCode(statusCode);

        Map<String, Map<Integer, String>> responseMessages = Map.of(
                "POST", Map.of(
                        201, "User created successfully",
                        400, "Invalid input"
                ),
                "GET", Map.of(
                        404, "User not found"
                ),
                "PATCH", Map.of(
                        200, "User updated successfully",
                        400, "Invalid input",
                        404, "User not found"
                ),
                "DELETE", Map.of(
                        404, "User not found"
                )
        );

        if (responseMessages.containsKey(requestType) && responseMessages.get(requestType).containsKey(statusCode)) {
            response.then().body("title", equalTo(responseMessages.get(requestType).get(statusCode)));
        }
    }

    @Then("response {string} should contain {string}")
    public void validateResponseContains(String field,String value) {
        response.then().body(field, equalTo(value));
    }

    @And("check that response contains message {string}")
    public void checkThatResponseContainsMessage(String message) {
        response.then().body("description", equalTo(message));
    }

    @Given("retrieve all users")
    public void retrieveAllUsers() {
        response = given()
                .contentType("application/json")
                .when()
                .get(BASE_URL);
    }

    @And("verify there is {int} users retrieved")
    public void verifyThereIsUsersRetrieved(int expectedUserCount) {
        response.then()
                .statusCode(200)
                .body("size()", equalTo(expectedUserCount));
    }

    @Then("delete all users")
    public void deleteAllUsers() {
        if (response.statusCode() == 200) {
            List<Map<String, Object>> users = response.jsonPath().getList("");

            for (Map<String, Object> user : users) {
                String userId = user.get("id").toString();
                given()
                        .pathParam("id", userId)
                        .when()
                        .delete(BASE_URL + "/{id}")
                        .then()
                        .statusCode(204);
                System.out.println("Deleted user with ID: " + userId);
            }
        } else {
            System.out.println("No users found to delete");
        }
    }
}
