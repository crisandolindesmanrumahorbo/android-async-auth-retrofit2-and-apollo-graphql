package com.eform.graphqlpractice.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("type")
    private String type;
}
