package stepdefinition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import pojo.response.GetItems;

import java.util.List;


public class GetItemsInCartStepDefs extends BaseStep {
    ObjectMapper mapper = new ObjectMapper();

    @And("The added items are displayed correctly")
    public void theAddedItemsAreDisplayedCorrectly() throws JsonProcessingException {
        List<GetItems> actualItems = mapper.readValue(response.asString(), new TypeReference<>() {
        });

        actualItems.forEach(
                item -> {
                    Assertions.assertThat(item.getId()).isEqualTo(items.get(actualItems.indexOf(item)));

                    Assertions.assertThat(item.getProductId()).isEqualTo(products.get(actualItems.indexOf(item)));
                }
        );


    }

    @And("The user send GET request get cart items endpoint")
    public void theUserSendGETRequestGetCartItemsEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(createCartEndpoint + "/" + cartId + "/items");

    }
}
