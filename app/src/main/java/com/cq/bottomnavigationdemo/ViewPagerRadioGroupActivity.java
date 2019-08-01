package com.cq.bottomnavigationdemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;



/**
 * Created by ${YangJunJin}
 * on 2019/8/1
 */
public class ViewPagerRadioGroupActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private RadioGroup mRGContain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_radio_group);

        mViewPager = findViewById(R.id.viewpager);
        mRGContain = findViewById(R.id.rg_contain);

        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        mViewPager.setPageTransformer(true, new PageTransformer());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRGContain.check(R.id.rb_wx);
                        break;
                    case 1:
                        mRGContain.check(R.id.rb_book);
                        break;
                    case 2:
                        mRGContain.check(R.id.rb_discover);
                        break;
                    case 3:
                        mRGContain.check(R.id.rb_me);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mRGContain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_wx: //微信
                        mViewPager.setCurrentItem(0, true);
                        break;
                    case R.id.rb_book: //通讯录
                        mViewPager.setCurrentItem(1, true);
                        break;
                    case R.id.rb_discover: //发现
                        mViewPager.setCurrentItem(2, true);
                        break;
                    case R.id.rb_me: //我
                        mViewPager.setCurrentItem(3, true);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
