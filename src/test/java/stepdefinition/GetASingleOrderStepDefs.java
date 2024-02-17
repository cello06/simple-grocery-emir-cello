package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;

import java.util.List;

public class GetASingleOrderStepDefs extends BaseStep{

    @And("The user sends GET request with order id to get a single order end point")
    public void theUserSendsGETRequestWithOrderIdToGetASingleOrderEndPoint() {
        response = RestAssured.given()
                .spec(request)
                .header("Authorization","Bearer " + accessToken)
                .when()
                .get(getAllOrdersEndpoint + "/" + orderId);

    }

    @And("The order id, item id and customer name are true")
    public void theOrderIdItemIdAndCustomerNameAreTrue() {
        String actualOrderId = response.jsonPath().getString("id");

        List<String> actualItemIds = response.jsonPath().getList("items.id");

        String actualCustomerName = response.jsonPath().getString("customerName");

        Assertions.assertThat(actualOrderId).isEqualTo(orderId);
        Assertions.assertThat(actualItemIds.contains(itemId)).isTrue();
        Assertions.assertThat(actualCustomerName).isEqualTo(customerNames.get(0));
    }
}
