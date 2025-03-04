package com.example.latihanapi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("latihan/login")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    // register api here
    @FormUrlEncoded
    @POST("latihan/register-user")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("password") String password,
            @Field("full_name") String fullName,
            @Field("email") String email
    );

}
