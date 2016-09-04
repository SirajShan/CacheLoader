package mindvalley.com.resource_download_caching_loader.net.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public interface DownloadResourceApiInterface {
    @GET
    Call<ResponseBody> downloadResource(@Url String url);
}
