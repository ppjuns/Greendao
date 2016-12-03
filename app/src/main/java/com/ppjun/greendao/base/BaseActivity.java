package com.ppjun.greendao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Package :com.ppjun.greendao.base
 * Description :
 * Author :Rc3
 * Created at :2016/12/2 10:27.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(setlayoutId(savedInstanceState));
        initContext();
        initViews();
        initDatas();
        initListeners();
    }

    protected abstract int setlayoutId(Bundle savedInstanceState);

    protected abstract void initContext();


    protected abstract void initViews();

    protected abstract void initDatas();

    protected abstract void initListeners();

}
