package com.example.administrator.rxjavademo.baseuse;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.rxjavademo.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/5/14.
 */

public class BaseUseActivity extends Activity {
    private static final String TAG = "BaseUseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //被观察者
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
                Log.d(TAG, "e.onNext(4)");
                e.onNext(4);
//                e.onError(new Throwable("发送失败了"));
            }
        });
        //观察者
        Observer<Integer> observer = new Observer<Integer>() {
            private Disposable d;
            private int i;

            @Override
            public void onSubscribe(Disposable d) {
                this.d = d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext:" + value);
                i++;
                if (i == 2) {
                    d.dispose();
                    Log.d(TAG, "isDisposed:" + d.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete:");
            }
        };

        observable.subscribe(observer);
    }
}
