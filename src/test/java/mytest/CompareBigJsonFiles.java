package mytest;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class CompareBigJsonFiles {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        // Load the two JSON files into JsonNode objects
        JsonNode jsonNode1 = mapper.readTree(new File("src/test/java/files/AddPlaceJson.json"));
        JsonNode jsonNode2 = mapper.readTree(new File("src/test/java/files/AddPlaceJson1.json"));

        // Simple equality check
        if (jsonNode1.equals(jsonNode2)) {
            System.out.println("The two JSON files are equal.");
        } else {
            System.out.println("The two JSON files are different.");
            // Detailed comparison
            compareJsonNodes(jsonNode1, jsonNode2);
        }

    }

    public static void compareJsonNodes(JsonNode node1, JsonNode node2) {
        // Iterate through the fields of the first JSON object
        Iterator<Map.Entry<String, JsonNode>> fields = node1.fields();

        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            String fieldName = field.getKey();
            JsonNode jsonNode1 = field.getValue();
            JsonNode jsonNode2 = node2.get(fieldName);

            if (jsonNode2 == null) {
                System.out.println("Field '" + fieldName + "' is missing in the second JSON.");
            } else if (!jsonNode1.equals(jsonNode2)) {
                System.out.println("Field '" + fieldName + "' differs.");
                System.out.println("File1: " + jsonNode1);
                System.out.println("File2: " + jsonNode2);
            }
        }

        // Check for fields in the second JSON that are missing in the first
        fields = node2.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            if (!node1.has(field.getKey())) {
                System.out.println("Field '" + field.getKey() + "' is present in the second JSON but missing in the first.");
            }
        }
    }




}
