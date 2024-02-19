package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pojo.request.UpdateOrder;


public class UpdateOrderStepDefs extends BaseStep {

    private static final Logger LOGGER = LogManager.getLogger(UpdateOrderStepDefs.class);
    String updatingCustomerName;

    @And("The user sends PATCH request with new {string} customer name to the update order end point")
    public void theUserSendsPATCHRequestWithNewCustomerNameToTheUpdateOrderEndPoint(String newCustomerName) {
        UpdateOrder updateOrder = new UpdateOrder(newCustomerName);

        response = RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + accessToken)
                .contentType("application/json")
                .body(updateOrder)
                .when().log().all()
                .patch(createOrderEndpoint + "/" + orderId);

        LOGGER.info("The user sends PATCH request with new newCustomerName to the update order end point");
        updatingCustomerName = newCustomerName;
    }

    @And("Verify that whether customer name is changed or not")
    public void verifyThatWhetherCustomerNameIsChangedOrNot() {
        response = RestAssured.given()
                .spec(request)
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .get(createOrderEndpoint + "/" + orderId);

        String actualCustomerName = response.jsonPath().getString("customerName");

        Assertions.assertThat(actualCustomerName).isEqualTo(updatingCustomerName);
        LOGGER.debug("Customer name is successfully updated!");
    }
}
