package org.example;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.net.http.HttpResponse;

import static io.restassured.RestAssured.given;

public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://postman-echo.com";
        Response response = given()
                .when()
                .patch("/delete")
                .then()
                .extract()
                .response();

        System.out.println(response.getBody().asString());



    }
}
