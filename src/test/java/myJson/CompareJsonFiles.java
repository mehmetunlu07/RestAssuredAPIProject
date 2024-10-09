package myJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import java.io.StringReader;

import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.diff.JsonDiff;

public class CompareJsonFiles {
      static String json1="{\n" +
              "  \"name\": \"Chris\",\n" +
              "  \"age\": 23,\n" +
              "  \"city\": \"New York\"\n" +
              "}";

      static String json2="{\n" +
              "  \"name\": \"Christian\",\n" +
              "  \"age\": 230,\n" +
              "  \"city\": \"New Yorker new\"\n" +
              "}";


    public static void main(String[] args) throws JsonProcessingException {
        ///////////////1st method: with JsonNode /////////////////////////////////
          ObjectMapper mapper=new ObjectMapper();

          JsonNode jnode1=mapper.readTree(json1);
          JsonNode jnode2=mapper.readTree(json2);

         // Assert.assertEquals(json1,json2);   // if assertion fails gives difference
        //System.out.println(json1.equals(json2));


        if (jnode1.equals(jnode2)) {
            System.out.println("Both JSON files are equal.");
        } else {
            System.out.println("The JSON files are different.");
        }

        /////////////////// 2nd method:Json-Patch////////////////////////////

        // Generate a patch (differences) between the two JSONs
        //JsonNode patch = JsonDiff.asJson(jnode1, jnode2);
        // Print the patch (added, removed, or modified fields)
        //System.out.println("Differences (Patch): " + patch.toString());

    }
}
