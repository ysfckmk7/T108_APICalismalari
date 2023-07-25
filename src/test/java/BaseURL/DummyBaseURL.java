package BaseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyBaseURL {
    // https://dummy.restapiexample.com


    protected RequestSpecification specDummy;

    @Before
    public void setUp(){

        specDummy =new RequestSpecBuilder()
                .setBaseUri("https://dummy.restapiexample.com")
                .build();

    }
}
