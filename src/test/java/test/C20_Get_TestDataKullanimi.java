package test;

import BaseURL.DummyBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataDummy;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C20_Get_TestDataKullanimi extends DummyBaseURL {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
    gonderdigimizde donen response’un status code’unun 200, content Type’inin
    application/json ve body’sinin asagidaki gibi oldugunu test edin.


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
        JSONObject expBody=testDataDummy.expDummyOlustur();

        // 3 - Reponse'i kaydet

        Response response=given()
                .spec(specDummy)
                .when()
                .get("/{pp1}/{pp2}/{pp3}/{pp4}");
        response.prettyPrint();

        //4 - Assertion


        JsonPath resJS=response.jsonPath();

        assertEquals(testDataDummy.basariliStatusKodu,response.statusCode());
        assertEquals(testDataDummy.contentType,response.contentType());

        assertEquals(expBody.getJSONObject("data").get("id"),resJS.get("data.id"));
        assertEquals(expBody.getJSONObject("data").get("employee_name"),resJS.get("data.employee_name"));
        assertEquals(expBody.getJSONObject("data").get("employee_salary"),resJS.get("data.employee_salary"));
        assertEquals(expBody.getJSONObject("data").get("employee_age"),resJS.get("data.employee_age"));
        assertEquals(expBody.getJSONObject("data").get("profile_image"),resJS.get("data.profile_image"));
        assertEquals(expBody.get("message"),resJS.get("message"));
        assertEquals(expBody.get("status"),resJS.get("status"));






    }



}
