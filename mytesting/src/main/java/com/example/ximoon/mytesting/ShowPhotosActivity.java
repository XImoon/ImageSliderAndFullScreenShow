package com.example.ximoon.mytesting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ximoon.mytesting.component.ImagePagerAdapter;
import com.example.ximoon.mytesting.component.MutipleTouchViewPager;

import java.util.ArrayList;


/***
 * 查看大图UI
 *
 */
public class ShowPhotosActivity extends AppCompatActivity implements LayoutVisibilityListener, View.OnClickListener {

    private MutipleTouchViewPager viewPager;
    private ImagePagerAdapter imagePagerAdapter;
    private int index;
    private ArrayList<String> photos = new ArrayList<String>();
    private RadioGroup mFullRgIndicator;
    private ImageView mFullIvBack;
    // 是否全屏显示
    private boolean isFull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);
        initView();
        initData();
    }

    public void initView() {
        photos = (ArrayList<String>) getIntent().getSerializableExtra("photos");
        index = getIntent().getIntExtra("index", 0);
        mFullIvBack = bindView(R.id.full_iv_back);
        mFullIvBack.setOnClickListener(this);
        viewPager = bindView(R.id.pager);
        mFullRgIndicator = bindView(R.id.full_rg_indicator);
        int size = photos.size();
        RadioButton rb = null;
        for (int i = 0; i < size; i++) {
            rb = (RadioButton) getLayoutInflater().inflate(R.layout.view_radiobutton_productdetail, null);
            mFullRgIndicator.addView(rb);
        }
        mFullRgIndicator.check(mFullRgIndicator.getChildAt(0).getId());
    }

    public void initData() {
        imagePagerAdapter = new ImagePagerAdapter(this, photos);
        imagePagerAdapter.setOnLayoutVisibilityListener(this);
        viewPager.setAdapter(imagePagerAdapter);
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                mFullRgIndicator.check(mFullRgIndicator.getChildAt(arg0).getId());
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        viewPager.setCurrentItem(index);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.full_iv_back:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public static void startShowPhotosActivity(Activity activity, ArrayList<String> photos,int index) {
        Intent intent = new Intent(activity, ShowPhotosActivity.class);
        intent.putStringArrayListExtra("photos", photos);
        intent.putExtra("index", index);
        activity.startActivity(intent);
    }

    @Override
    public void layoutVisibility() {
        // 判断是商品详情页的全屏看图模式
        // 如果处于全屏状态
        if (isFull) {
            mFullRgIndicator.setVisibility(View.VISIBLE);
            mFullIvBack.setVisibility(View.VISIBLE);
        } else {
            mFullRgIndicator.setVisibility(View.INVISIBLE);
            mFullIvBack.setVisibility(View.INVISIBLE);
        }
        isFull = !isFull;
    }

    public <T extends View> T bindView(int id) {
        T view = (T) findViewById(id);
        return view;
    }
}
