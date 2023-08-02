package org.example.requests;

import io.restassured.response.Response;
import org.example.url.JobBoardUrl;

import static io.restassured.RestAssured.given;

public class ReadSinglePostingRequest {

    public static Response readPosting(String id) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .get(JobBoardUrl.getPostingUrl(id))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
