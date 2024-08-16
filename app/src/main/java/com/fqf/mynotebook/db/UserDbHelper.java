package com.fqf.mynotebook.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fqf.mynotebook.entity.UserInfo;
import com.fqf.mynotebook.ui.RegisterActivity;

public class UserDbHelper extends SQLiteOpenHelper {
    private static UserDbHelper userDbHelper;
    private static final String DB_NAME = "user.db";
    private static final  int VERSION = 1;
    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table user_table(user_id integer primary key autoincrement," +
                "username text," +       //用户名
                "password text," +       //密码
                "nickname text" +       //昵称
                ")");
    }
    public synchronized static UserDbHelper getInstance(Context context){
        if (null==userDbHelper){
            userDbHelper  = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return userDbHelper;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    /*
    * 实现登录功能
    * */
    @SuppressLint("Range")
    public UserInfo login(String username){
        SQLiteDatabase db = getReadableDatabase();
        UserInfo userInfo = null;
        String sql = "select user_id,username,password,nickname from user_table where username = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()){
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            userInfo = new UserInfo(user_id, name, password, nickname);
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    /*
     * 实现注册功能
     * */
    public int register(String username,String password,String nickname){
        int insert = 0;
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //填充占位符
        values.put("username",username);
        values.put("password",password);
        values.put("nickname",nickname);

        String sql = "select user_id from user_table where username = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if(!cursor.moveToNext()){
            String nullColumnHack = "values(null,?,?,?)";
            insert = (int)db.insert("user_table", nullColumnHack, values);
        }
        cursor.close();
        db.close();
        return insert;
    }

}
