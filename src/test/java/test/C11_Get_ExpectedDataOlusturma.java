package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C11_Get_ExpectedDataOlusturma {
    /*
    https://jsonplaceholder.typicode.com/posts/22 url'ine bir GET request
     yolladigimizda donen response body’sinin asagida verilen ile ayni oldugunu test ediniz
    Response body :
    {
               "userId": 3,
               "id": 22,
               "title": "dolor sint quo a velit explicabo quia nam",
               "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
               um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
   }
     */

    @Test
    public void get01(){

        // 1 - url  hazirla

        String url ="https://jsonplaceholder.typicode.com/posts/22";

        // 2- Eger soruda bize verilmisse Expected Data hazirla

        JSONObject reqbody=new JSONObject();
        reqbody.put("userId",3);
        reqbody.put("id",22);
        reqbody.put("title","dolor sint quo a velit explicabo quia nam");
        reqbody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        System.out.println("reqbody :" + reqbody);

        // 3- Bize donen Response'i Actualy Data olarak kaydet

        Response response=given().when().get(url);
        response.prettyPrint();

        // 4 - Assertion

        // NOT : Donen Response'in Body'si ile islem yapmak istiyorsak bunu JSONPath objesine donusturmemiz gerekiyor


        JsonPath resJSPath =response.jsonPath();

      assertEquals(reqbody.get("userId"),resJSPath.get("userId"));
      assertEquals(reqbody.get("id"),resJSPath.get("id"));
      assertEquals(reqbody.get("title"),resJSPath.get("title"));
      assertEquals(reqbody.get("body"),resJSPath.get("body"));






    }
}
