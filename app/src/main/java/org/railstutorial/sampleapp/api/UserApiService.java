package org.railstutorial.sampleapp.api;

import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by danny on 16-6-8.
 */
public interface UserApiService {

    @FormUrlEncoded
    @POST("users")
    Call<Envelop<User>> signup(@Field("user[name]") String name,
                               @Field("user[email]") String email,
                               @Field("user[password]") String password,
                               @Field("user[password_confirmation]") String confirmationPassword);


    @GET("users")
    Call<Envelop<List<User>>> fetchUsers(@Query("page") int page);

    @FormUrlEncoded
    @PATCH("users/{id}")
    Call<Envelop<User>> update(@Path("id") long userId,
                               @Field("user[name]") String name,
                               @Field("user[email]") String email,
                               @Field("user[password]") String password,
                               @Field("user[password_confirmation]") String confirmationPassword);

}
