package apiTest;

import api.Endpoint;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.User;

import static io.restassured.RestAssured.given;

public class TestScript {

    BaseClass base = new BaseClass();

    @Test
    public void fullCrudFlow() {

        // ======================
        // 1️⃣ POST - CREATE
        // ======================
        User user = new User("morpheus", "leader");

        Response postRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(user)
                        .when()
                        .post(Endpoint.POST_USER);

        System.out.println("POST RESPONSE:");
        System.out.println(postRes.asString());

        // ======================
        // 2️⃣ GET - READ
        // ======================
        Response getRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .get(Endpoint.GET_USER);

        System.out.println("GET RESPONSE:");
        System.out.println(getRes.asString());

        // ======================
        // 3️⃣ PUT - FULL UPDATE
        // ======================
        User putUser = new User("neo", "zion resident");

        Response putRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(putUser)
                        .when()
                        .put(Endpoint.PUT_USER);

        System.out.println("PUT RESPONSE:");
        System.out.println(putRes.asString());

        // ======================
        // 4️⃣ PATCH - PARTIAL UPDATE
        // ======================
        User patchUser = new User();
        patchUser.setJob("QA Lead");

        Response patchRes =
                given()
                        .spec(base.getRequestSpec())
                        .body(patchUser)
                        .when()
                        .patch(Endpoint.PATCH_USER);

        System.out.println("PATCH RESPONSE:");
        System.out.println(patchRes.asString());

        // ======================
        // 5️⃣ DELETE
        // ======================
        Response deleteRes =
                given()
                        .spec(base.getRequestSpec())
                        .when()
                        .delete(Endpoint.DELETE_USER);

        System.out.println("DELETE STATUS:");
        System.out.println(deleteRes.statusCode());
    }
}