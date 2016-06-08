package org.railstutorial.sampleapp.model;

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
    }

    public static class Pagination {
        String nextUrl;
    }

}
