package com.fqf.mynotebook.Base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
            initViews();
        setClickerListener();
        initData();
    }
    /*
    *加载布局文件
    *
    * */
    protected abstract int getLayoutResId();
    /*
    * 初始化控件*/
    protected abstract void initViews();
    /*设置点击事件*/
    protected abstract void setClickerListener();
    /*初始化数据*/
    protected abstract void initData();
}
