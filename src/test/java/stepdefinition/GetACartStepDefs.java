package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GetACartStepDefs extends BaseStep{

    @And("The user sends GET request to the get a cart endpoint")
    public void theUserSendsGETRequestToTheGetACartEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(createCartEndpoint + "/" + cartId);
    }

    @And("The date is correctly displayed")
    public void theDateIsCorrectlyDisplayed() {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String expectedTime = dateFormatter.format(date);
        String actualTime = response.jsonPath().getString("created");
        actualTime = actualTime.split("T")[0];
        Assertions.assertThat(actualTime).isEqualTo(expectedTime);


    }
}
