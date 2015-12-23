package com.example.ximoon.mytesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.BaseSliderView.OnSliderClickListener;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnSliderClickListener ,ViewPagerEx.OnPageChangeListener{

    private SliderLayout mLayout;
    private ArrayList<String> mUrls = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mLayout = (SliderLayout) findViewById(R.id.slider);
        mLayout.setLayoutParams(new LinearLayout.LayoutParams(ConstantUtils.DISPLAYW,ConstantUtils.DISPLAYW));
    }


    private void initData(){
        Picasso mPicasso = null;
        LruCache lruCache = new LruCache(getApplicationContext());
        lruCache.clear();
        Picasso.Builder builder = new Picasso.Builder(getApplicationContext()).memoryCache(lruCache);
        lruCache.clear();
        mPicasso = builder.build();
        mUrls.add("http://pic27.nipic.com/20130223/890845_100218871001_2.jpg");
        mUrls.add("http://pic8.nipic.com/20100719/668573_214559007865_2.jpg");
        mUrls.add("http://pic8.nipic.com/20100719/668573_214559054398_2.jpg");
        mUrls.add("http://b.hiphotos.baidu.com/zhidao/pic/item/7dd98d1001e93901c890284478ec54e736d1963e.jpg");
        mUrls.add("http://img.61gequ.com/allimg/2012-6/20126214121490973.jpg");
        for (int i = 0; i < mUrls.size(); i++){
            ImageSliderView imageSliderView = new ImageSliderView(this);
            //              加载图片（重载）        设置图片显示方式                                    单击事件                        设置加载器
            imageSliderView.image(mUrls.get(i)).setScaleType(BaseSliderView.ScaleType.CenterCrop).setOnSliderClickListener(this).setPicasso(mPicasso);
            // 传递参数
            imageSliderView.bundle(new Bundle());
            // 参数值设置（相当于tag或者data）
            imageSliderView.getBundle().putInt("position", i);
            mLayout.addSlider(imageSliderView);
        }
        // 设置默认的页卡指示器位置（若手动设置了页卡指示器则无效）
        // mLayout.setPresetIndicator(SliderLayout.PresetIndicators.Left_Bottom);
        mLayout.setPresetTransformer(SliderLayout.Transformer.ZoomOut);
        // 轮播说明文本框动画效果
        mLayout.setCustomAnimation(new DescriptionAnimation());
        // 切换间隔
        mLayout.setDuration(5000);
        // 创建也卡切换监听器
        mLayout.addOnPageChangeListener(this);
        // 手动设置自定义指示器（若无设置则为自定义）
        mLayout.setCustomIndicator((PagerIndicator) findViewById(R.id.page_indicator));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 设置停止轮播
        mLayout.stopAutoCycle();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int position = slider.getBundle().getInt("position");
        // 放大图
        ShowPhotosActivity.startShowPhotosActivity(this,mUrls,position);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
