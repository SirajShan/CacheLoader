package mindvalley.com.mindvalley_sirajuddin_android_test.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import mindvalley.com.mindvalley_sirajuddin_android_test.R;
import mindvalley.com.mindvalley_sirajuddin_android_test.model.UserInfo;
import mindvalley.com.mindvalley_sirajuddin_android_test.presenter.DownloadPresenter;
import mindvalley.com.mindvalley_sirajuddin_android_test.presenter.PinsBoardRecyclerAdapter;
import mindvalley.com.mindvalley_sirajuddin_android_test.util.AppConstants;
import mindvalley.com.resource_download_caching_loader.net.api.ResourceHandlerInterface;
import mindvalley.com.resource_download_caching_loader.net.manager.LocalStorageManager;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class PinsBoardActivity extends MasterActivity
         {
    private SwipeRefreshLayout swipeContainer;
    private DownloadPresenter downloadPresenter;
    public PinsBoardActivity(){
        super(R.layout.activity_pins);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadPresenter =new DownloadPresenter(PinsBoardActivity.this);
        initializeComponents();
        initializePins();
    }

    private void initializePins() {

        downloadPresenter.grabResource(AppConstants.downLoadUrl, new ResourceHandlerInterface() {
            @Override
            public void downLoadedResource(byte[] bytes) {
                     List<UserInfo> resourceList = downloadPresenter.deserializeToBoards(LocalStorageManager
                             .getInstance()
                             .getConverters().ConvertBytestoUtf8(bytes));
                      BindRecycler(resourceList);
            }
        });

    }

    private void BindRecycler(List<UserInfo> resourceList) {
        if(resourceList !=null&& resourceList.size()>0){
            PinBoardEmptyView rvGrid= (PinBoardEmptyView) findViewById(R.id.rvPins);
            rvGrid.setHasFixedSize(true);
            StaggeredGridLayoutManager _staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
            rvGrid.setLayoutManager(_staggeredGridLayoutManager);
            rvGrid.setEmptyView(findViewById(R.id.list_empty));
            PinsBoardRecyclerAdapter rcAdapter = new PinsBoardRecyclerAdapter(getApplicationContext(), resourceList,downloadPresenter);
            rvGrid.setAdapter(rcAdapter);
            if(swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);
        }
    }

    private void initializeComponents() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initializePins();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}
