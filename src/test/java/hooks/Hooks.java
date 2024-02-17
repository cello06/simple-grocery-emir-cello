package hooks;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    Logger logger = LogManager.getLogger(Hooks.class);

    public final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setUpTestEnvironment(Scenario scenario){
        logger.info("::::::::::::::::::TEST INFORMATION:::::::::::::::::::::::::::");
        logger.info("Scenario " + scenario.getName() + "execute");
    }

    @After
    public void tearDownTestENvironment(Scenario scenario){
        if(scenario.isFailed()){
            Response response = context.getResponse();

            logger.error("Scenario " + scenario.getName() + " is failed");

            if(response != null){
                logger.error(response.getBody().prettyPrint());
            }else {
                logger.error("Scenario failed! But no response was set in the TestContext.");
            }
        }
        logger.info("Scenario" + scenario.getName() + "Finished!!");
        logger.info(":::::::::::::::::::::::::::::::::::::::::::::::");
    }
}
