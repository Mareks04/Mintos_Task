package helpers;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import steps.UserSteps;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Initializing test setup...");
        UserSteps userSteps = new UserSteps();

        userSteps.retrieveAllUsers();
        userSteps.deleteAllUsers();
        System.out.println("Test setup prepared");
    }

    @Before
    public void setup(Scenario scenario) {
        System.out.println("Scenario: '" + scenario.getName() + "' has started");
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("Scenario: '" + scenario.getName() + "' has ended");
    }
}
