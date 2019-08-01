package com.cq.bottomnavigationdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ${YangJunJin}
 * on 2019/8/1
 */
public  class MainFragmentAdapter extends FragmentPagerAdapter {

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    //根据position的值 返回对应的fragment对象
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = MainFragmentFactory.getFragment(position);
        return fragment;
    }

    //返回ViewPager要显示的item数量
    @Override
    public int getCount() {
        return 4;
    }
}