package tests.steps;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public abstract class BaseSteps {
    protected final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    protected final String token = System.getProperty("API_KEY");
    protected final String tokenAdmin = System.getProperty("TOKEN_ADMIN3");
    protected final String tokenPublic = System.getProperty("TOKEN_PUBLIC3");

}
