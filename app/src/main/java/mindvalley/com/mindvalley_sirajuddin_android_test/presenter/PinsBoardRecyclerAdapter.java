package mindvalley.com.mindvalley_sirajuddin_android_test.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import mindvalley.com.mindvalley_sirajuddin_android_test.R;
import mindvalley.com.mindvalley_sirajuddin_android_test.model.Category;
import mindvalley.com.mindvalley_sirajuddin_android_test.model.UserInfo;
import mindvalley.com.resource_download_caching_loader.net.api.ResourceHandlerInterface;
import mindvalley.com.resource_download_caching_loader.net.manager.DownloadManager;
import mindvalley.com.resource_download_caching_loader.net.manager.LocalStorageManager;

/**
 * @author Sirajuddin
 * @purpose
 * @since 03-09-2016
 */

public class PinsBoardRecyclerAdapter extends RecyclerView.Adapter<PinsBoardRecyclerAdapter.PinViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private List<UserInfo> boards;
    private Context _Context;
    private DownloadPresenter downloadPresenter;
    private int lastPosition = -1;

    public PinsBoardRecyclerAdapter(Context _context, List<UserInfo> boards, DownloadPresenter downloadPresenter) {
        this._Context = _context;
        this.boards = boards;
        this.downloadPresenter = new DownloadPresenter(_context);
    }

    @Override
    public PinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _Item = LayoutInflater.from(parent.getContext()).inflate(R.layout.resource_item_layout, parent, false);
        return new PinViewHolder(_Item);
    }

    private void updatePinImage(PinViewHolder holder, byte[] bytes) {
        holder.btnCancel.setVisibility(View.GONE);
        Bitmap bitmap = LocalStorageManager.getInstance().getConverters().ConvertBytestoBitmap(bytes);
        holder.pinImage.setImageBitmap(bitmap);
    }

    private void updateProfilePic(PinViewHolder holder, byte[] bytes) {
        Bitmap bitmap = LocalStorageManager.getInstance().getConverters().ConvertBytestoBitmap(bytes);
        holder.profileImage.setImageBitmap(bitmap);
    }

    @Override
    public void onBindViewHolder(final PinViewHolder holder, int position) {
        UserInfo board = this.boards.get(position);
        int totalPics = 0;
        if (board.getUser() != null) {
            holder.userName.setText("@" + board.getUser().getUsername());
            holder.fullName.setText(board.getUser().getName());
            holder.userName2.setText(board.getUser().getUsername().length() > 10 ? (board.getUser().getUsername().substring(0, 10)) + "..."
                    : board.getUser().getUsername());
        }

        DownloadManager.getInstance().grabResource(board.getUrls().getSmall(), new ResourceHandlerInterface() {
            @Override
            public void downLoadedResource(byte[] bytes) {
                updatePinImage(holder, bytes);
            }


        }, holder.btnCancel);

        downloadPresenter.grabResource(board.getUser().getProfileImage().getSmall(), new ResourceHandlerInterface() {
            @Override
            public void downLoadedResource(byte[] bytes) {
                updateProfilePic(holder, bytes);
            }
        });
        holder.selfLiked.setText(board.getLikedByUser() ? "Yes" : "No");
        holder.totalLikes.setText(String.valueOf(board.getLikes()));

        if (board.getCategories() != null) {
            for (Category category : board.getCategories()) {
                totalPics += category.getPhotoCount();
            }
        }

        holder.totalPics.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.ENGLISH).format(totalPics)));
        holder.itemView.setTag(board);
    }

    @Override
    public int getItemCount() {
        return this.boards.size();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(_Context, "Long press triggered", Toast.LENGTH_LONG).show();
        return true;
    }

    public class PinViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView userName;
        public TextView fullName;
        public ImageView pinImage;
        public TextView totalPics;
        public TextView totalLikes;
        public TextView selfLiked;
        public TextView userName2;
        public CircleImageView profileImage;
        public ImageButton btnCancel;

        public PinViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            userName = (TextView) itemView.findViewById(R.id.pin_item_userName);
            fullName = (TextView) itemView.findViewById(R.id.pin_item_fullName);
            pinImage = (ImageView) itemView.findViewById(R.id.pin_item_image);
            totalPics = (TextView) itemView.findViewById(R.id.pin_item_totalPics);
            totalLikes = (TextView) itemView.findViewById(R.id.pin_item_likes);
            selfLiked = (TextView) itemView.findViewById(R.id.pin_item_self_liked);
            userName2 = (TextView) itemView.findViewById(R.id.pin_item_userName2);
            profileImage = (CircleImageView) itemView.findViewById(R.id.imgAuthorPic);
            btnCancel = (ImageButton) itemView.findViewById(R.id.btn_cancel);
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(v.getContext(), "Long clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
