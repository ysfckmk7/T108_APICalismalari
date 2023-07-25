package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C13_Get_SoftAssertIleExpectedDataTesti {
    /*

     http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
   Response Body
{
        "status": "success",
        "data": {
            "id": 3,
            "employee_name": "Ashton Cox",
            "employee_salary": 86000,
            "employee_age": 66,
            "profile_image": ""
},
        "message": "Successfully! Record has been fetched."
}
     */
    @Test
    public void get01(){

        // 1 - url  hazirla

        String url ="http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Eger soruda bize verilmisse Expected Data hazirla

        JSONObject data =new JSONObject();
        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");


        JSONObject expbody=new JSONObject();
        expbody.put("status","success");
        expbody.put("message","Successfully! Record has been fetched.");
        expbody.put("data",data);

        System.out.println(expbody);


        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response=given().when().get(url);
        response.prettyPrint();

        // 4 - Assertion

        JsonPath resJSPath =response.jsonPath();

        SoftAssert softAssert =new SoftAssert();

        softAssert.assertEquals(resJSPath.get("status"),expbody.get("status"));
        softAssert.assertEquals(resJSPath.get("message"),expbody.get("message"));
        softAssert.assertEquals(resJSPath.get("data.id"),expbody.getJSONObject("data").get("id"));
        softAssert.assertEquals(resJSPath.get("data.employee_name"),expbody.getJSONObject("data").get("employee_name"));
        softAssert.assertEquals(resJSPath.get("data.employee_salary"),expbody.getJSONObject("data").get("employee_salary"));
        softAssert.assertEquals(resJSPath.get("data.employee_age"),expbody.getJSONObject("data").get("employee_age"));
        softAssert.assertEquals(resJSPath.get("data.profile_image"),expbody.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();



    }
}
