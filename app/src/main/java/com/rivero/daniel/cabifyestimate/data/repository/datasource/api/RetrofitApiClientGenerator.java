package com.rivero.daniel.cabifyestimate.data.repository.datasource.api;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rivero.daniel.cabifyestimate.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RetrofitApiClientGenerator implements ApiClientGenerator {

    private Retrofit retrofit;

    @Inject
    public RetrofitApiClientGenerator() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.cabify_base_url)
                .addConverterFactory(jsonConverterFactory())
                .client(buildHttpClient());

        retrofit = builder.build();
    }

    @NonNull
    private GsonConverterFactory jsonConverterFactory() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        return GsonConverterFactory.create(gson);
    }

    private OkHttpClient buildHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(buildHeaderInterceptor())
                .addInterceptor(httpLoggingInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        return httpClientBuilder.build();
    }

    private Interceptor buildHeaderInterceptor() {
        return chain -> {
            Request.Builder requestBuilder = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", BuildConfig.cabify_auth_token)
                    .addHeader("Accept-Language", "en");

            return chain.proceed(requestBuilder.build());
        };
    }

    private Interceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    @Override
    public <T> T generateApi(Class<T> service) {
        return retrofit.create(service);
    }
}
