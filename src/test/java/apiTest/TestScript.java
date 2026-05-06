package apiTest;

import api.Endpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UserRequest;
import pojo.UserResponse;

import static io.restassured.RestAssured.given;

public class TestScript {

    BaseClass base = new BaseClass();

    String userId;

    // ======================
    // 1️⃣ POST
    // ======================
    @Test(priority = 1)
    public void createUserTest() {

        UserRequest user = new UserRequest("morpheus", "leader");

        Response postRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(user)
                        .when()
                        .post(Endpoint.POST_USER);

        Assert.assertEquals(postRes.statusCode(), 201);

        UserResponse postResponse = postRes.as(UserResponse.class);

        System.out.println("POST ID: " + postResponse.getId());

        Assert.assertEquals(postResponse.getName(), "morpheus");
        Assert.assertEquals(postResponse.getJob(), "leader");
        Assert.assertNotNull(postResponse.getId());

        userId = postResponse.getId();
    }

    // ======================
    // 2️⃣ GET
    // ======================
    @Test(priority = 2, dependsOnMethods = "createUserTest")
    public void getUserTest() {

        Response getRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .get(Endpoint.GET_USER);

        Assert.assertEquals(getRes.statusCode(), 200);

        System.out.println("GET RESPONSE: " + getRes.asString());

        Assert.assertNotNull(getRes.jsonPath().getString("data.id"));
    }

    // ======================
    // 3️⃣ PUT
    // ======================
    @Test(priority = 3, dependsOnMethods = "createUserTest")
    public void putUserTest() {

        UserRequest putUser = new UserRequest("neo", "zion resident");

        Response putRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(putUser)
                        .when()
                        .put(Endpoint.PUT_USER);

        Assert.assertEquals(putRes.statusCode(), 200);

        UserResponse putResponse = putRes.as(UserResponse.class);

        Assert.assertEquals(putResponse.getName(), "neo");
        Assert.assertEquals(putResponse.getJob(), "zion resident");
    }

    // ======================
    // 4️⃣ PATCH
    // ======================
    @Test(priority = 4)
    public void patchUserTest() {

        UserRequest patchUser = new UserRequest();
        patchUser.setJob("QA Lead");

        Response patchRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(patchUser)
                        .when()
                        .patch(Endpoint.PATCH_USER);

        Assert.assertEquals(patchRes.statusCode(), 200);

        UserResponse patchResponse = patchRes.as(UserResponse.class);

        Assert.assertEquals(patchResponse.getJob(), "QA Lead");
    }

    // ======================
    // 5️⃣ DELETE
    // ======================
    @Test(priority = 5)
    public void deleteUserTest() {

        Response deleteRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .delete(Endpoint.DELETE_USER);

        Assert.assertTrue(
                deleteRes.statusCode() == 200 || deleteRes.statusCode() == 204
        );
    }
}