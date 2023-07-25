package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriCagirma {
    /*

    {"firstName":"John",
    "lastName":"doe",
    "age": 26,
    "adress":{
              "streetAddress":"Kurtulus cad.",
              "city":"Ankara",
              "postalCode":"06100"
    },

    "phoneNumbers":[{
              "number":"532-555 55 55",
              "type":"cep"
    },
    {
              "number":"0312-123 4567",
              "type":"ev"}]
      }

     */


    @Test
    public void jsonPath01(){

        JSONObject ceptelbilgisi=new JSONObject();
        ceptelbilgisi.put("number","532-555 55 55");
        ceptelbilgisi.put("type","cep");

        JSONObject evtelbilgisi=new JSONObject();
        evtelbilgisi.put("number","0312-123 4567");
        evtelbilgisi.put("type","ev");

        JSONArray telbilgisi=new JSONArray();
        telbilgisi.put(ceptelbilgisi);
        telbilgisi.put(evtelbilgisi);


        JSONObject adresbilgisi=new JSONObject();
        adresbilgisi.put("streetAddress","Kurtulus cad.");
        adresbilgisi.put("city","Ankara");
        adresbilgisi.put("postalCode","06100");

        JSONObject kisibilgisi=new JSONObject();
        kisibilgisi.put("firstName","John");
        kisibilgisi.put("lastName","doe");
        kisibilgisi.put("age",26);
        kisibilgisi.put("adress",adresbilgisi);
        kisibilgisi.put("phoneNumbers",telbilgisi);


        System.out.println("kisibilgisi : " + kisibilgisi);


        /*
        kisibilgisi :

        {"firstName":"John",
        "lastName":"doe",
        "adress":{
                 "streetAddress":"Kurtulus cad.",
                 "city":"Ankara",
                 "postalCode":"06100"},
        "age":26,
        "phoneNumbers":[{
                  "number":"532-555 55 55",
                  "type":"cep"
                  },
                 {
                 "number":"0312-123 4567",
                  "type":"ev"}
                  ]}
         */


        System.out.println("isim : " +kisibilgisi.get("firstName"));
        System.out.println("soyisim : " +kisibilgisi.get("lastName"));
        System.out.println("yas : " +kisibilgisi.get("age"));
        System.out.println("cadde ismi : " +kisibilgisi.getJSONObject("adress").get("streetAddress"));
        System.out.println("sehir : " +kisibilgisi.getJSONObject("adress").get("city"));
        System.out.println("posta kodu : " +kisibilgisi.getJSONObject("adress").get("postalCode"));
        System.out.println("cep tel : " +kisibilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("number"));
        System.out.println("cep type : " +kisibilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("type"));
        System.out.println("ev tel : " +kisibilgisi.getJSONArray("phoneNumbers").getJSONObject(1).get("number"));
        System.out.println("ev type : " +kisibilgisi.getJSONArray("phoneNumbers").getJSONObject(1).get("type"));







    }
}
