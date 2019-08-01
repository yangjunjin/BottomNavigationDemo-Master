package com.cq.bottomnavigationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ${YangJunJin}
 * on 2019/8/1
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_no_scroll:
                startActivity(new Intent(MainActivity.this, NoScrollActivity.class));
                break;
            case R.id.btn_scroll:
                startActivity(new Intent(MainActivity.this, ViewPagerRadioGroupActivity.class));
                break;
            case R.id.btn_viewpager_dot:
                startActivity(new Intent(MainActivity.this, ViewPagerRedDotActivity.class));
                break;
            case R.id.btn_changecoloriconwithtext:
                startActivity(new Intent(MainActivity.this, ChangeColorIconWithTextActivity.class));
                break;
        }
    }
}
