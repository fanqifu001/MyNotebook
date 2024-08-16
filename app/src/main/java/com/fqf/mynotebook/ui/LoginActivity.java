package com.fqf.mynotebook.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fqf.mynotebook.R;
import com.fqf.mynotebook.db.UserDbHelper;
import com.fqf.mynotebook.entity.UserInfo;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_username;
    private EditText edit_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_username.getText().toString();
                String password = edit_password.getText().toString();
                if (TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }else{
                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if (username.equals(login.getUsername())&&password.equals(login.getPassword())){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    void initView(){
        edit_username = findViewById(R.id.user_login_edit);
        edit_password = findViewById(R.id.pwd_login_edit);
        btn_login = findViewById(R.id.btn_login);
    }
}