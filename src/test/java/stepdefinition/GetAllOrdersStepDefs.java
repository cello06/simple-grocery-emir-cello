package stepdefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import pojo.response.GetAllOrders;

import java.util.List;


public class GetAllOrdersStepDefs extends BaseStep{
    ObjectMapper mapper = new ObjectMapper();
    @And("The user sends GET request to get all orders end point")
    public void theUserSendsGETRequestToGetAllOrdersEndPoint() {
        response = RestAssured.given()
                .spec(request)
                .header("Authorization","Bearer " + accessToken)
                .when()
                .get(getAllOrdersEndpoint);

    }

    @And("The order ids, item ids and customer names are true")
    public void theOrderIdsItemIdsAndCustomerNamesAreTrue() throws JsonProcessingException {
        List<GetAllOrders> actualOrders = mapper.readValue(response.asString(), new TypeReference<>() {
        });

        actualOrders.forEach(
                getAllOrder -> {
                    Assertions.assertThat(getAllOrder.getCustomerName()).isEqualTo(customerNames.get(actualOrders.indexOf(getAllOrder)));

                    Assertions.assertThat(getAllOrder.getId()).isEqualTo(orders.get(actualOrders.indexOf(getAllOrder)));

                    Assertions.assertThat(items.contains(getAllOrder.getItems()[actualOrders.indexOf(getAllOrder)].getId())).isTrue();
                }
        );
    }
}
