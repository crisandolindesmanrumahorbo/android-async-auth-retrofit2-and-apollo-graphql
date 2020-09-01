package com.eform.graphqlpractice.rest;

import com.apollographql.apollo.ApolloClient;
import com.eform.graphqlpractice.constant.Constant;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApolloConnector {

    public static ApolloClient setupApollo() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request original = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", Constant.TOKEN)
                                .build();
                        return chain.proceed(original);
                    }
                })
                .build();

        return ApolloClient.builder()
                .serverUrl(Constant.GRAPHQL_URL)
                .okHttpClient(okHttpClient)
                .build();
    }

}
