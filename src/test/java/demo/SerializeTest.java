package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.*;

import static io.restassured.RestAssured.*;

public class SerializeTest {
    public static void main(String[] args) throws JsonProcessingException {
        RestAssured.baseURI="https://rahulshettyacademy.com";

        AddPlace p=new AddPlace();

        p.setAccuracy(50);
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setAddress("29, side layout, cohen 09");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");

        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        List<String> list=new ArrayList<String>();
        list.add("shoe park");
        list.add("shop");
        p.setTypes(list);


        String responseString=given().queryParam("key ","qaclick123").header("Content-Type","application/json")
                .body(p)
                .when().post("/maps/api/place/add/json")   //.asString();
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

       System.out.println(responseString);

        //2nd way:
        ObjectMapper mapper=new ObjectMapper();
        String addPlace=mapper.writeValueAsString(p);
        System.out.println("----->  "+addPlace);





    }
}
