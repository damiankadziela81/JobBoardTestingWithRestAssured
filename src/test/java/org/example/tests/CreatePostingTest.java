package org.example.tests;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.CreatePostingRequest;
import org.example.requests.DeletePostingRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

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

        final Response response = CreatePostingRequest.createPosting(jobPosting);

        Assertions.assertThat(response.statusCode()).isEqualTo(201);
        Assertions.assertThat(response.jsonPath().getString("company.name")).isEqualTo("Valve");

        final var postingId = response.jsonPath().getString("id");
        final Response deleteResponse = DeletePostingRequest.deletePosting(postingId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(204);
        Assertions.assertThat(deleteResponse.asString()).isEmpty();

    }
}
