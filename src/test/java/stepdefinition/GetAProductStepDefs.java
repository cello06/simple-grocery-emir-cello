package stepdefinition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import pojo.response.GetAProduct;

import java.io.File;

public class GetAProductStepDefs extends BaseStep {
    TestContext context = new TestContext();
    ObjectMapper mapper = new ObjectMapper();

    private static final Logger LOGGER = LogManager.getLogger(GetAProductStepDefs.class);

    @When("The user send GET request with {int} id to the get a product endpoint")
    public void theUserSendGETRequestWithIdToTheGetAProductEndpoint(int productId) {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(getAllProductsEndpoint + "/" + productId);

        LOGGER.info("The user send GET request with productId to the get a product endpoint");
    }

    @And("The product whose id is sent equals to the expected product")
    public void theProductWhoseIdIsSentEqualsToTheExpectedProduct() throws Exception {
        GetAProduct actualProduct = response.as(GetAProduct.class);

        File file = new File("src/test/resources/testdata/get_a_product.json");
        GetAProduct expectedProduct = mapper.readValue(file, new TypeReference<>() {
        });
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
        softAssertions.assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());
        softAssertions.assertThat(actualProduct.getCategory()).isEqualTo(expectedProduct.getCategory());
        softAssertions.assertThat(actualProduct.getPrice()).isEqualTo(expectedProduct.getPrice());
        softAssertions.assertThat(actualProduct.getManufacturer()).isEqualTo(expectedProduct.getManufacturer());
        softAssertions.assertThat(actualProduct.getInStock()).isEqualTo(expectedProduct.getInStock());
        softAssertions.assertThat(actualProduct.getCurrentStock()).isEqualTo(expectedProduct.getCurrentStock());

        softAssertions.assertAll();

        LOGGER.debug("The product whose id is sent equals to the expected product");
    }
}
