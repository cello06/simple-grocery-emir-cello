package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;

import java.util.List;


public class DeleteAnOrderStepDefs extends BaseStep {

    @And("The user sends DELETE request to delete an order endpoint")
    public void theUserSendsDELETERequestToDeleteAnOrderEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .delete(createOrderEndpoint + "/" + orderId);

        orders.remove(orderId);
    }

    @And("Verify that the order is successfully deleted")
    public void verifyThatTheOrderIsSuccessfullyDeleted() {
        response = RestAssured.given()
                .spec(request).header("Authorization", "Bearer " + accessToken)
                .when().get(getAllOrdersEndpoint);

        List<String> orderIds = response.jsonPath().getList("id");

        Assertions.assertThat(orderIds.contains(orderId)).isFalse();
    }
}
