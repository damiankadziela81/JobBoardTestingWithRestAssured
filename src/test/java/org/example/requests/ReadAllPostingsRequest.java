package org.example.requests;

import io.restassured.response.Response;
import org.example.url.JobBoardUrl;

import static io.restassured.RestAssured.given;

public class ReadAllPostingsRequest {

    public static Response readPostings() {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .get(JobBoardUrl.getPostingsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
