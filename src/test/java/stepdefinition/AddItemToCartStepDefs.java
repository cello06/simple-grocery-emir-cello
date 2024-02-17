package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import pojo.request.AddItem;

public class AddItemToCartStepDefs extends BaseStep{

    @And("The user sends POST request with {int} product id to add item to the cart endpoint")
    public void theUserSendsPOSTRequestWithProductIdToAddItemToTheCartEndpoint(int productId) {
        AddItem item = new AddItem();
        item.setProductId(productId);

        products.add(productId);

        response = RestAssured.given()
                .spec(request)
                .contentType("application/json")
                .body(item)
                .when()
                .post(createCartEndpoint + "/" + cartId + "/items");

        itemId = response.jsonPath().getInt("itemId");
        items.add(itemId);
    }

    @And("The item id is not null or empty")
    public void theItemIdIsNotNullOrEmpty() {

        Assertions.assertThat(itemId).isNotNull();
        Assertions.assertThat(itemId).isNotEqualTo(0);
    }
}
