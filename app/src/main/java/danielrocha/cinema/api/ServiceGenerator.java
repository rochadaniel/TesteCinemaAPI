package danielrocha.cinema.api;

import danielrocha.cinema.BuildConfig;
import danielrocha.cinema.utils.OkHttpHelper;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by danielrocha on 01/08/16.
 */
public class ServiceGenerator {
    public static final String API_BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static Retrofit retrofit;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());


    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient client = OkHttpHelper.getHttpClient();
        retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

/*    public static <S> S createService(Class<S> serviceClass, String token) {
        OkHttpClient client = OkHttpHelper.getHttpClient(token);
        retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }*/

    public static Retrofit getRetrofit() {
        if(retrofit == null) {
            OkHttpClient client = httpClient.build();
            return builder.client(client).build();
        } else return retrofit;
    }
}
