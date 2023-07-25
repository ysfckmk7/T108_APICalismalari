package test;

import BaseURL.HerokuapBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testData.TestDataHerokuapp;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C21_Post_TestDataKullanimi extends HerokuapBaseURL {
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

           Response Body
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


     */
    @Test
    public void post01(){

        // 1 - url ve Request body hazirla

        specHerokApp.pathParam("pp1","booking");

        TestDataHerokuapp testDataHerokuapp=new TestDataHerokuapp();

        JSONObject reqBody=testDataHerokuapp.reqBodyJson();

        // 2 - Expected Data hazirla

        JSONObject expBody= testDataHerokuapp.expBodyJson();

        // 3 - Reponse'i kaydet

        Response response=given()
                .spec(specHerokApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");


        response.prettyPrint();


        // 4 - Assertion

        JsonPath resJP=response.jsonPath();

        assertEquals(testDataHerokuapp.basariliStatuskodu,response.statusCode());

        assertEquals(expBody.getJSONObject("booking").get("firstname"),resJP.get("booking.firstname"));
        assertEquals(expBody.getJSONObject("booking").get("lastname"),resJP.get("booking.lastname"));
        assertEquals(expBody.getJSONObject("booking").get("totalprice"),resJP.get("booking.totalprice"));
        assertEquals(expBody.getJSONObject("booking").get("depositpaid"),resJP.get("booking.depositpaid"));
        assertEquals(expBody.getJSONObject("booking").get("additionalneeds"),resJP.get("booking.additionalneeds"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),resJP.get("booking.bookingdates.checkin"));
        assertEquals(expBody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),resJP.get("booking.bookingdates.checkout"));

    }


}
