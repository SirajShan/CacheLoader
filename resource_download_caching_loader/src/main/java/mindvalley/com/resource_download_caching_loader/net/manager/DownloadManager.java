package mindvalley.com.resource_download_caching_loader.net.manager;

import android.util.Log;
import android.view.View;

import mindvalley.com.resource_download_caching_loader.net.RetrofitApi;
import mindvalley.com.resource_download_caching_loader.net.api.DownloadResourceApiInterface;
import mindvalley.com.resource_download_caching_loader.net.api.ResourceHandlerInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class DownloadManager {
    private static DownloadManager instance;
    static LocalStorageManager localStorageManager;

    public DownloadManager() {
    }

    public static DownloadManager getInstance() {
        if (instance == null) {
            localStorageManager = LocalStorageManager.getInstance();
            return instance = new DownloadManager();
        }
        return instance;
    }

    public Call grabResourceFromRemote(String uri, final ResourceHandlerInterface handler, View cancel) {
        RetrofitApi builder = new RetrofitApi();
        DownloadResourceApiInterface api = builder.createService(DownloadResourceApiInterface.class);
        final Call<ResponseBody> call = api.downloadResource(uri);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                byte[] bytes = localStorageManager.updateCache(call, response);
                handler.downLoadedResource(bytes);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
        if (cancel != null) {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call.cancel();
                }
            });
        }
        return call;
    }

    public void grabResource(String uri, final ResourceHandlerInterface handler, View cancel) {
        byte[] bytes = localStorageManager.grabResourceFromCache(uri);
        if (bytes != null) {
            handler.downLoadedResource(bytes);
            return;
        }
        grabResourceFromRemote(uri, handler, cancel);
    }

    public void grabResource(int resource, final ResourceHandlerInterface handler, View cancel) {
        byte[] bytes= localStorageManager.grabResourceFromCache(String.valueOf(resource));
        if(bytes!=null) {
            handler.downLoadedResource(bytes);
            return;
        }
    }

}
