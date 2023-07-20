package org.example.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.properties.JobBoardProperties;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreatePostingTest {

    @Test
    void createPostingTest() {

        JSONObject company = new JSONObject();
        company.put("name", "Valve");
        company.put("contact", "gaben@valve.com");
        company.put("website", "https://valve.com");

        JSONObject jobPosting = new JSONObject();
        jobPosting.put("description", "job from Java");
        jobPosting.put("salary", 8000);
        jobPosting.put("expiresAt", "2024-08-01");
        jobPosting.put("company", company);

        final Response response = given()
                .contentType(ContentType.JSON)
                .body(jobPosting.toString())
                .when()
                .post(JobBoardProperties.getBaseUrl())
                .then()
                .log().ifError()
                .extract()
                .response();

        Assertions.assertThat(response.statusCode()).isEqualTo(201);
        Assertions.assertThat(response.jsonPath().getString("company.name")).isEqualTo("Valve");

    }
}
