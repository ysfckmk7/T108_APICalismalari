package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class Test01 {


        /*
        1- GET https://reqres.in/api/users?page=2 kullanarak tüm data.email değerlerinin ".jpg" ile bittiğini
         ve hiçbirinin ".jpg" ile bitmediğini kontrol edin. onlar boş.

        2- https://reqres.in/api/users adresine bir POST isteği oluşturun:
           a- Kontrol Http Durum Kodu = 200
           b- Yanıt içerik türünün JSON olduğunu kontrol edin
           c- İstekte verilen verilerle eşleşen yanıtları kontrol edin. (isim ve iş)
           d- Kimlik parametresinin boş ve tamsayı olmadığını kontrol edin

       Kullanıcı verileri, istek gövdesinde bir JSON nesnesi olarak gönderilmelidir.

       Örnek Gönderi Verileri:
     {
       "name": "turknet",
       "iş": "apiTest"
      }
       1) GET https://gorest.co.in/public/v1/users kullanarak tüm data.id değerlerinin 4 haneli tamsayılar olduğunu
       ve hiçbirinin tam sayı olmadığını kontrol edin. onlar boş.

       2) https://gorest.co.in/public/v1/users adresine bir POST isteği oluşturun
       ve verilen verilerle eşleşen yanıtları kontrol edin.

       Kullanıcı verileri, istek gövdesinde bir JSON nesnesi olarak gönderilmelidir.
       Erişim belirteci, bir Taşıyıcı belirteci olarak gönderilmelidir.

       http Yetkilendirme başlığı.
       Erişim belirteci: 1db9c9b6c959682be7c96f74ca532c3cb0bd331f46b86a92602f8d319481b6f5

       Örnek veriler:

       3) https://gorest.co.in/public/v1/users uç noktasına aynı e-posta adresiyle tekrar bir POST isteği oluşturun ve doğrulayın
       kullanıcı oluşturulmadı.
       Dönen mesajı günlüğe kaydet uç noktadan
         */

    @Test
    public void get01(){

        // 1- GET https://reqres.in/api/users?page=2 kullanarak tüm data.email değerlerinin ".jpg" ile bittiğini
        // ve hiçbirinin ".jpg" ile bitmediğini kontrol edin. onlar boş.

        String url="https://reqres.in/api/users?page=2";

        Response response=given().when().get(url);
        response.prettyPrint();

    }
    @Test
    public void post01(){
        /*
        2- https://reqres.in/api/users adresine bir POST isteği oluşturun:
           a- Kontrol Http Durum Kodu = 200
           b- Yanıt içerik türünün JSON olduğunu kontrol edin
           c- İstekte verilen verilerle eşleşen yanıtları kontrol edin. (isim ve iş)
           d- Kimlik parametresinin boş ve tamsayı olmadığını kontrol edin
         */

        String url="https://reqres.in/api/users";
        JSONObject reqBody=new JSONObject();
        reqBody.put("name","turknet");
        reqBody.put("iş","apiTest");
        Response response=given().contentType(ContentType.JSON).when().body(reqBody.toString()).post(url);
        response.prettyPrint();

        System.out.println("StatusCode : " + response.getStatusCode());
        System.out.println("ContentType : " + response.contentType());
        response.then().assertThat()
                .statusCode(201)
                .contentType("application/json; charset=utf-8")
                .body("name", Matchers.equalTo("turknet"))
                .body("iş",Matchers.equalTo("apiTest"));

    }
    @Test
    public void get02(){
        /*
        1) GET https://gorest.co.in/public/v1/users kullanarak tüm data.id değerlerinin 4 haneli tamsayılar olduğunu
       ve hiçbirinin tam sayı olmadığını kontrol edin. onlar boş.
         */

        String url="https://gorest.co.in/public/v1/users";

        Response response=given().then().when().get(url);

        response.prettyPrint();

        response.then().assertThat()
                .body("data.id",Matchers.hasSize(10))
                .body("data.status",Matchers.hasItems("active","inactive"));

    }

    @Test
    public void post02(){

        /*
          2) https://gorest.co.in/public/v1/users adresine bir POST isteği oluşturun
       ve verilen verilerle eşleşen yanıtları kontrol edin.
         */

        String url ="https://gorest.co.in/public/v1/users";
        JSONObject reqBody=new JSONObject();
        reqBody.put("meta","null");
        reqBody.put("data.field","email");
        reqBody.put("data.message","can't be blank");
        reqBody.put("data.field","name");
        reqBody.put("data.message","can't be blank");
        reqBody.put("data.field","gender");
        reqBody.put("data.message","can't be blank, can be male of female");
        reqBody.put("data.field","status");
        reqBody.put("data.message","can't be blank");


        Response response=given().contentType(ContentType.JSON).then().when().post(url);
        response.prettyPrint();

        response.then().assertThat()

                .body("data.field",Matchers.equalTo("email"))
                .body("data.message",Matchers.equalTo("can't be blank"))

                .body("data.field",Matchers.equalTo("name"))
                .body("data.message",Matchers.equalTo("can't be blank"))

                .body("data.field",Matchers.equalTo("gender"))
                .body("data.message",Matchers.equalTo("can't be blank, can be male of female"))

                .body("data.field",Matchers.equalTo("status"))
                .body("data.message",Matchers.equalTo("can't be blank"));

    }
}
