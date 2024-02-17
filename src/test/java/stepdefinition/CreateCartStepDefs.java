package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;

public class CreateCartStepDefs extends BaseStep{

    @When("The user sends POST request to the create a cart endpoint")
    public void theUserSendsPOSTRequestToTheCreateACartEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .post(createCartEndpoint);

        cartId =  response.jsonPath().getString("cartId");
    }

    @And("The cart id should not be null or empty")
    public void theCartIdShouldNotBeNullOrEmpty() {

        Assertions.assertThat(cartId).isNotEmpty();
        Assertions.assertThat(cartId).isNotNull();
    }
}
