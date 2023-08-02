package org.example.requests;

import io.restassured.response.Response;
import org.example.url.JobBoardUrl;

import static io.restassured.RestAssured.given;

public class DeletePostingRequest {

    public static Response deletePosting(String id) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .when()
                .delete(JobBoardUrl.getPostingUrl(id))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
