package org.railstutorial.sampleapp.api;

import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by danny on 16-6-8.
 */
public interface UserApiService {

    @FormUrlEncoded
    @POST("api/v1/sessions")
    Call<Envelop<User>> signup(@Field("user[name]") String name,
                               @Field("user[email]") String email,
                               @Field("user[password]") String password,
                               @Field("user[confirmation_password") String confirmationPassword);

}
