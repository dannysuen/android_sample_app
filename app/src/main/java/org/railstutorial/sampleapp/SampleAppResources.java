package org.railstutorial.sampleapp;


public class SampleAppResources {

    public static String getString(int resId) {
        return SampleAppApplication.getApplication().getString(resId);
    }

}
