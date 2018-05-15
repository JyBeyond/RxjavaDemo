package com.example.administrator.rxjavademo.delay;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.rxjavademo.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2018/5/15.
 */

public class DelayActivity extends Activity implements View.OnClickListener {

    private Button btnDownTime;
    private static final String TAG = "DelayActivity";
    private Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);
        btnDownTime = findViewById(R.id.btn_down_time);
        btnDownTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_down_time:
                mDisposable = Observable.intervalRange(0, 11, 0, 1, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                Log.d(TAG, "aLong:" + aLong);
                                btnDownTime.setEnabled(false);
                                btnDownTime.setText((10 - aLong) + "秒后可重新发送");
                            }
                        }).doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                btnDownTime.setEnabled(true);
                                btnDownTime.setText("获取验证码");
                            }
                        }).subscribe();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null) {
            mDisposable.isDisposed();
        }
        super.onDestroy();
    }
}
