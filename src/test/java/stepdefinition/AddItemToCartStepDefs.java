package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import pojo.request.AddItem;

public class AddItemToCartStepDefs extends BaseStep {

    private static final Logger LOGGER = LogManager.getLogger(AddItemToCartStepDefs.class);

    @And("The user sends POST request with {int} product id to add item to the cart endpoint")
    public void theUserSendsPOSTRequestWithProductIdToAddItemToTheCartEndpoint(int productId) {
        AddItem item = new AddItem();
        item.setProductId(productId);

        products.add(productId);

        response = RestAssured.given()
                .spec(request)
                .contentType("application/json")
                .body(item)
                .when().log().all()
                .post(createCartEndpoint + "/" + cartId + "/items");

        LOGGER.info("The user sends POST request with product id to add item to the cart endpoint");

        itemId = response.jsonPath().getInt("itemId");

        items.add(itemId);

    }

    @And("The item id is not null or empty")
    public void theItemIdIsNotNullOrEmpty() {

        Assertions.assertThat(itemId).isNotNull();
        Assertions.assertThat(itemId).isNotEqualTo(0);
        LOGGER.debug("Item id is successfully created!");
    }
}
