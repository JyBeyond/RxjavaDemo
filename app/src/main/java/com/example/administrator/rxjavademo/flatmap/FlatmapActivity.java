package com.example.administrator.rxjavademo.flatmap;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
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
                Observable.create(new ObservableOnSubscribe<RegisterInfo>() {
                    @Override
                    public void subscribe(ObservableEmitter<RegisterInfo> e) throws Exception {
                        String userName = edtUserName.getText().toString();
                        String psw = edtUserPsw.getText().toString();
                        Api.register(FlatmapActivity.this, userName, psw);
                    }
                }).subscribeOn(Schedulers.io())
                        .flatMap(new Function<RegisterInfo, ObservableSource<LoginInfo>>() {

                            @Override
                            public ObservableSource<LoginInfo> apply(RegisterInfo registerInfo) throws Exception {
                                return null;
                            }
                        });
                break;
        }
    }
}
