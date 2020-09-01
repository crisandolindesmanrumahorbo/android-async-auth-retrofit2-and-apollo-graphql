package com.eform.graphqlpractice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.eform.graphqlpractice.model.LoginRequest;
import com.eform.graphqlpractice.model.LoginResponse;
import com.eform.graphqlpractice.model.User;
import com.eform.graphqlpractice.rest.APIInterface;
import com.eform.graphqlpractice.rest.ApolloConnector;
import com.eform.graphqlpractice.rest.RetrofitConnector;
import com.eform.graphqlpractice.type.VehicleWrapper;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "token";
    private final String TAG = "MainActivity";
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mutationVehicle();
//        queryVehicle();
        login();
        getUser();
    }

    private void login() {
        apiInterface = RetrofitConnector.setupRetrofit("").create(APIInterface.class);
        LoginRequest loginRequest = new LoginRequest
                ("arief@dowith.com",
                        "qwertyui");
        Call<LoginResponse> call = apiInterface.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                SharedPreferences sharedPreferences = getSharedPreferences(
                        "MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("token", loginResponse.getType() + " " + loginResponse.getToken());
                myEdit.apply();

                Log.d(TAG, "response login " + loginResponse.getToken()
                        + " " + loginResponse.getType());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void getUser() {
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String token = sh.getString("token", "");

        apiInterface = RetrofitConnector.setupRetrofit(token).create(APIInterface.class);
        Call<User> call = apiInterface.doGetUser();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                User user = response.body();
                Log.d(TAG, "get user " + user.getId() + " "
                        + user.getName() + " " + user.getEmail() + " " + user.getPhone());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void mutationVehicle() {
        VehicleWrapper vehicleWrapper = VehicleWrapper.builder()
                .type("sport")
                .modelCode("3S")
                .brandName("Ferrari")
                .build();
        ApolloConnector.setupApollo().mutate(
                CreateVehicleMutation.builder()
                        .vehicle(vehicleWrapper)
                        .build()
        ).enqueue(new ApolloCall.Callback<CreateVehicleMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateVehicleMutation.Data> response) {
                Log.d(TAG, "response mutation " + response.getData().createVehicle().type());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.d(TAG, "Exception Mutation " + e.getMessage(), e);
            }
        });
    }

    private void queryVehicle() {
        ApolloConnector.setupApollo().query(
                VehicleQuery.builder()
                        .id(1)
                        .build())
                .enqueue(new ApolloCall.Callback<VehicleQuery.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<VehicleQuery.Data> response) {
                        Log.d(TAG, "response query " + response.getData().vehicle().modelCode());
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.d(TAG, "Exception Query " + e.getMessage(), e);
                    }
                });
    }
}