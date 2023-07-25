package test;

import BaseURL.HerokuapBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class C16_BaseUrlHerokuapp extends HerokuapBaseURL {
    /*

    Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin

         1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
         gonderdigimizde donen response’un status code’unun 200 oldugunu ve
         Response’ta 12 booking oldugunu test edin

         2- https://restful-booker.herokuapp.com/booking
          endpointine asagidaki body’ye sahip bir POST
          request gonderdigimizde donen response’un
          status code’unun 200 oldugunu ve “firstname”
          degerinin “Ahmet” oldugunu test edin

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
     */
    @Test
    public void get01(){

        /*
         1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
         gonderdigimizde donen response’un status code’unun 200 oldugunu ve
         Response’ta 12 bookingid oldugunu test edin
         */
        // 1 - url hazirla (Path parametleri )
        specHerokApp.pathParam("pp1","booking");

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response=given().spec(specHerokApp).when().get("/{pp1}");
        response.prettyPrint();

        // 4 - Assertion

        response.then().assertThat()
                .statusCode(200)
                .body("bookingid",hasItem(77));

    }

    @Test
    public void post01(){
        /*
        2- https://restful-booker.herokuapp.com/booking
          endpointine asagidaki body’ye sahip bir POST
          request gonderdigimizde donen response’un
          status code’unun 200 oldugunu ve “firstname”
          degerinin “Ahmet” oldugunu test edin
         */

        String url ="https://restful-booker.herokuapp.com/booking";




        /*
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
         */
        JSONObject bookingdates=new JSONObject();
        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject reqbody=new JSONObject();
        reqbody.put("firstname","Ahmet");
        reqbody.put("lastname","Bulut");
        reqbody.put("totalprice",500);
        reqbody.put("depositpaid",false);
        reqbody.put("bookingdates",bookingdates);
        reqbody.put("additionalneeds","wi-fi");

        System.out.println("reqbody : " + reqbody);


        Response response=given().contentType(ContentType.JSON).body(reqbody.toString()).when().post(url);
        response.prettyPrint();


        response.then().assertThat()
                .statusCode(200)
                .body("booking.firstname",equalTo("Ahmet"));







    }
}
