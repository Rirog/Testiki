package tests.steps;

import client.TokenInterceptor;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public abstract class BaseSteps {

    protected final String token = System.getProperty("API_KEY");
    protected final String tokenAdmin = System.getProperty("TOKEN_ADMIN2");
    protected final String tokenPublic = System.getProperty("TOKEN_PUBLIC2");


    protected <T> T createRetrofit(Class<T> service, String apiKey) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(new TokenInterceptor(apiKey))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        
        return retrofit.create(service);
    }
}
