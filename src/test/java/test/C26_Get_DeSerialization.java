package test;

import BaseURL.DummyBaseURL;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Get_DeSerialization extends DummyBaseURL {
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
        // 1 - url hazirla
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2 - Expected Data hazirla
        TestDataDummy testDataDummy=new TestDataDummy();
        HashMap<String,Object> expBody=testDataDummy.expDummyOlusturMap();

        // 3 - Response'i kaydet
        Response response=given()
                .spec(specDummy)
                        .when()
                        .get("/{pp1}/{pp2}/{pp3}/{pp4}");
                response.prettyPrint();
         // 4 - Assertion

        HashMap<String,Object> respMap=response.as(HashMap.class);

        assertEquals(testDataDummy.basariliStatusKodu,response.statusCode());
        assertEquals(expBody.get("status"),respMap.get("status"));
        assertEquals(expBody.get("message"),respMap.get("message"));
        assertEquals(    ((Map)(expBody.get("data") )).get("id"),((( (Map)respMap.get("data") )).get("id") )     );
        assertEquals(    ((Map)(expBody.get("data") )).get("employee_name"),((( (Map)respMap.get("data") )).get("employee_name") )     );
        assertEquals(    ((Map)(expBody.get("data") )).get("employee_salary"),((( (Map)respMap.get("data") )).get("employee_salary") )     );
        assertEquals(    ((Map)(expBody.get("data") )).get("employee_age"),((( (Map)respMap.get("data") )).get("employee_age") )     );
        assertEquals(    ((Map)(expBody.get("data") )).get("profile_image"),((( (Map)respMap.get("data") )).get("profile_image") )     );







    }



}
