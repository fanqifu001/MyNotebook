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

public class RegisterActivity extends AppCompatActivity {
    private EditText edit_username;
    private EditText edit_password;
    private Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"请输入用户名和密码",Toast.LENGTH_LONG).show();
                }else {
                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password, "暂无~");
                    if (row>0){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

    }
    void initView(){
        edit_username = findViewById(R.id.user_rgt_edit);
        edit_password = findViewById(R.id.pwd_rgt_edit);
        btn_register = findViewById(R.id.btn_register);
    }
}