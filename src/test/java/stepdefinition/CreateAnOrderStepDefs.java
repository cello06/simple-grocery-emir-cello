package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import pojo.request.CreateOrder;

public class CreateAnOrderStepDefs extends BaseStep {

    @And("The user sends POST request with {string} to create an order end point")
    public void theUserSendsPOSTRequestWithToCreateAnOrderEndPoint(String customerName) {
        CreateOrder order = new CreateOrder();
        order.setCartId(cartId);
        order.setCustomerName(customerName);

        response = RestAssured.given()
                .spec(request)
                .header("Authorization" , "Bearer " + accessToken)
                .contentType("application/json")
                .body(order).when().post(createOrderEndpoint);

        orderId = response.jsonPath().getString("orderId");

        orders.add(orderId);

        customerNames.add(customerName);

    }

    @And("Created should be true")
    public void createdShouldBeTrue() {
        boolean isCreated = response.jsonPath().getBoolean("created");
        Assertions.assertThat(isCreated).isTrue();
    }

    @And("The order id should not be null or empty")
    public void theOrderIdShouldNotBeNullOrEmpty() {

        Assertions.assertThat(orderId).isNotEmpty();
        Assertions.assertThat(orderId).isNotNull();
    }



}
