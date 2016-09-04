package mindvalley.com.mindvalley_sirajuddin_android_test.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class PinBoardEmptyView extends RecyclerView {
    private View emptyView;
    Animation animFadeOut;
    Animation animFadeIn;

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {


        @Override
        public void onChanged() {
                    emptyView.setVisibility(View.GONE);
                    PinBoardEmptyView.this.setAnimation(animFadeIn);
                    PinBoardEmptyView.this.setVisibility(View.VISIBLE);
        }
    };

    public PinBoardEmptyView(Context context) {
        super(context);
    }

    public PinBoardEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PinBoardEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if(adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }
}