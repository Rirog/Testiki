package client;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class TokenInterceptor implements Interceptor {
    private final String apikey;

    public TokenInterceptor(String apikey) {
        this.apikey = apikey;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("x-api-key", apikey)
                .build();
        return chain.proceed(request);
    }
}
