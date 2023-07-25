package testData;

import io.restassured.http.ContentType;
import org.json.JSONObject;

import java.util.HashMap;

public class TestDataDummy {
    public int basariliStatusKodu=200;

    public String contentType="application/json";
    /*
     {
          "status": "success",
          "data": {
             "id": 3,
             "employee_name": "Ashton Cox",
             "employee_salary": 86000,
             "employee_age": 66,
             "profile_image": ""
          },
          "message": "Successfully! Record has been fetched."
          }
     */



    public JSONObject dataJson(){

        JSONObject data=new JSONObject();
        data.put("id",3);
        data.put("employee_name","Ashton Cox");
        data.put("employee_salary",86000);
        data.put("employee_age",66);
        data.put("profile_image","");

       return data;
    }

    public JSONObject expDummyOlustur(){

        JSONObject expBody=new JSONObject();
        expBody.put("status","success");
        expBody.put("data",dataJson());
        expBody.put("message","Successfully! Record has been fetched.");

        return expBody;
    }
    public HashMap expDummyOlusturMap(){

        HashMap<String,Object> expBodyMap=new HashMap<>();
        expBodyMap.put("status","success");
        expBodyMap.put("data",dataJson());
        expBodyMap.put("message","Successfully! Record has been fetched.");

        return expBodyMap;
    }

}
