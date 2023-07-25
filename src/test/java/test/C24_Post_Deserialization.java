package test;

import BaseURL.HerokuapBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataHerokuapp;
import testData.TestDataJsonPlaceHolder;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C24_Post_Deserialization extends HerokuapBaseURL {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
    request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.


      Request body
      {
      "firstname" : "Ahmet",
      "lastname" : “Bulut",
      "totalprice" : 500,
      "depositpaid" : false,
      "bookingdates" : {
          "checkin" : "2021-06-01",
          "checkout" : "2021-06-10"
      },
      "additionalneeds" : "wi-fi"
      }

      Response Body // expected data
      {
      "bookingid": 24,
      "booking": {
          "firstname": "Ahmet",
          "lastname": "Bulut",
          "totalprice": 500,
          "depositpaid": false,
          "bookingdates": {
              "checkin": "2021-06-01",
              "checkout": "2021-06-10"
      },
      "additionalneeds": "wi-fi"
      }
      }

     */
    @Test
    public void post01(){

        // 1 - url ve body hazirla
        specHerokApp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp=new TestDataHerokuapp();
        HashMap<String,Object> resBody=testDataHerokuapp.reqBodyMap();

        // 2 - Expected Data hazirla

        HashMap<String,Object> expBody=testDataHerokuapp.expBodyMap();

        // 3 - Reponse'i kaydet

        Response response=given()
                .spec(specHerokApp)
                .contentType(ContentType.JSON)
                .when()
                .body(resBody)
                .post("/{pp1}");

        response.prettyPrint();

        //4 - Assertion


       HashMap<String,Object> respMAP=response.as(HashMap.class );

       assertEquals(((Map)(expBody.get("booking"))).get("firstname"),(((Map)respMAP.get("booking"))).get("firstname"));
       assertEquals(((Map)(expBody.get("booking"))).get("lastname"),(((Map)respMAP.get("booking"))).get("lastname"));
        assertEquals(((Map)(expBody.get("booking"))).get("totalprice"),(((Map)respMAP.get("booking"))).get("totalprice"));
        assertEquals(((Map)(expBody.get("booking"))).get("depositpaid"),(((Map)respMAP.get("booking"))).get("depositpaid"));
        assertEquals(((Map)(expBody.get("booking"))).get("additionalneeds"),(((Map)respMAP.get("booking"))).get("additionalneeds"));

        assertEquals(((Map)(((Map)(expBody.get("booking"))).get("bookingdates"))).get("checkin")
                ,((Map)(((Map)(respMAP.get("booking"))).get("bookingdates"))).get("checkin"));

        assertEquals(((Map)(((Map)(expBody.get("booking"))).get("bookingdates"))).get("checkout")
                ,((Map)(((Map)(respMAP.get("booking"))).get("bookingdates"))).get("checkout"));








    }
}

