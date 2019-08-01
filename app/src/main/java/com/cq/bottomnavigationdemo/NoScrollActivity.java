package com.cq.bottomnavigationdemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioGroup;



public class NoScrollActivity extends AppCompatActivity {
    private RadioGroup mRgGroup;
    private FragmentManager fragmentManager;

    private static final String[] FRAGMENT_TAG = {"tab_map", "tab_car", "tab_find", "tab_me"};

    private final int show_tab_map = 0;//地图
    private final int show_tab_car = 1;//找车
    private final int show_tab_find = 2;//发现
    private final int show_tab_me = 3;//我的
    private int mrIndex = show_tab_map;//默认选中地图

    private int index = -100;// 记录当前的选项
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private TabCarFragment tabCarFragment;
    private TabMapFragment tabMapFragment;
    private TabFindFragment tabFindFragment;
    private TabMeFragment tabMeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //解决底部选项按钮被输入文字框顶上去
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //显示界面
        setContentView(R.layout.activity_no_scroll);

        fragmentManager = getSupportFragmentManager();

        //防止app闪退后fragment重叠
        if (savedInstanceState != null) {
            //读取上一次界面Save的时候tab选中的状态
            mrIndex = savedInstanceState.getInt(PRV_SELINDEX, mrIndex);

            tabCarFragment = (TabCarFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[1]);
            tabMapFragment = (TabMapFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[0]);
            tabFindFragment = (TabFindFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[2]);
            tabMeFragment = (TabMeFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG[3]);
        }
        //初始化
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(PRV_SELINDEX, mrIndex);
        super.onSaveInstanceState(outState);
    }

    protected void initView() {
        //获得RadioGroup控件
        mRgGroup = (RadioGroup) findViewById(R.id.rg_group);
        //选择设置Fragment
        setTabSelection(show_tab_map);
        //点击事件
        mRgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_car://导航
                        setTabSelection(show_tab_car);
                        break;
                    case R.id.rb_map://地图
                        setTabSelection(show_tab_map);
                        break;
                    case R.id.rb_find://发现
                        setTabSelection(show_tab_find);
                        break;
                    case R.id.rb_me://我的
                        setTabSelection(show_tab_me);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param id 传入的选择的fragment
     */
    private void setTabSelection(int id) {    //根据传入的index参数来设置选中的tab页。
        if (id == index) {
            return;
        }
        index = id;
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 设置切换动画
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case show_tab_car://导航的fragment
                mRgGroup.check(R.id.rb_car);
                if (tabCarFragment == null) {
                    tabCarFragment = new TabCarFragment();
                    transaction.add(R.id.fl_container, tabCarFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(tabCarFragment);
                }
                transaction.commit();
                break;
            case show_tab_map://地图的fragment
                mRgGroup.check(R.id.rb_map);
                if (tabMapFragment == null) {
                    tabMapFragment = new TabMapFragment();
                    transaction.add(R.id.fl_container, tabMapFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(tabMapFragment);
                }
                transaction.commit();
                break;
            case show_tab_find://的fragment
                mRgGroup.check(R.id.rb_find);//设置商城被选中
                if (tabFindFragment == null) {
                    tabFindFragment = new TabFindFragment();
                    transaction.add(R.id.fl_container, tabFindFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(tabFindFragment);
                }
                transaction.commit();
                break;
            case show_tab_me://我的的fragment
                mRgGroup.check(R.id.rb_me);//我的的fragmen
                if (tabMeFragment == null) {
                    tabMeFragment = new TabMeFragment();
                    transaction.add(R.id.fl_container, tabMeFragment, FRAGMENT_TAG[index]);
                } else {
                    transaction.show(tabMeFragment);
                }
                transaction.commit();
                break;
        }
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (tabCarFragment != null) {
            transaction.hide(tabCarFragment);
        }
        if (tabMapFragment != null) {
            transaction.hide(tabMapFragment);
        }
        if (tabFindFragment != null) {
            transaction.hide(tabFindFragment);
        }
        if (tabMeFragment != null) {
            transaction.hide(tabMeFragment);
        }
    }
}
