package com.example.administrator.rxjavademo.flatmap;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/5/14.
 */

public class Api {
    public static void register(Context mContext, String userName, String psw) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(psw))
            return;
        if ("lijingya".equals(userName) && "123".equals(psw)) {
            Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
        }
    }

    public static void login(Context mContext, String userName, String psw, int clientId) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(psw) || clientId != 1)
            return;
        if ("lijingya".equals(userName) && "123".equals(psw) && clientId == 1) {
            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
        }
    }
}
