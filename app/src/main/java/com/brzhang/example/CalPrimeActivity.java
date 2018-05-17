package com.brzhang.example;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brzhang on 2017/10/15.
 * Description :
 */

public class CalPrimeActivity extends AppCompatActivity {
    static final String UPPER_NUM = "upper";
    EditText  etNum;
    CalThread calThread;
    private Button mBtn;

    // 定义一个线程类
    class CalThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();
            mHandler = new Handler() {
                // 定义处理消息的方法
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x123) {
                        int upper = msg.getData().getInt(UPPER_NUM);
                        List<Integer> nums = new ArrayList<Integer>();
                        // 计算从2开始、到upper的所有质数
                        outer:
                        for (int i = 2; i <= upper; i++) {
                            // 用i处于从2开始、到i的平方根的所有数
                            for (int j = 2; j <= Math.sqrt(i); j++) {
                                // 如果可以整除，表明这个数不是质数
                                if (i != 2 && i % j == 0) {
                                    continue outer;
                                }
                            }
                            nums.add(i);
                        }
                        // 使用Toast显示统计出来的所有质数
                        Toast.makeText(CalPrimeActivity.this, nums.toString()
                                , Toast.LENGTH_LONG).show();
                        mBtn.setText("计算完毕");
                    }
                }
            };
            Looper.loop();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_prime);
        etNum = (EditText) findViewById(R.id.etNum);
        mBtn = (Button) findViewById(R.id.button_cal);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal();
            }
        });
        calThread = new CalThread();
        // 启动新线程
        calThread.start();
    }

    // 为按钮的点击事件提供事件处理函数
    public void cal() {
        // 创建消息
        Message msg = new Message();
        msg.what = 0x123;
        Bundle bundle = new Bundle();
        bundle.putInt(UPPER_NUM,
                Integer.parseInt(etNum.getText().toString()));
        msg.setData(bundle);
        // 向新线程中的Handler发送消息
        calThread.mHandler.sendMessage(msg);
    }
}
