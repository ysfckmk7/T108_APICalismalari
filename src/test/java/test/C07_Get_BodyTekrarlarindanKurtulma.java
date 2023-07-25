package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class C07_Get_BodyTekrarlarindanKurtulma {
    /*

    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request gonderdigimizde
    donen Response’un,
         status code’unun 200,
         ve content type’inin application-json,
         ve response body’sindeki
                "firstname“in, "Mary",
                ve "lastname“in, "Jackson",
                ve "totalprice“in, 673,
                ve "depositpaid“in, true,
                ve "additionalneeds“in, "Breakfast"
         oldugunu test edin
     */


    /*
    {
    "firstname": "Mary",
    "lastname": "Jackson",
    "totalprice": 673,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2023-01-11",
        "checkout": "2023-02-28"
    }
     */



    @Test
    public void get01(){

        // 1 - Request icin gerekli url hazirla

        String url ="https://restful-booker.herokuapp.com/booking/10";

        // 2 - Expected Data hazirla

        // 3 - Response'i kaydet

        Response response = given().when().get(url);

        response.prettyPrint();

        // 4 - Assertion

       /* response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", Matchers.equalTo("Mary"),
                        "lastname",Matchers.equalTo("Jackson"),
                         "totalprice",Matchers.equalTo(860),
                        "depositpaid",Matchers.equalTo(true),
                        "additionalneeds",Matchers.nullValue());  */



        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Mary"),
                        "lastname",equalTo("Jackson"),
                        "totalprice",equalTo(860),
                        "depositpaid",equalTo(true),
                        "additionalneeds",nullValue());






    }
}
