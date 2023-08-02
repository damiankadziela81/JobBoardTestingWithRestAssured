package org.example.requests;

import io.restassured.response.Response;
import org.example.url.JobBoardUrl;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UpdatePostingRequest {

    public static Response updatePosting(JSONObject jobPosting) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(jobPosting.toString())
                .when()
                .put(JobBoardUrl.getPostingsUrl())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
