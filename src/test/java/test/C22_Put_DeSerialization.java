package test;

import BaseURL.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C22_Put_DeSerialization extends JsonPlaceHolderBaseURL {
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

        TestDataJsonPlaceHolder testDataJsonPlaceHolder=new TestDataJsonPlaceHolder();
        HashMap<String,Object> reqBody = testDataJsonPlaceHolder.reqBodyOlusturMap();

        // 2 - Expected Data hazirla

        HashMap<String,Object> expBody = testDataJsonPlaceHolder.reqBodyOlusturMap();

        // 3 - Reponse'i kaydet

        Response response=given()
                .spec(specJsonPlace)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .put("/{pp1}/{pp2}");

        response.prettyPrint();

        // 4 - Assertion

        HashMap<String,Object> respMAP=response.as(HashMap.class ); // Bu adimda De-Serialization islemini gerceklestirdik

        assertEquals(testDataJsonPlaceHolder.basariliStatusCode,response.statusCode());

        assertEquals(expBody.get("id"),respMAP.get("id"));
        assertEquals(expBody.get("title"),respMAP.get("title"));
        assertEquals(expBody.get("body"),respMAP.get("body"));
        assertEquals(expBody.get("userId"),respMAP.get("userId"));





    }
}
