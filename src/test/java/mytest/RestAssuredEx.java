package mytest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredEx {

    public static void main(String[] args) {

        RestAssured.baseURI="https://rahulshettyacademy.com/";

//        JSONObject js=new JSONObject();
//        js.put("name","Learn Appium Automation with Java");
//        js.put("isbn","bck");
//        js.put("aisle","676770");
//        js.put("author","Haci Unlu");
//
//        given().contentType(ContentType.JSON).body(js.toString())
//                .when().post("Library/Addbook.php")
//                .then().log().all().statusCode(equalTo(200));


//        given().contentType(ContentType.JSON).body("{\n" +
//                "\"name\":\"Learning Python1 Automation with Java\",\n" +
//                "\"isbn\":\"fkijje\",\n" +
//                "\"aisle\":\"3437\",\n" +
//                "\"author\":\"Hu Yoe\"\n" +
//                "}")
//                .when().post("Library/Addbook.php")
//                .then().log().all().statusCode(200);


//        File file = new File("src/test/java/files/book.json");
//
//        given().body(file)
//                .when().post("Library/Addbook.php")
//                .then().log().all().statusCode(equalTo(200));

       //schema validation

        String schema="{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"Msg\": { \"type\": \"string\" },\n" +
                "    \"ID\": { \"type\": \"string\" }\n" +
                "  },\n" +
                "  \"required\": [\"Msg\", \"ID\"]\n" +
                "}";

        given().contentType(ContentType.JSON).body("{\n" +
                        "\"name\":\"Learning Python1 Automation with Java\",\n" +
                        "\"isbn\":\"fjkjjje\",\n" +
                        "\"aisle\":\"3457\",\n" +
                        "\"author\":\"Hu Yoe\"\n" +
                        "}")
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat()
                .body(matchesJsonSchema(schema));



    }
}
