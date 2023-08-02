package org.example.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.properties.JobBoardProperties;

public class BaseRequest {

    private static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSpec () {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(JobBoardProperties.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        return requestBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs () {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(JobBoardProperties.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        return requestBuilder.build();
    }
}
