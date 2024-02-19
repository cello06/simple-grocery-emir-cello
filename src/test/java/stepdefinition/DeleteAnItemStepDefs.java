package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.List;

public class DeleteAnItemStepDefs extends BaseStep{

    private static final Logger LOGGER = LogManager.getLogger(DeleteAnItemStepDefs.class);

    @And("The user sends DELETE request to delete an item endpoint")
    public void theUserSendsDELETERequestToDeleteAnItemEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .delete(createCartEndpoint + "/" + cartId + "/items/" + itemId);

       LOGGER.info("The user sends DELETE request to delete an item endpoint");

        items.remove(itemId);
    }

    @And("The deleted item should not be displayed in items list")
    public void theDeletedItemShouldNotBeDisplayedInItemsList() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(createCartEndpoint + "/" + cartId + "/items");


        List<Integer> itemIds = response.jsonPath().getList("id");

        Assertions.assertThat(itemIds.contains(itemId)).isFalse();

        LOGGER.debug("Item is successfully deleted!");
    }
}
