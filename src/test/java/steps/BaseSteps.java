package steps;

import client.TokenInterceptor;
import config.ConfigManager;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.*;


public abstract class BaseSteps {
    protected final String token = ConfigManager.getApiKey();
    protected final String tokenAdmin = ConfigManager.getTokenAdmin();
    protected final String tokenPublic = ConfigManager.getTokenPublic();



    protected <T> T createRetrofit(Class<T> service, String apiKey) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new TokenInterceptor(apiKey))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigManager.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(service);
    }
}
