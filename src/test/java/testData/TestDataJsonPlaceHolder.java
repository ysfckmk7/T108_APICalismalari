package testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class TestDataJsonPlaceHolder {

    public int basariliStatusCode = 200;

    public JSONObject expectedBodyOlusturJson(){
         /*
        {
     "userId": 3,
     "id": 22,
     "title": "dolor sint quo a velit explicabo quia nam",
     "body": "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita ear
     um mollitia molestiae aut atque rem suscipit\nnam impedit esse"
}
         */
        JSONObject reqbody=new JSONObject();
        reqbody.put("userId",3);
        reqbody.put("id",22);
        reqbody.put("title","dolor sint quo a velit explicabo quia nam");
        reqbody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return reqbody;
    }

    /*
     Request Body
       {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }
     */
    public JSONObject requestBodyOlusturJson(){

        JSONObject reqBody=new JSONObject();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10);
        reqBody.put("id",70);

        return  reqBody;

    }

    public HashMap reqBodyOlusturMap(){

        /*
        {
       "title": "Ahmet",
       "body": "Merhaba",
       "userId": 10,
       "id": 70
       }
         */


        HashMap<String, Object> reqBody = new HashMap<>();
        reqBody.put("title","Ahmet");
        reqBody.put("body","Merhaba");
        reqBody.put("userId",10.0);
        reqBody.put("id",70.0);

        return reqBody;
    }






}
