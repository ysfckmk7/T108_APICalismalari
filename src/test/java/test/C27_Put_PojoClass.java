package test;

import BaseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderReqBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C27_Put_PojoClass extends JsonPlaceHolderBaseURL {
    /*

https://jsonplaceholder.typicode.com/posts/70 url'ine asagidaki body’e sahip bir
PUT request yolladigimizda donen response’in response body’sinin asagida
verilen ile ayni oldugunu test ediniz

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

        // 1 - url ve body hazirla
        specJsonPlace.pathParams("pp1","posts","pp2",70);
        JsonPlaceHolderReqBodyPojo reqBody = new JsonPlaceHolderReqBodyPojo("Ahmet","body",10,70);

        System.out.println("reqBody : " + reqBody);

        // 2 - Expected Data hazirla
        JsonPlaceHolderReqBodyPojo expBody = new JsonPlaceHolderReqBodyPojo("Ahmet","body",10,70);

        // 3 - Response'i kaydet

        Response response=given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4 - Assertion
        JsonPlaceHolderReqBodyPojo resPojo = response.as(JsonPlaceHolderReqBodyPojo.class);

        assertEquals(expBody.getTitle(),resPojo.getTitle());
        assertEquals(expBody.getBody(),resPojo.getBody());
        assertEquals(expBody.getId(),resPojo.getId());
        assertEquals(expBody.getUserId(),resPojo.getUserId());











    }
}
