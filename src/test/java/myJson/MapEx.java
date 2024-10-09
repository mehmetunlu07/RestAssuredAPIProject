package myJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MapEx {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON from file
        Map<String, Object> map = objectMapper.readValue(new File("src/test/java/myJson/data.json"), Map.class);

        // Accessing fields from the map
        String name = (String) map.get("name");
        int age = (int) map.get("age");
        String city = (String) map.get("city");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}
