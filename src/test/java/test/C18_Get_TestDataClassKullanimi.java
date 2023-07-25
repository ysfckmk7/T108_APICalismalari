package test;

import BaseURL.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class C18_Get_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {
    /*
      https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
      yolladigimizda donen response’in status kodunun 200 ve response body’sinin
      asagida verilen ile ayni oldugunutest ediniz

   Response body = Expected Data
 {
     "userId": 3,
     "id": 22,
     "title": "dolor sint quo a velit explicabo quia nam",
     "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
     um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}

     */
    @Test
    public void get01(){

        // 1 - url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",22);


        // 2 - Expected Data hazirla

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();

        JSONObject expData=testDataJsonPlaceHolder.expectedBodyOlusturJson();


        // 3 - Response'i kaydet

        Response response=given().spec(specJsonPlace).when().get("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion
        JsonPath reqjsonpath=response.jsonPath();

        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.getStatusCode());
        assertEquals(expData.get("userId"),reqjsonpath.get("userId"));
        assertEquals(expData.get("id"),reqjsonpath.get("id"));
        assertEquals(expData.get("title"),reqjsonpath.get("title"));
        assertEquals(expData.get("body"),reqjsonpath.get("body"));

    }
}
