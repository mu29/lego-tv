package mu29.legotv.data.remote;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * InJung Chung
 */

public interface RestClient {

    class Factory {
        private static RestClient instance;

        private static void create() {
            create(null);
        }

        public static void create(String token) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HeaderInterceptor(token))
                .build();

            Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setLenient() // TODO : 서버가 JSON 타입으로 제대로 보내게 되면 지우자
                .create();

            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addCallAdapterFactory(new RxThreadCallAdapter(Schedulers.io(), AndroidSchedulers.mainThread()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

            instance = retrofit.create(RestClient.class);
        }

        public static synchronized RestClient getApi() {
            if (instance == null) {
                create();
            }
            return instance;
        }

        public static Map<String, String> buildParams(String[] paramName, String[] paramData) {
            Map<String, String> params = new HashMap<>();
            for (int i = 0; i < paramName.length; i++)
                params.put(paramName[i], paramData[i]);

            return params;
        }
    }
}
