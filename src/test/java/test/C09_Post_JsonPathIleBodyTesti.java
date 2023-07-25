package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C09_Post_JsonPathIleBodyTesti {
    /*

    https://restful-booker.herokuapp.com/booking url’ine asagidaki body'ye sahip bir POST
    request gonderdigimizde
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


       donen Response’un,
       status code’unun 200,
       ve content type’inin application-json,
       ve response body’sindeki
       "firstname“in,"Ahmet",
       ve "lastname“in, "Bulut",
       ve "totalprice“in,500,
       ve "depositpaid“in,false,
       ve "checkin" tarihinin 2021-06-01
       ve "checkout" tarihinin 2021-06-10
       ve "additionalneeds“in,"wi-fi"
       oldugunu test edin
     */

    @Test
    public void post01(){

        // 1 - url ve request body hazirla

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

        JSONObject reqinner=new JSONObject();
        reqinner.put("checkin","2021-06-01");
        reqinner.put("checkout","2021-06-10");


        JSONObject reqbody=new JSONObject();
        reqbody.put("firstname","Ahmet");
        reqbody.put("lastname","Bulut");
        reqbody.put("totalprice",500);
        reqbody.put("depositpaid",false);
        reqbody.put("bookingdates",reqinner);
        reqbody.put("additionalneeds","wi-fi");

        System.out.println(reqbody);

        // 2- Eger soruda bize verilmisse Expected Data hazirla

        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response =given()
                .contentType(ContentType.JSON)
                .when()
                .body(reqbody.toString())
                .post(url);

        response.prettyPrint();


        // 4 - Assertion
        /*
         donen Response’un,
       status code’unun 200,
       ve content type’inin application/json; charset=utf-8,
       ve response body’sindeki
       "firstname“in,"Ahmet",
       ve "lastname“in, "Bulut",
       ve "totalprice“in,500,
       ve "depositpaid“in,false,
       ve "checkin" tarihinin 2021-06-01
       ve "checkout" tarihinin 2021-06-10
       ve "additionalneeds“in,"wi-fi"
         */

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname",equalTo("Ahmet"),"booking.lastname", equalTo("Bulut"),
                        "booking.totalprice", equalTo(500),"booking.depositpaid", equalTo(false),
                        "booking.additionalneeds", equalTo("wi-fi"),
                        "booking.bookingdates.checkin",equalTo("2021-06-01"),
                        "booking.bookingdates.checkout",equalTo("2021-06-10"));

        System.out.println(reqbody.get("firstname"));

    }
}
