package com.example.administrator.rxjavademo.flatmap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.rxjavademo.MainActivity;
import com.example.administrator.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/14.
 */

public class FlatmapActivity extends Activity implements View.OnClickListener {
    private EditText edtUserName, edtUserPsw;
    private Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flatmap);
        edtUserName = findViewById(R.id.edt_user_name);
        edtUserPsw = findViewById(R.id.edt_user_psw);
        btnRegister = findViewById(R.id.register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                String userName = edtUserName.getText().toString();
                String psw = edtUserPsw.getText().toString();

                Api.register(FlatmapActivity.this, userName, psw)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<RegisterInfo>() {
                            @Override
                            public void accept(RegisterInfo registerInfo) throws Exception {
                                Toast.makeText(FlatmapActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .observeOn(Schedulers.io())
                        .flatMap(new Function<RegisterInfo, ObservableSource<LoginInfo>>() {

                            @Override
                            public ObservableSource<LoginInfo> apply(RegisterInfo registerInfo) throws Exception {
                                return Api.login(FlatmapActivity.this, registerInfo.getUserName(), registerInfo.getPsw(), 2);
                            }
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<LoginInfo>() {
                            @Override
                            public void accept(LoginInfo loginInfo) throws Exception {
                                Toast.makeText(FlatmapActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(FlatmapActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                ;
                break;
        }
    }
}
