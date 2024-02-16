package stepdefinition;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigManager;

public abstract class BaseStep {
    static protected Response response;
    static protected RequestSpecification request;
    protected String baseURI;
    protected String accessToken;
    protected String registerEndpoint;
    protected String getAllProductsEndpoint;
    protected String getAProductEndpoint;

    public BaseStep() {
        baseURI = ConfigManager.getProperty("base.URI");

        registerEndpoint = ConfigManager.getProperty("registration.endpoint");

        getAllProductsEndpoint = ConfigManager.getProperty("get.all.products.endpoint");

        getAProductEndpoint = ConfigManager.getProperty("get.a.product.endpoint");
    }
}
