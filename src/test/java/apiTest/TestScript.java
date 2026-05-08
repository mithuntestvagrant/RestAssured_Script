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

    @Test
    public void createGetUpdatePatchAndDeleteUser() {

        
        // POST - CREATE USER

        UserRequest user = new UserRequest("morpheus", "leader");

        Response postRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(user)
                        .when()
                        .post(Endpoint.POST_USER);

        Assert.assertEquals(postRes.statusCode(), 201);

        UserResponse postResponse = postRes.as(UserResponse.class);

        userId = postResponse.getId();

        System.out.println("Created User ID: " + userId);

        postRes.prettyPrint();

        System.out.println("POST (Create User) completed successfully");



        // GET - USER


        Response getRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .get(Endpoint.GET_USER);

        Assert.assertEquals(getRes.statusCode(), 200);

        getRes.prettyPrint();

        System.out.println("GET (Fetch User) completed successfully");



        // PUT - FULL UPDATE


        UserRequest putUser =
                new UserRequest("neo", "zion resident");

        Response putRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(putUser)
                        .when()
                        .put(Endpoint.PUT_USER);

        Assert.assertEquals(putRes.statusCode(), 200);

        putRes.prettyPrint();

        System.out.println("PUT (Full Update) completed successfully");



        // PATCH - PARTIAL UPDATE


        UserRequest patchUser = new UserRequest();
        patchUser.setJob("QA Lead");

        Response patchRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(patchUser)
                        .when()
                        .patch(Endpoint.PATCH_USER);

        Assert.assertEquals(patchRes.statusCode(), 200);

        patchRes.prettyPrint();

        System.out.println("PATCH (Partial Update) completed successfully");



        // DELETE - USER


        Response deleteRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .delete(Endpoint.DELETE_USER);

        Assert.assertTrue(
                deleteRes.statusCode() == 200 ||
                        deleteRes.statusCode() == 204
        );

        System.out.println("DELETE completed successfully");
    }
}