package com.example.ximoon.mytesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;

/**
 * Created by ximoon on 15/12/22.
 */
public class ImageSliderView extends BaseSliderView {

    protected ImageSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.content_main,null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ConstantUtils.DISPLAYW,ConstantUtils.DISPLAYW));
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        bindEventAndShow(view,imageView);
        return view;
    }
}
