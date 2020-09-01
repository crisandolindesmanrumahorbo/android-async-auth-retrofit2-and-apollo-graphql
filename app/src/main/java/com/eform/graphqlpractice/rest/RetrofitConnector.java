package com.eform.graphqlpractice.rest;

import com.eform.graphqlpractice.constant.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnector {

    public static Retrofit setupRetrofit(final String token) {
        OkHttpClient client;
        if (token.equals("")) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request original = chain.request()
                                    .newBuilder()
                                    .build();
                            return chain.proceed(original);
                        }
                    })
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NotNull
                        @Override
                        public Response intercept(@NotNull Chain chain) throws IOException {
                            Request original = chain.request()
                                    .newBuilder()
                                    .addHeader("Authorization", token)
                                    .build();
                            return chain.proceed(original);
                        }
                    })
                    .build();
        }

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
