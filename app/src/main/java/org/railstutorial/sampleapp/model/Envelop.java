package org.railstutorial.sampleapp.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @param <Data>
 */
public class Envelop<Data> {

    public Meta meta;

    public Data data;

    public Pagination pagination;

    public static class Meta {
        public int code;

        @SerializedName("error_type")
        public String errorType;

        @SerializedName("error_message")
        public String errorMessage;
    }

    public static class Pagination {
        @SerializedName("next_url")
        public String nextUrl;
    }

}
