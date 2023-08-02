package org.example.tests;

import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.example.requests.CreatePostingRequest;
import org.example.requests.DeletePostingRequest;
import org.example.requests.ReadAllPostingsRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

class ReadPostingsTest {

    @Test
    void readPostingTest() {

        final String description1 = "job from Java 1";
        final String description2 = "job from Java 2";
        final String companyName1 = "Valve 1";
        final String companyName2 = "Valve 2";


        JSONObject company1 = new JSONObject();
        company1.put("name", companyName1);
        company1.put("contact", "gaben@valve.com");
        company1.put("website", "https://valve.com");

        JSONObject jobPosting1 = new JSONObject();
        jobPosting1.put("description", description1);
        jobPosting1.put("salary", 8000);
        jobPosting1.put("expiresAt", "2024-08-01");
        jobPosting1.put("company", company1);

        JSONObject company2 = new JSONObject();
        company2.put("name", companyName2);
        company2.put("contact", "gaben2@valve.com");
        company2.put("website", "https://valve2.com");

        JSONObject jobPosting2 = new JSONObject();
        jobPosting2.put("description", description2);
        jobPosting2.put("salary", 9000);
        jobPosting2.put("expiresAt", "2024-08-02");
        jobPosting2.put("company", company2);

        final Response response1 = CreatePostingRequest.createPosting(jobPosting1);
        final var postingId1 = response1.jsonPath().getString("id");

        final Response response2 = CreatePostingRequest.createPosting(jobPosting2);
        final var postingId2 = response2.jsonPath().getString("id");

        final Response readResponse = ReadAllPostingsRequest.readPostings();
        Assertions.assertThat(readResponse.statusCode()).isEqualTo(200);

        JSONArray jsonArray = new JSONArray(readResponse.asString());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.optString("id").equals(postingId1)) {
                Assertions.assertThat(jsonObject.getString("description")).isEqualTo(description1);
                Assertions.assertThat(jsonObject.getJSONObject("company").getString("name")).isEqualTo(companyName1);
            }
            if (jsonObject.optString("id").equals(postingId2)) {
                Assertions.assertThat(jsonObject.getString("description")).isEqualTo(description2);
                Assertions.assertThat(jsonObject.getJSONObject("company").getString("name")).isEqualTo(companyName2);
            }
        }

        //clean up
        final Response deleteResponse1 = DeletePostingRequest.deletePosting(postingId1);
        Assertions.assertThat(deleteResponse1.statusCode()).isEqualTo(204);
        Assertions.assertThat(deleteResponse1.asString()).isEmpty();

        final Response deleteResponse2 = DeletePostingRequest.deletePosting(postingId2);
        Assertions.assertThat(deleteResponse2.statusCode()).isEqualTo(204);
        Assertions.assertThat(deleteResponse2.asString()).isEmpty();

    }
}
