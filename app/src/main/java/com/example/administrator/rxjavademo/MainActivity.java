package com.example.administrator.rxjavademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.rxjavademo.baseuse.BaseUseActivity;
import com.example.administrator.rxjavademo.threadcontrol.ThreadSchedulerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBaseUse, btnThreadScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBaseUse = findViewById(R.id.btn_base_use);
        btnThreadScheduler = findViewById(R.id.btn_thread_scheduler);
        btnBaseUse.setOnClickListener(this);
        btnThreadScheduler.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_base_use:
                Intent intent = new Intent(this, BaseUseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_thread_scheduler:
                Intent intent1 = new Intent(this, ThreadSchedulerActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
