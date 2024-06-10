package features.steps;


import java.math.BigInteger;
import java.text.ParseException;
import java.util.Map;

import net.serenitybdd.annotations.Step;
import dto.Store;
import org.junit.Assert;
import org.assertj.core.api.SoftAssertions;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StoreApiSteps {

    @Step
    public Store createStoreClass(Map<String, String> strStoreData) {
        String id = strStoreData.get("orderId");
        BigInteger petId = ((id == null) ? null : BigInteger.valueOf(Long.parseLong(id)));
        Store store = new Store(
                petId,
                Integer.valueOf(strStoreData.get("petId")),
                Integer.valueOf(strStoreData.get("quantity")),
                strStoreData.get("shipDate"),
                strStoreData.get("status"),
                Boolean.valueOf(strStoreData.get("complete"))
        );
        return store;
    }

    public Response createOrderRequest(String url, Store store) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder(url, "application/json", store);
        RequestSpecification requestSpec = apiRequestBuilder.getRequestSpecification();
        requestSpec = RestAssured.given().spec(requestSpec);
        Response res = requestSpec.when().post();
        return res;
    }

    @Step
    public Store validatePostStatusAndReturnResponse(Response res) {
        Store store = storeResponseDeSerialization(res);
        Assert.assertEquals("Status Check Passed!", 200, res.getStatusCode());
        return store;
    }

    private Store storeResponseDeSerialization(Response res) {
        return res.as(Store.class);
    }

    public void compareStore(SoftAssertions softAssertion, Store expected, Store actual) {
        if (expected.getId() != null) {
            softAssertion.assertThat(expected.getId()).isEqualTo(actual.getId());
        }
        softAssertion.assertThat(expected.getPetId()).isEqualTo(actual.getPetId());
        softAssertion.assertThat(expected.getQuantity()).isEqualTo(actual.getQuantity());
        String expectedShippedDate = strDateFormat(expected.getShipDate(), "yyyy-MM-dd'T'HH:mm", false);
        String actualShippedDate = strDateFormat(actual.getShipDate(), "yyyy-MM-dd'T'HH:mm", true);
        softAssertion.assertThat(expectedShippedDate).isEqualTo(actualShippedDate);
        softAssertion.assertThat(expected.getStatus()).isEqualTo(actual.getStatus());
        softAssertion.assertThat(expected.getComplete()).isEqualTo(actual.getComplete());
    }

    public String strDateFormat(String date, String format, boolean isUTCTime) {
        String returnDate = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            if (isUTCTime) {
                dateFormat.setTimeZone(TimeZone.GMT_ZONE);
            }
            returnDate = dateFormat.parse(date).toString();

        } catch (ParseException exception) {
            System.out.println("Failed to parse date > " + exception.getMessage());
        }
        return returnDate;
    }

    public Store fetchOrderInfoById(String orderUrl, String orderId) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder(orderUrl + "/" + orderId, "application/json", null);
        RequestSpecification requestSpec = apiRequestBuilder.getRequestSpecification();
        requestSpec = RestAssured.given().spec(requestSpec);
        Response res = requestSpec.when().get();
        Store expectedResponse = res.as(Store.class);
        Assert.assertEquals("Status Check Passed!", 200, res.getStatusCode());
        return expectedResponse;
    }

    public Response fetchOrderInfo(String orderUrl, String orderId) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder(orderUrl + "/" + orderId, "application/json", null);
        RequestSpecification requestSpec = apiRequestBuilder.getRequestSpecification();
        requestSpec = RestAssured.given().spec(requestSpec);
        Response res = requestSpec.when().get();
        return res;
    }

    public Response deleteOrderInfoById(String orderUrl, String orderId) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder(orderUrl + "/" + orderId, "application/json", null);
        RequestSpecification requestSpec = apiRequestBuilder.getRequestSpecification();
        requestSpec = RestAssured.given().spec(requestSpec);
        Response res = requestSpec.when().delete();
        return res;
    }

    public Response fetchInventoryData(String inventoryURL) {
        ApiRequestBuilder apiRequestBuilder = new ApiRequestBuilder(inventoryURL, "application/json", null);
        RequestSpecification requestSpec = apiRequestBuilder.getRequestSpecification();
        requestSpec = RestAssured.given().spec(requestSpec);
        Response res = requestSpec.when().get();
        return res;
    }
}
