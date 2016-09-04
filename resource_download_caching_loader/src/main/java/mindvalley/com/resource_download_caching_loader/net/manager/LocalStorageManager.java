package mindvalley.com.resource_download_caching_loader.net.manager;

import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import mindvalley.com.resource_download_caching_loader.net.ApiConstants;
import mindvalley.com.resource_download_caching_loader.net.model.ResourceInfo;
import mindvalley.com.resource_download_caching_loader.net.util.Converters;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class LocalStorageManager {

    private static LocalStorageManager instance;
    private LruCache<String, byte[]> memoryCache;
    private static DownloadManager downloadManager;
    private Gson gson;

    public Converters getConverters() {
        return converters;
    }

    public void setConverters(Converters converters) {
        this.converters = converters;
    }

    private Converters converters;

    public LocalStorageManager() {
        gson = new Gson();
        this.converters=new Converters();
        initLruCache();
    }

    public void saveToMemoryCacheByKey(String key, byte[] bytes) {
        memoryCache.put(key, bytes);
    }

    public void saveToMemoryCache(String url, byte[] bytes) {
        saveToMemoryCacheByKey(url, bytes);
    }

    private void initLruCache() {
        memoryCache = new LruCache<String, byte[]>(ApiConstants.memory) {
        };
    }

    public byte[] updateCache(Call<ResponseBody> call, Response<ResponseBody> response) {
        byte[] bytes = null;
        String uri = call.request().url().toString();
        String contentType = response.headers().get("content-type");
        ResourceInfo scheme = new ResourceInfo(uri, contentType);
        String serializedScheme = gson.toJson(scheme);
        try {
            bytes = response.body().bytes();
            saveToMemoryCache(serializedScheme, bytes);
            Log.i("log", serializedScheme);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static LocalStorageManager getInstance() {
        if (instance == null) {
            return instance = new LocalStorageManager();
        }
        return instance;
    }

    public byte[] getFromMemoryCacheByKey(String key) {
        byte[] bytes = memoryCache.get(key);
        if (bytes != null) {
        } else {
        }
        return bytes;
    }

    public byte[] getFromMemoryCache(String url) {
        String key = findCacheKeyFromUri(url);
        if (TextUtils.isEmpty(key))
            return null;
        return getFromMemoryCacheByKey(key);
    }


    private String findCacheKeyFromUri(String uri) {
        Map<String, byte[]> snapshot = memoryCache.snapshot();
        String key = null;
        for (String id : snapshot.keySet()) {
            Type deserializeType = new TypeToken<ResourceInfo>() {
            }.getType();
            ResourceInfo deserialized = gson.fromJson(id, deserializeType);
            if (deserialized.uri.equals(uri)) {
                key = id;
                break;
            }
        }
        return key;
    }

    public byte[] grabResourceFromCache(String uri) {
        byte[] bytes = getFromMemoryCache(uri);
        return bytes;
    }

}
