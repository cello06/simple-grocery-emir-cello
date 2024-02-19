package stepdefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pojo.request.RegisterClient;


public class RegisterStepDefs extends BaseStep {

    private static final Logger LOGGER = LogManager.getLogger(RegisterStepDefs.class);
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final TestContext TEST_CONTEXT = new TestContext();



    @Given("The user is on the correct baseUri")
    public void theUserIsOnTheCorrectBaseUri() {
        request = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .build();

        LOGGER.info("The user is on the correct baseUri");
        TEST_CONTEXT.setResponse(response);
    }

    @When("The user send POST request with {string} and {string} to the registration endpoint")
    public void theUserSendPOSTRequestWithAndToTheRegistrationEndpoint
            (String clientName, String clientEmail) {
        RegisterClient client = new RegisterClient();
        client.setClientName(clientName);
        client.setClientEmail(clientEmail);
        response = RestAssured.given()
                .spec(request)
                .contentType("application/json")
                .body(client).log().all()
                .when()
                .post(registerEndpoint);

        LOGGER.info("The user send POST request with clientName and clientEmail to the registration endpoint");
        TEST_CONTEXT.setResponse(response);

        accessToken = response.jsonPath().getString("accessToken");
    }


    @Then("The status code should be {int}")
    public void theStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.statusCode();

        Assertions.assertThat(actualStatusCode)
                .as("The status code is not true!")
                .isEqualTo(expectedStatusCode);
        LOGGER.debug("The status code is --> "+ actualStatusCode);
    }

    @And("The token should not be null or empty")
    public void theTokenShouldNotBeNullOrEmpty() {
        Assertions.assertThat(accessToken).isNotEmpty();
        Assertions.assertThat(accessToken).isNotNull();

        LOGGER.debug("Access Token is successfully created!");
    }


    @When("User sends a request with wrong end-points.")
    public void userSendsARequestWithWrongEndPoints() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get("wrong.endpoint");

        LOGGER.info("User sends a request with wrong end-points.");
    }
}
