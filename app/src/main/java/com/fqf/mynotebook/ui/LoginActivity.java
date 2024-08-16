package com.fqf.mynotebook.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fqf.mynotebook.Base.BaseActivity;
import com.fqf.mynotebook.R;
import com.fqf.mynotebook.db.UserDbHelper;
import com.fqf.mynotebook.entity.UserInfo;
import com.google.android.material.slider.RangeSlider;

public class LoginActivity extends BaseActivity {

    private EditText edit_username;
    private EditText edit_password;
    private Button login_button;
    private TextView tv_2reg;
    private CheckBox checked_pwd;
    private final static String SP_INFO = "Login";
    private SharedPreferences mSharedPreferences;
    private boolean is_login;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        edit_username = findViewById(R.id.user_login_edit);
        edit_password = findViewById(R.id.pwd_login_edit);
        tv_2reg = findViewById(R.id.to_register);
        login_button = findViewById(R.id.btn_login);
        checked_pwd = findViewById(R.id.check_pwd);
    }

    @Override
    protected void setClickerListener() {
        tv_2reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }else{
                    UserInfo userInfo = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if(userInfo!=null){
                        if (username.equals(userInfo.getUsername())&&password.equals(userInfo.getPassword())){
                            SharedPreferences.Editor edit = mSharedPreferences.edit();
                            edit.putString("username",username);
                            edit.putString("password",password);
                            edit.putBoolean("is_login",is_login);
                            edit.commit();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this,"该账号暂未注册~",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        checked_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                is_login = isChecked;
            }
        });
    }

    protected void initData() {
        mSharedPreferences = getSharedPreferences(SP_INFO, MODE_PRIVATE);
        boolean isLogin = mSharedPreferences.getBoolean("is_login",false);
        String username = mSharedPreferences.getString("username", "");
        String password = mSharedPreferences.getString("password", "");
        if (isLogin){
            edit_username.setText(username);
            edit_password.setText(password);
            checked_pwd.setChecked(true);
        }
    }
}