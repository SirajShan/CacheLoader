package mindvalley.com.resource_download_caching_loader.net.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.UnsupportedEncodingException;

/**
 * @author Sirajuddin
 * @purpose
 * @since 02-09-2016
 */

public  class Converters {

    public Bitmap ConvertBytestoBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public String ConvertBytestoUtf8(byte[] bytes){
        try {
            String str = new String(bytes, "UTF-8");
            return str;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}