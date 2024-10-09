package mytest;
import static io.restassured.RestAssured.*;  // for given

import files.ReUsableMethods;
import files.payload;
import io.restassured.path.json.JsonPath;  //for JasonPath

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.Addbook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }

    @DataProvider(name="BooksData")
    public Object[][] getdata(){
        return new Object[][] {{"klmn","1234"},{"zxcv","4567"},{"qwer","6789"}};
    }





}
