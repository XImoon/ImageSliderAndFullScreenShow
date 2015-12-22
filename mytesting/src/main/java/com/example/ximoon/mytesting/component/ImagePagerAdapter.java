package com.example.ximoon.mytesting.component;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ximoon.mytesting.ConstantUtils;
import com.example.ximoon.mytesting.LayoutVisibilityListener;
import com.example.ximoon.mytesting.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;
import java.util.ArrayList;

/***
 * 查看大图适配器
 *
 * @author tony.liu
 */
public class ImagePagerAdapter extends PagerAdapter {

    private LayoutVisibilityListener listener;
    private Picasso mPicasso;
    private Context context;
    private ArrayList<String> photos;
    private final String TAG = getClass().getCanonicalName();

    public ImagePagerAdapter(Context context, ArrayList<String> photos) {
        this.context = context;
        this.photos = photos;
        mPicasso = Picasso.with(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        // TODO
    }

    @Override
    public int getCount() {
        if (photos != null) {
            return photos.size();
        }
        return 0;
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        View imageLayout = LayoutInflater.from(context).inflate(R.layout.row_common_image_pager_layout, null);
        final PhotoView loadView = (PhotoView) imageLayout.findViewById(R.id.image);
        final ProgressBar loadingBar = (ProgressBar) imageLayout.findViewById(R.id.loading);
        String url = photos.get(position);
        RequestCreator requestCreator = mPicasso.load(url).error(R.mipmap.bg_picutre_show).placeholder(R.mipmap.bg_picutre_show);
        requestCreator.noFade();
        requestCreator.into(loadView, new Callback() {
            @Override
            public void onSuccess() {
                loadingBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                loadingBar.setVisibility(View.GONE);
            }
        });
        loadView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                                           @Override
                                           public void onPhotoTap(View view, float x, float y) {
                                               listener.layoutVisibility();
                                           }
                                       }

        );

        view.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public int getItemPosition(Object object) {
        if (photos.size() > 0) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    public ArrayList<String> getImages() {
        return photos;
    }

    public void setOnLayoutVisibilityListener(LayoutVisibilityListener listener) {
        this.listener = listener;
    }

}
