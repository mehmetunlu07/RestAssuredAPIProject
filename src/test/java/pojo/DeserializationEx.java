package pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeserializationEx {

    public static void main(String[] args) throws IOException {

        //String file=new String(Files.readAllBytes(Paths.get("src/test/java/files/pojoJson.json")));
        //1st way:
        ObjectMapper mapper=new ObjectMapper();
        GetCourse gc=mapper.readValue(new String(Files.readAllBytes(Paths.get("src/test/java/files/pojoJson.json"))),GetCourse.class);

//        2nd way:
//        GetCourse gc=given().queryParam("","").body(new String(Files.readAllBytes(Paths.get("src/test/java/files/pojoJson.json"))))
//                .when().get(".....").as(GetCourse.class);

        System.out.println(gc.getExpertise());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getUrl());
        System.out.println(gc.getServices());
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getCourses().getApi().get(2).getCourseTitle());

        //Get the course names of api
        List<Api> apiCourses=gc.getCourses().getApi();
        for(int i=0;i<apiCourses.size();i++)
        {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SOAPUI"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        //Get the course names of WebAutomation
        List<WebAutomation> webList=gc.getCourses().getWebAutomation();
        for(int i=0;i<webList.size();i++){
            System.out.println(webList.get(i).getCourseTitle());
        }



        //Get the course names of WebAutomation and perform assertion
        List<String> list= new ArrayList<String>();
        List<WebAutomation> w=gc.getCourses().getWebAutomation();

        for(int j=0;j<w.size();j++)
        {
            list.add(w.get(j).getCourseTitle());
        }

        String []courseTitles={"Selenium Webdriver","Cypress","Protractor"};
        List<String> expectedList=	Arrays.asList(courseTitles);

        Assert.assertTrue(list.equals(expectedList));




    }

}
