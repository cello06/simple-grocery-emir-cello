package stepdefinition;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import pojo.response.GetAllProducts;

import java.io.File;
import java.util.List;

public class GetAllProductsStepDefs extends BaseStep {
    //TestContext testContext = new TestContext();

    ObjectMapper mapper = new ObjectMapper();

    @When("The user sends GET request to the get all products endpoint")
    public void theUserSendsGETRequestToTheGetAllProductsEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(getAllProductsEndpoint);

        //testContext.setResponse(response);
    }

    @And("The product list should contain all products")
    public void theProductListShouldContainAllProducts() throws Exception {


        List<GetAllProducts> actualProductsList = mapper.readValue(response.asString(), new TypeReference<>() {
        });

        File file = new File("src/test/resources/testdata/get_all_products.json");

        List<GetAllProducts> expectedProductList = mapper.readValue(file, new TypeReference<>() {
        });

        actualProductsList.forEach(
                item -> {
                    Assertions.assertThat(item.getId()).isEqualTo(expectedProductList.get(actualProductsList.indexOf(item)).getId());

                    Assertions.assertThat(item.getName()).isEqualTo(expectedProductList.get(actualProductsList.indexOf(item)).getName());

                    Assertions.assertThat(item.getCategory()).isEqualTo(expectedProductList.get(actualProductsList.indexOf(item)).getCategory());

                    Assertions.assertThat(item.getInStock()).isEqualTo(expectedProductList.get(actualProductsList.indexOf(item)).getInStock());

                }
        );

    }
}
