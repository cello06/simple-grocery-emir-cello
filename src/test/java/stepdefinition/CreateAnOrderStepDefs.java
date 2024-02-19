package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pojo.request.CreateOrder;

public class CreateAnOrderStepDefs extends BaseStep {

    private static final Logger LOGGER = LogManager.getLogger(CreateAnOrderStepDefs.class);

    @And("The user sends POST request with {string} to create an order end point")
    public void theUserSendsPOSTRequestWithToCreateAnOrderEndPoint(String customerName) {
        CreateOrder order = new CreateOrder();
        order.setCartId(cartId);
        order.setCustomerName(customerName);

        response = RestAssured.given()
                .spec(request)
                .header("Authorization" , "Bearer " + accessToken)
                .contentType("application/json")
                .body(order)
                .when().log().all()
                .post(createOrderEndpoint);

        LOGGER.info("The user sends POST request with customerName to create an order end point");

        orderId = response.jsonPath().getString("orderId");
        orders.add(orderId);
        customerNames.add(customerName);

    }

    @And("Created should be true")
    public void createdShouldBeTrue() {
        boolean isCreated = response.jsonPath().getBoolean("created");
        Assertions.assertThat(isCreated).isTrue();
        LOGGER.debug("Is created part checked for seeing true value!");
    }

    @And("The order id should not be null or empty")
    public void theOrderIdShouldNotBeNullOrEmpty() {

        Assertions.assertThat(orderId).isNotEmpty();
        Assertions.assertThat(orderId).isNotNull();
        LOGGER.debug("Order id is successfully created!");
    }



}
