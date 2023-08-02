package org.example.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.properties.JobBoardProperties;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreatePostingRequest {

    public static Response createPosting(JSONObject jobPosting) {
        return given()
                .contentType(ContentType.JSON)
                .body(jobPosting.toString())
                .when()
                .post(JobBoardProperties.getBaseUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
