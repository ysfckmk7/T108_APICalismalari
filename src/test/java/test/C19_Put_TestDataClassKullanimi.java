package test;

import BaseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C19_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseURL {
    /*
    https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
      PUT request yolladigimizda donen response’in
      status kodunun 200, content type’inin “application/json; charset=utf-8”,
      Connection header degerinin “keep-alive”
      ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

       Request Body
       {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }

       Expected Data :
       {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }



     */

    @Test
    public void put01(){
        // 1 - url hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",70);

        JSONObject reqBody=new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);
        System.out.println("reqBody : " + reqBody);

        // 2 - Expected Data hazirla
        JSONObject exBody=new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);
        System.out.println("reqBody : " + reqBody);


        // 3 - Response'i kaydet
        Response response=given().contentType(ContentType.JSON).spec(specJsonPlace).body(reqBody.toString()).when().put("/{pp1}/{pp2}");
        response.prettyPrint();

        // 4 - Assertion

        JsonPath reqjsonpath=response.jsonPath();

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .header("Connection","keep-alive");

        assertEquals(reqBody.get("id"),reqjsonpath.get("id"));
        assertEquals(reqBody.get("title"),reqjsonpath.get("title"));
        assertEquals(reqBody.get("body"),reqjsonpath.get("body"));
        assertEquals(reqBody.get("userId"),reqjsonpath.get("userId"));





    }


}
