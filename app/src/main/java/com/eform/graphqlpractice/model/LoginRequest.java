package com.eform.graphqlpractice.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginRequest {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;


}
