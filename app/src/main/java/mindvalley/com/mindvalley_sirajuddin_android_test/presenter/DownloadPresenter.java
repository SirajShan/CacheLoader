package mindvalley.com.mindvalley_sirajuddin_android_test.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import mindvalley.com.mindvalley_sirajuddin_android_test.model.UserInfo;
import mindvalley.com.resource_download_caching_loader.net.api.ResourceHandlerInterface;
import mindvalley.com.resource_download_caching_loader.net.manager.DownloadManager;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class DownloadPresenter {
    private Context context;
    private Gson gson;
    DownloadManager downloadManager;
    public DownloadPresenter(Context context) {
        this.context= context;
        this.gson=new Gson();
        downloadManager=DownloadManager.getInstance();
    }
    public void grabResource(String uri, final ResourceHandlerInterface handler) {
        downloadManager.grabResource(uri,handler,null);
    }

    public List<UserInfo> deserializeToBoards(String serialized){
        Type type = new TypeToken<List<UserInfo>>() {
        }.getType();
        return  gson.fromJson(serialized, type);
    }
}