package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

public class CreateCartStepDefs extends BaseStep{
    private static final Logger LOGGER = LogManager.getLogger(CreateCartStepDefs.class);
    @When("The user sends POST request to the create a cart endpoint")
    public void theUserSendsPOSTRequestToTheCreateACartEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .post(createCartEndpoint);

        LOGGER.info("The user sends POST request to the create a cart endpoint");

        cartId =  response.jsonPath().getString("cartId");
    }

    @And("The cart id should not be null or empty")
    public void theCartIdShouldNotBeNullOrEmpty() {

        Assertions.assertThat(cartId).isNotEmpty();
        Assertions.assertThat(cartId).isNotNull();

        LOGGER.debug("Cart id is successfully created!");
    }
}
