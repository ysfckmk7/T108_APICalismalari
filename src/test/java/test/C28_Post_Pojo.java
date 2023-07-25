package test;

import BaseURL.HerokuapBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderReqBodyPojo;
import pojos.PojoHerokuAppBooking;
import pojos.PojoHerokuAppBookingDates;
import pojos.PojoHerokuAppExpBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C28_Post_Pojo extends HerokuapBaseURL {
    /*
    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
request gonderdigimizde donen response’un id haric asagidaki gibi oldugunu test edin.


            Request body
            {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" :
            {
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

        PojoHerokuAppBookingDates bookingDates=new PojoHerokuAppBookingDates("2021-06-01","2021-06-10");
        PojoHerokuAppBooking reqBody=new PojoHerokuAppBooking("Ahmet","Bulut",500,false,bookingDates,"wi-fi");

        // 2 - Expected Data hazirla
        PojoHerokuAppExpBody expBody=new PojoHerokuAppExpBody(24,reqBody);

        // 3 - Response'i kaydet

        Response response=given()
                .spec(specHerokApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody)
                .post("/{pp1}");
        response.prettyPrint();

        // 4 - Assertion

        PojoHerokuAppExpBody resPojo=response.as(PojoHerokuAppExpBody.class);

        assertEquals(expBody.getBooking().getFirstname(),resPojo.getBooking().getFirstname());
        assertEquals(expBody.getBooking().getLastname(),resPojo.getBooking().getLastname());
        assertEquals(expBody.getBooking().getTotalprice(),resPojo.getBooking().getTotalprice());
        assertEquals(expBody.getBooking().isDepositpaid(),resPojo.getBooking().isDepositpaid());
        assertEquals(expBody.getBooking().getBookingdates().getCheckin(),resPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expBody.getBooking().getBookingdates().getCheckout(),resPojo.getBooking().getBookingdates().getCheckout());
        assertEquals(expBody.getBooking().getAdditionalneeds(),resPojo.getBooking().getAdditionalneeds());



    }


}
