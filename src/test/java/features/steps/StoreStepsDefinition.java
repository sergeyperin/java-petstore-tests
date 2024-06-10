package features.steps;

import java.io.FileNotFoundException;
import java.util.*;

import dto.Store;
import features.config.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.junit.Assert;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;

@RunWith(SerenityRunner.class)
public class StoreStepsDefinition {

    private Response res = null;
    private SoftAssertions softAssertion = null;

    @Before
    public void setup() throws FileNotFoundException {
        System.out.println(ConfigReader.getInstance().getConfig().getEnvs().get(System.getProperty("test.env")).getUrl());
        RestAssured.baseURI = ConfigReader.getInstance().getConfig().getEnvs().get(System.getProperty("test.env")).getUrl();
        this.softAssertion = new SoftAssertions();
    }

    @After
    public void tearDown() {
        this.softAssertion.assertAll();
        RestAssured.reset();
    }


    private Store store = null;
    private static String storeUrl = null;
    @Steps
    StoreApiSteps storeAPISteps;

    @Given("^I would like to place an order for a pet:$")
    public void i_would_like_to_place_an_order_for_a_pet(Map<String, String> order) {
        StoreStepsDefinition.storeUrl = order.get("url");
        //load list string value into class
        this.store = storeAPISteps.createStoreClass(order);
        this.res = storeAPISteps.createOrderRequest(StoreStepsDefinition.storeUrl, this.store);
        Store actualResponse = storeAPISteps.validatePostStatusAndReturnResponse(this.res);
        storeAPISteps.compareStore(this.softAssertion, this.store, actualResponse);
    }

    @When("^My order is placed, I would like to get the order information by \"([^\"]*)\"$")
    public void my_order_is_placed_I_would_like_to_get_the_order_information_by(String orderId) {
        Store actualResponse = storeAPISteps.fetchOrderInfoById(StoreStepsDefinition.storeUrl, orderId);
        storeAPISteps.compareStore(this.softAssertion, this.store, actualResponse);
    }

    @When("^I would like to get the order information by \"([^\"]*)\"$")
    public void i_would_like_to_get_the_order_information_by(String orderId) {
        this.res = storeAPISteps.fetchOrderInfo(StoreStepsDefinition.storeUrl, orderId);
    }

    @When("^I would like to delete my order by \"([^\"]*)\"$")
    public void i_would_like_to_delete_my_order_by_if_I_am_not_not_happy_with_it(String orderId) {
        this.res = storeAPISteps.deleteOrderInfoById(StoreStepsDefinition.storeUrl, orderId);
    }

    @Given("^I hit the \"([^\"]*)\"$")
    public void i_hit_the(String inventoryURL) throws Exception {
        this.res = storeAPISteps.fetchInventoryData(inventoryURL);
    }

    @When("^Request should submit and Positive API response should received \"([^\"]*)\"$")
    public void request_should_submit_and_Positive_API_response_should_received(String response) throws Exception {
        Assert.assertEquals("Status Check Passed!", response, this.res.getStatusCode() + "");
    }

    @Then("^Response should display$")
    public void response_data_should_display() throws Exception {
        String response = this.res.body().asString();
        System.out.println("Response data " + response);
        Assert.assertNotNull("Response data should present ", response);
    }
}
