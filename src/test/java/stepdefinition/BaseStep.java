package stepdefinition;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseStep {
    static protected Response response;

    static protected RequestSpecification request;
    protected String baseURI;
    protected static String accessToken;
    protected static String cartId;
    protected static Integer itemId;
    protected static String orderId;
    protected static List<Integer> items = new ArrayList<>();
    protected static List<Integer> products = new ArrayList<>();
    protected static List<String> orders = new ArrayList<>();

    protected static List<String> customerNames = new ArrayList<>();
    protected String registerEndpoint;
    protected String getAllProductsEndpoint;
    protected String getAProductEndpoint;
    protected String createCartEndpoint;
    protected String createOrderEndpoint;
    protected String getAllOrdersEndpoint;

    public BaseStep() {
        baseURI = ConfigManager.getProperty("base.URI");

        registerEndpoint = ConfigManager.getProperty("registration.endpoint");

        getAllProductsEndpoint = ConfigManager.getProperty("get.all.products.endpoint");

        getAProductEndpoint = ConfigManager.getProperty("get.a.product.endpoint");

        createCartEndpoint =ConfigManager.getProperty("create.cart.endpoint");

        createOrderEndpoint = ConfigManager.getProperty("create.order.endpoint");

        getAllOrdersEndpoint = ConfigManager.getProperty("get.all.order.endpoint");
    }
}
