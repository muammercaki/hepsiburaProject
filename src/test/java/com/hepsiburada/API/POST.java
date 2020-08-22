package com.hepsiburada.API;

import com.hepsiburada.utils.Driver;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;


public class POST extends Driver {
    protected static Logger logger = LogManager.getLogger(POST.class);

    @Step("Rest api POST request")
    public void restApiPost() {

        String body = " '   {  '  + \r\n" +
                " '       \"name\": \"morpheus\",  '  + \r\n" +
                " '       \"job\": \"leader\"  '  + \r\n" +
                " '  }  ' ; ";

        RestAssured.baseURI = "https://reqres.in/";

        String response = given().
                body(body).
                when().
                post("/api/users").
                then().assertThat().
                statusCode(201).and().
                contentType(ContentType.JSON).
                extract().
                response().asString();

        logger.info("Response is: " + response);

    }


}

