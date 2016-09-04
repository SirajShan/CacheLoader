package mindvalley.com.resource_download_caching_loader.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class RetrofitApi {
    private static Retrofit.Builder builder;
    public static final String API_BASE_URL = "http://your.api-base.url";
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public RetrofitApi() {
        builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
