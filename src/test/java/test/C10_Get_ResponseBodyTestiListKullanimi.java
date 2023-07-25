package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C10_Get_ResponseBodyTestiListKullanimi {
    /*

     http://dummy.restapiexample.com/api/v1/employees url'ine bir GET request
     yolladigimizda
     donen Response'in
            status code'unun 200,
            e content type'inin application/json,
            e response body'sindeki
            employees sayisinin 24
             employee'lerden birinin "Ashton Cox"
             girilen yaslar icinde 61,21 ve 35 degerinin oldugunu test edin test edin.



     */

    @Test
    public void get01(){

        // 1 - url  hazirla

        String url ="http://dummy.restapiexample.com/api/v1/employees";



        // 2- Eger soruda bize verilmisse Expected Data hazirla

        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response =given().when().get(url);
        response.prettyPrint();


        // 4 - Assertion

        response.then().assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("data.id", Matchers.hasSize(24))
                .body("data.employee_name",Matchers.hasItem("Ashton Cox"))
                .body("data.employee_age",Matchers.hasItems(61,21,35));








    }
}
