package org.example.properties;

import java.util.ResourceBundle;

public class JobBoardProperties {

    public static final String BASE_URL = "jobboard.baseurl";

    public static String getBaseUrl() {
        return getProperty(BASE_URL);
    }

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("jobboard").getString(key);
    }
}
