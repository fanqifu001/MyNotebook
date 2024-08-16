package com.fqf.mynotebook.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
;

import com.fqf.mynotebook.Base.BaseActivity;
import com.fqf.mynotebook.R;
import com.fqf.mynotebook.db.UserDbHelper;

public class RegisterActivity extends BaseActivity {
    private EditText edit_username;
    private EditText edit_password;
    private Button btn_register;
    private Toolbar back;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        edit_username = findViewById(R.id.user_rgt_edit);
        edit_password = findViewById(R.id.pwd_rgt_edit);
        btn_register = findViewById(R.id.btn_register);
        back = findViewById(R.id.toolbar_back);
    }

    @Override
    protected void setClickerListener() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }else {
                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password, "暂无~");
                    if (row>0){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,"该用户名已注册~",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }
}