package mytest;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MapParsing {
    public static void main(String[] args) throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

   //******************* Converting response to Map ******************************************

        Response response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json"))))
                .when().post("maps/api/place/add/json");

        System.out.println("Response as String ---- "+response.asString());
                Map<String, Object> map=response.jsonPath().getMap("");
                System.out.println("Response as Map----  "+map);

        String id="", scope="";
                Set<String> set=map.keySet();
                for(String s:set){
                    System.out.println(s+" - "+ map.get(s));
                    if(s.equals("scope")){
                        scope=map.get(s).toString();
                    }else if(s.equals("id")){
                        id=map.get(s).toString();
                    }
                }

        System.out.println(scope);
        System.out.println(id);

 //***********2nd way:Convert response to Map with ObjectMapper

        ObjectMapper mapper=new ObjectMapper();

        Map<String, Object> mapAddPlace=mapper.readValue(new File("src/test/java/files/AddPlaceJson.json"),Map.class);

        Map<String, Object> mapAddPlace1=mapper.readValue(new String(Files.readAllBytes(Paths.get("src/test/java/files/AddPlaceJson.json"))),Map.class);


        System.out.println("Convert response to Map with readValue ----"+ mapAddPlace);
        System.out.println("Convert response to Map with readValue ----"+ mapAddPlace1);


        //******************* Writing response a json file ******************************************

        mapper.writeValue(new File("newAddPlace1.json"), response.asString());  //mapAddPlace ya da person   // map ya da person object olarak da oluyor
        System.out.println("file written successfully");









    }
}
