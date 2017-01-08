package mu29.legotv.data.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * InJung Chung
 */

class HeaderInterceptor implements Interceptor {

    private String token;

    HeaderInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("token", token == null ? "" : token)
            .method(original.method(), original.body())
            .build();

        return chain.proceed(request);
    }

}
