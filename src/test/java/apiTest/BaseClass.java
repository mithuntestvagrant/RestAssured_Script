package apiTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

    public RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api")
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "pub_00a9c1aeeb5f03ef1d8b19ec3d8e728a94d31532782f7b1e68f5e86d0bc75b20")
                .build();
    }
}