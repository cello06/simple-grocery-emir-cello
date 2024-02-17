package stepdefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.assertj.core.api.Assertions;
import pojo.request.RegisterClient;


public class RegisterStepDefs extends BaseStep {

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    //private final TestContext TEST_CONTEXT = new TestContext();



    @Given("The user is on the correct baseUri")
    public void theUserIsOnTheCorrectBaseUri() {
        request = new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .build();
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
                .body(client)
                .when()
                .post(registerEndpoint);

       // TEST_CONTEXT.setResponse(response);

        accessToken = response.jsonPath().getString("accessToken");
    }


    @Then("The status code should be {int}")
    public void theStatusCodeShouldBe(int expectedStatusCode) {
        int actualStatusCode = response.statusCode();

        Assertions.assertThat(actualStatusCode)
                .as("The status code is not true!")
                .isEqualTo(expectedStatusCode);
    }

    @And("The token should not be null or empty")
    public void theTokenShouldNotBeNullOrEmpty() {
        Assertions.assertThat(accessToken).isNotEmpty();
        Assertions.assertThat(accessToken).isNotNull();
    }


    @When("User sends a request with wrong end-points.")
    public void userSendsARequestWithWrongEndPoints() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get("wrong.endpoint");
    }
}
