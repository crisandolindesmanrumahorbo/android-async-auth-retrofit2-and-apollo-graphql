package com.eform.graphqlpractice.rest;

import com.eform.graphqlpractice.model.LoginRequest;
import com.eform.graphqlpractice.model.LoginResponse;
import com.eform.graphqlpractice.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("/users/1")
    Call<User> doGetUser();

    @POST("/authentication/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
