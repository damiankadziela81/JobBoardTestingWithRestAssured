package org.example.url;

public class JobBoardUrl {

    private static final String POSTINGS = "/postings";

    public static String getPostingsUrl () {
        return POSTINGS;
    }

    public static String getPostingUrl (String id) {
        return POSTINGS + "/" + id;
    }
}
