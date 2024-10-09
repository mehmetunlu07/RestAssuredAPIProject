package myJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonEx {

    public static void main(String[] args) throws JsonProcessingException {


        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";

// Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse JSON string
        Person person = objectMapper.readValue(jsonString, Person.class);

// Access parsed data
        person.setAge(25);
        System.out.println(person.getName()); // Output: John
        System.out.println(person.getAge()); // Output: 30
        System.out.println(person.getCity()); // Output: New York
        System.out.println(person.toString());

    }

}
