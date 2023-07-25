package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C12_Post_ExpectedDataVeJsonPathIleAssertion {
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


        Response Body - Expected Body
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

        // 1 - url ve  Request body hazirla

        String url ="https://restful-booker.herokuapp.com/booking";

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin","2021-06-01");
        bookingdates.put("checkout","2021-06-10");

        JSONObject reqbody=new JSONObject();
        reqbody.put("firstname" , "Ahmet");
        reqbody.put("lastname"  , "Bulut");
        reqbody.put("totalprice" , 500);
        reqbody.put("depositpaid" , false);
        reqbody.put("bookingdates" , bookingdates);
        reqbody.put("additionalneeds" , "wi-fi");

        System.out.println("reqbody : " + reqbody);

        // 2- Eger soruda bize verilmisse Expected Data hazirla

        JSONObject expbody=new JSONObject();
        expbody.put("bookingid",24);
        expbody.put("booking",reqbody);

        System.out.println("expbody : " + expbody);



        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response=given().contentType(ContentType.JSON).when().body(reqbody.toString()).post(url);
        response.prettyPrint();


        // 4 - Assertion

        JsonPath resJSPath=response.jsonPath();

        assertEquals(expbody.getJSONObject("booking").get("firstname"),resJSPath.get("booking.firstname"));
        assertEquals(expbody.getJSONObject("booking").get("lastname"),resJSPath.get("booking.lastname"));
        assertEquals(expbody.getJSONObject("booking").get("totalprice"),resJSPath.get("booking.totalprice"));
        assertEquals(expbody.getJSONObject("booking").get("depositpaid"),resJSPath.get("booking.depositpaid"));
        assertEquals(expbody.getJSONObject("booking").get("additionalneeds"),resJSPath.get("booking.additionalneeds"));
        assertEquals(expbody.getJSONObject("booking").getJSONObject("bookingdates").get("checkin"),resJSPath.get("booking.bookingdates.checkin"));
        assertEquals(expbody.getJSONObject("booking").getJSONObject("bookingdates").get("checkout"),resJSPath.get("booking.bookingdates.checkout"));


    }
}
