package test;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02_Get_ResponseBilgileriAssertion {


    /*
    https://restful-booker.herokuapp.com/booking/10 url’ine bir GET request
    gonderdigimizde donen Response’un,
    status code’unun 200,
    ve content type’inin application/json; charset=utf-8,
    ve Server isimli Header’in degerinin Cowboy,
    ve status Line’in HTTP/1.1 200 OK
    oldugunu test edin

     */


    @Test
    public void get01() {


        // 1- Gonderecegimiz Request icin gerekli olan URL ve ihtiyacimiz varsa Request Body hazirla
        String url = "https://restful-booker.herokuapp.com/booking/10";


        // 2- Eger soruda bize verilmisse Expected Data hazirla

        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response = given().when().get(url);


        response.prettyPrint();

        // 4- Expected Data ile Actualy Datayi karsilastirmamiz yani Assertion yapmamiz gerek

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json; charset=utf-8").
                header("Server","Cowboy").
                statusLine("HTTP/1.1 200 OK");



    }
 }
