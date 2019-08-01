package com.cq.bottomnavigationdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ${YangJunJin}
 * on 2019/8/1
 */
public class ViewPagerRedDotActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private TextView tab1, tab2, tab3, tab4;
    private RelativeLayout rl1, rl2, rl3, rl4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_red_dot);

        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
        tab4 = findViewById(R.id.tab4);
        rl1 = findViewById(R.id.rl1);
        rl2 = findViewById(R.id.rl2);
        rl3 = findViewById(R.id.rl3);
        rl4 = findViewById(R.id.rl4);
        mViewPager = findViewById(R.id.viewpager);

        initAction();
    }

    private void initAction() {
        rl1.setSelected(true);
        rl1.setOnClickListener(this);
        rl2.setOnClickListener(this);
        rl3.setOnClickListener(this);
        rl4.setOnClickListener(this);

        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        mViewPager.setPageTransformer(true, new PageTransformer());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl1:
                mViewPager.setCurrentItem(0);
                selectPosition(0);
                break;
            case R.id.rl2:
                mViewPager.setCurrentItem(1);
                selectPosition(1);
                break;
            case R.id.rl3:
                mViewPager.setCurrentItem(2);
                selectPosition(2);
                break;
            case R.id.rl4:
                mViewPager.setCurrentItem(3);
                selectPosition(3);
                break;
        }

    }

    /**
     * 选中
     *
     * @param position
     */
    public void selectPosition(int position) {
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);

        if (0 == position) {
            tab1.setSelected(true);
        } else if (1 == position) {
            tab2.setSelected(true);
        } else if (2 == position) {
            tab3.setSelected(true);
        } else if (3 == position) {
            tab4.setSelected(true);
        }
    }
}
