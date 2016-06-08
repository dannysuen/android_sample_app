package org.railstutorial.sampleapp.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class User {

    public long id;
    public String name;
    public String email;

    @SerializedName("gravatar_url")
    public String gravatarUrl;

}
