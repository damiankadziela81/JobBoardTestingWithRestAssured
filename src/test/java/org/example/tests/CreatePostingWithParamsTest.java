package org.example.tests;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.CreatePostingRequest;
import org.example.requests.DeletePostingRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

class CreatePostingWithParamsTest {

    @ParameterizedTest(name = "Create posting with description: {0}")
    @DisplayName("Create posting with valid description")
    @MethodSource("createPostingData")
    void createPostingWithParamsTest(String postingDescription, BigDecimal salary) {

        JSONObject company = new JSONObject();
        company.put("name", "Valve");
        company.put("contact", "gaben@valve.com");
        company.put("website", "https://valve.com");

        JSONObject jobPosting = new JSONObject();
        jobPosting.put("description", postingDescription);
        jobPosting.put("salary", salary);
        jobPosting.put("expiresAt", "2024-08-01");
        jobPosting.put("company", company);

        final Response response = CreatePostingRequest.createPosting(jobPosting);

        Assertions.assertThat(response.statusCode()).isEqualTo(201);
        Assertions.assertThat(response.jsonPath().getString("description")).isEqualTo(postingDescription);
        Assertions.assertThat(response.jsonPath().getString("salary")).isEqualTo(salary.toString());

        final var postingId = response.jsonPath().getString("id");
        final Response deleteResponse = DeletePostingRequest.deletePosting(postingId);
        Assertions.assertThat(deleteResponse.statusCode()).isEqualTo(204);
        Assertions.assertThat(deleteResponse.asString()).isEmpty();

    }

    private static Stream<Arguments> createPostingData() {
        return Stream.of(
                Arguments.of("TEST DESCRIPTION", new BigDecimal("8000.01")),
                Arguments.of("123", new BigDecimal("6666.66")),
                Arguments.of("*", new BigDecimal("12000"))
        );
    }
}
