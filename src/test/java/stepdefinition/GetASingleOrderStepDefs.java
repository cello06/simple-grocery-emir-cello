package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.List;

public class GetASingleOrderStepDefs extends BaseStep{

    private static final Logger LOGGER = LogManager.getLogger(GetASingleOrderStepDefs.class);

    @And("The user sends GET request with order id to get a single order end point")
    public void theUserSendsGETRequestWithOrderIdToGetASingleOrderEndPoint() {
        response = RestAssured.given()
                .spec(request)
                .header("Authorization","Bearer " + accessToken)
                .when()
                .get(getAllOrdersEndpoint + "/" + orderId);

        LOGGER.info("The user sends GET request with order id to get a single order end point");
    }

    @And("The order id, item id and customer name are true")
    public void theOrderIdItemIdAndCustomerNameAreTrue() {
        String actualOrderId = response.jsonPath().getString("id");

        List<Integer> actualItemIds = response.jsonPath().getList("items.id");

        String actualCustomerName = response.jsonPath().getString("customerName");

        Assertions.assertThat(actualOrderId).isEqualTo(orderId);
        Assertions.assertThat(actualItemIds.contains(itemId)).isTrue();
        Assertions.assertThat(actualCustomerName).isEqualTo(customerNames.get(0));

        LOGGER.debug("The order id, item id and customer name are true");
    }
}
