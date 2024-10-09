package mytest;
import files.payload;
import io.restassured.path.json.JsonPath;

import files.ReUsableMethods;
import org.testng.Assert;

public class ComplexJsonParse {
    public static void main(String[] args) {
        String response= payload.CoursePrice();
        JsonPath js= ReUsableMethods.rawToJson(response);

        //1. Print No of courses returned by API
        int numCourses=js.getInt("courses.size()");
        System.out.println("Number of courses.."+numCourses);

        //2.Print Purchase Amount
        int purchaseAmount=js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount :"+purchaseAmount);

        //3. Print Title of the first course
        String titleFirst=js.getString("courses[0].title");
        System.out.println("Title of first course:"+titleFirst);


        //4. Print All course titles and their respective Prices
         String title="";
         int price=0;
         for(int i=0;i<numCourses;i++) {
             title = js.getString("courses[" + i + "].title");
             price = js.getInt("courses[" + i + "].price");
             System.out.println(title+"  -  "+price);
         }
        //5. Print no of copies sold by RPA Course
        for(int i=0;i<numCourses;i++) {
            String courseTitle = js.getString("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA")) {
                int numCopies = js.getInt("courses[" + i + "].copies");
                System.out.println("Num of copies sold by RPA :"+numCopies);
                break;
            }
        }


        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int totalAmount=0;
        for(int i=0; i<numCourses; i++) {
            price=js.getInt("courses["+ i +"].price");
            int copy=js.getInt("courses["+ i +"].copies");
            totalAmount= totalAmount+(price*copy);
        };
        System.out.println("Total Amount Purchased: "+totalAmount);
        Assert.assertEquals(purchaseAmount,totalAmount);
        System.out.println(purchaseAmount+" - "+totalAmount);

    }

}
