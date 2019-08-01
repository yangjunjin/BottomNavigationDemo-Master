package com.cq.bottomnavigationdemo;


import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by ${YangJunJin}
 * on 2019/8/1
 */
public class MainFragmentFactory {
    private static HashMap<Integer, Fragment> mSavedFragment = new HashMap<>();

    //根据position得到对应的fragment
    public static Fragment getFragment(int position) {
        Fragment fragment = mSavedFragment.get(position);
        if(fragment == null) {
            switch (position) {
                case 0:
                    fragment =  new TabCarFragment();
                    break;
                case 1:
                    fragment = new TabFindFragment();
                    break;
                case 2:
                    fragment = new TabMapFragment();
                    break;
                case 3:
                    fragment = new TabMeFragment();
                    break;
            }
            //创建之后保存
            mSavedFragment.put(position, fragment);
        }


        return fragment;
    }
    public static void deleteFragment(){
        for (int i = 0 ; i < 4 ; i++){
            mSavedFragment.remove(i);
        }
    }
}
