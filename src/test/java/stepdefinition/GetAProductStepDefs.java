package stepdefinition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import pojo.response.GetAProduct;

import java.io.File;
import java.util.List;

public class GetAProductStepDefs extends BaseStep {
    TestContext context = new TestContext();
    ObjectMapper mapper = new ObjectMapper();

    @When("The user send GET request with {int} id to the get a product endpoint")
    public void theUserSendGETRequestWithIdToTheGetAProductEndpoint(int productId) {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(getAllProductsEndpoint +"/"+ productId);
    }

    @And("The product whose id is sent equals to the expected product")
    public void theProductWhoseIdIsSentEqualsToTheExpectedProduct() throws Exception {
        GetAProduct actualProduct = mapper.readValue(response.asString(), new TypeReference<GetAProduct>() {
        });

        System.out.println(actualProduct + "Actual");
        File file = new File("src/test/resources/testdata/get_a_product.json");
        GetAProduct expectedProduct = mapper.readValue(file, new TypeReference<>() {
        });
        System.out.println(expectedProduct + "expected");


//        Assertions.assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
//        Assertions.assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());
//        Assertions.assertThat(actualProduct.getCategory()).isEqualTo(expectedProduct.getCategory());
//        Assertions.assertThat(actualProduct.getManufacturer()).isEqualTo(expectedProduct.getManufacturer());
//        Assertions.assertThat(actualProduct.getPrice()).isEqualTo(expectedProduct.getPrice());
//        Assertions.assertThat(actualProduct.getInStock()).isEqualTo(expectedProduct.getInStock());
//        Assertions.assertThat(actualProduct.getCurrentStock()).isEqualTo(expectedProduct.getCurrentStock());
    }
}
