package mindvalley.com.resource_download_caching_loader.net.model;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class ResourceInfo {
    public String uri;
    public String contentType;

    public ResourceInfo(String uri, String contentType){
        this.uri=uri;
        this.contentType=contentType;
    }
}
