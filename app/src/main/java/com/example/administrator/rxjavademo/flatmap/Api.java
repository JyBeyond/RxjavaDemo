package com.example.administrator.rxjavademo.flatmap;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Administrator on 2018/5/14.
 */

public class Api {
    public static Observable<RegisterInfo> register(final Context mContext, final String userName, final String psw) {
        return Observable.create(new ObservableOnSubscribe<RegisterInfo>() {
            @Override
            public void subscribe(ObservableEmitter<RegisterInfo> e) throws Exception {
                //请求网络
                //拿到返回的信息,解析数据
                RegisterInfo registerInfo = new RegisterInfo();
                registerInfo.setPsw(psw);
                registerInfo.setUserId(1);
                registerInfo.setUserName(userName);
                e.onNext(registerInfo);
                e.onComplete();
            }
        });

    }

    public static Observable<LoginInfo> login(Context mContext, String userName, String psw, int clientId) {
        return Observable.create(new ObservableOnSubscribe<LoginInfo>() {
            @Override
            public void subscribe(ObservableEmitter<LoginInfo> e) throws Exception {
                //请求登录接口
                //返回数据
                //解析数据
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setReponseCode(300);
                //判断请求状态
                if (loginInfo.getReponseCode() == 200) {//成功
                    e.onNext(loginInfo);
                } else {
                    e.onError(new Throwable("获取数据失败"));
                }
            }
        });
    }
}
