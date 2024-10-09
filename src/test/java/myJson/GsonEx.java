package myJson;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class GsonEx {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();

        // Read JSON from file
        FileReader reader = new FileReader("src/test/java/myJson/data.json");
        Map<String, Object> map = gson.fromJson(reader, Map.class);

        // Accessing fields
        String name = (String) map.get("name");
        double age = (double) map.get("age");  // Gson parses numbers as double
        String city = (String) map.get("city");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}
