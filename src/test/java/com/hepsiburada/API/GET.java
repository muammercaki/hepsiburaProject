package com.hepsiburada.API;

import com.hepsiburada.utils.Driver;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


public class GET extends Driver {

    protected static Logger logger = LogManager.getLogger(GET.class);


    @Step("Rest api GET request")
    public void restApiGet() {

        String restAPIURL="https://restful-booker.herokuapp.com/booking/{id}";


        RequestSpecification request = RestAssured
                .given()
                .pathParam("id","1");

        Response response = request
                .when()
                .get(restAPIURL)
                .then()
                .statusCode(200)
                .log().body().extract().response();


        int statusCode = response.getStatusCode();

        if (statusCode >=302) {
            Assert.fail("statusCode : " + statusCode + " : Status code not 200 ");
        }

        else

        logger.info(statusCode);
    }
}
