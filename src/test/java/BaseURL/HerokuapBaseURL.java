package BaseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuapBaseURL {

    // https://restful-booker.herokuapp.com


    protected RequestSpecification specHerokApp;

    @Before
    public void setUp(){

        specHerokApp =new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();

    }

}
