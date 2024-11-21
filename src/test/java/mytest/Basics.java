package mytest;

import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;  //for RestAssured
import io.restassured.path.json.JsonPath;  //for JasonPath
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;  // for given
import static org.hamcrest.Matchers.equalTo;  //for equalTo
import static org.hamcrest.Matchers.*;   //for equalTo

public class Basics {

    public static void main(String[] args) throws IOException {
     /*
        given - all input details
        when - submit the api  - resource, http method
        then - validate the response

        Using static json file from external location:
        Read content of a file and convert into Byte-->Byte data to String
         */
        //*******************************Validate AddPlaceAPI**************************************
       RestAssured.baseURI = "https://rahulshettyacademy.com";

//        String body=new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json")));  //Putting jason file in src
//        System.out.println("Body :"+body);    //just a commit

        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json"))))
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js=new JsonPath(response);   //for parsing Json
        String placeId=js.getString("place_id");
        System.out.println("placeId"+"="+placeId);

        //***************************UpdatePlaceAPI*********************************************
        System.out.println("********************UpdatePlaceAPI*********************************");
        String newAddress="70 winter walk, Wylie, TX, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.UpdatePlace(placeId,newAddress))
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //****************************GetPlaceAPI************************************************
        System.out.println("********************GetPlaceAPI*********************************");


        Response getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("maps/api/place/get/json");
//                .then().log().all().assertThat().statusCode(200).body("address",equalTo("70 winter walk, Wylie, TX, USA"))
//                .extract().response();  //.asString();

        System.out.println("***********  "+getPlaceResponse.asString());

        JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse.asString());
        String actualAddress=js1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newAddress);
        Assert.assertTrue(actualAddress.equals(newAddress));

        //Storing response in a Map
        Map<String,Object> responseMap=getPlaceResponse.jsonPath().getMap("");
        System.out.println("----->  "+responseMap);
        Set<String> set=responseMap.keySet();
        for(String s:set){
         System.out.println(s+" - "+responseMap.get(s));
         if(s.contains("web")){
          break;
         }
        }

        //Storing response in a json file
        FileWriter file = new FileWriter("src/test/java/files/responseGetPlace.json");
        file.write(getPlaceResponse.asString());
        file.flush();
        file.close();

        System.out.println("Response saved to responseGetPlace.json file.");


     System.out.println("****************** Compare 2 response **************");
     String str=getPlaceResponse.asString();
     String str1="abc";  //getPlaceResponse.asString();
     if(str.equals(str1)) System.out.println(" Strings are the same");
     else System.out.println(" Strings are different");

    }


}
