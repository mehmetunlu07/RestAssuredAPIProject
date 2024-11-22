package mytest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class APIEx1 {

    public static void main(String[] args) throws IOException {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response response = given().log().all().cookie("sessionId","abcd24").queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json"))))
                .when().post("maps/api/place/add/json");

        response.then().log().all();


        String placeId=response.jsonPath().getString("place_id");
        System.out.println("PlaceId: "+ placeId);
        String cookieValue=response.getCookie("sessionId");
        System.out.println("cookie: "+cookieValue);

        System.out.println("************************* GET Add Place *******************************");
        given().log().all().contentType(ContentType.JSON).queryParam("place_id", placeId).queryParam("key","qaclick123")
                .when().get("/maps/api/place/get/json")
                .then().log().all().statusCode(200);






    }
}
