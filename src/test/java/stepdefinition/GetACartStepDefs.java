package stepdefinition;

import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GetACartStepDefs extends BaseStep{

    private static final Logger LOGGER = LogManager.getLogger(GetACartStepDefs.class);

    @And("The user sends GET request to the get a cart endpoint")
    public void theUserSendsGETRequestToTheGetACartEndpoint() {
        response = RestAssured.given()
                .spec(request)
                .when()
                .get(createCartEndpoint + "/" + cartId);

        LOGGER.info("The user sends GET request to the get a cart endpoint");
    }

    @And("The date is correctly displayed")
    public void theDateIsCorrectlyDisplayed() {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String expectedTime = dateFormatter.format(date);

        String actualTime = response.jsonPath().getString("created");
        actualTime = actualTime.split("T")[0];

        Assertions.assertThat(actualTime).isEqualTo(expectedTime);

        LOGGER.debug("The date is correctly displayed");


    }
}
