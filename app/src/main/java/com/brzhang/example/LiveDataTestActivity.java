package com.brzhang.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.brzhang.livedata.LiveData;
import com.brzhang.livedata.LiveDataManger;

/**
 * Created by brzhang on 2018/5/17.
 * Description :
 */
public class LiveDataTestActivity extends AppCompatActivity {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView btn1;
    private TextView btn2;

    class TestLiveData implements LiveData {

        public static final int KEY_TEXT1 = 100;
        public static final int KEY_TEXT2 = 200;
        String datatext1;
        String datatext2;

        @Override
        public void update(int key) {
            switch (key) {
                case KEY_TEXT1:
                    text1.setText(datatext1);
                    break;
                case KEY_TEXT2:
                    text2.setText(datatext2);
                    break;
            }
        }
    }

    private TestLiveData liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_test);
        liveData = new TestLiveData();
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        btn1 = (TextView) findViewById(R.id.btn1);
        btn2 = (TextView) findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment1.newInstance().show(getSupportFragmentManager(), "dialog");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment2.newInstance().show(getSupportFragmentManager(), "dialog");
            }
        });

        LiveDataManger.getInstance().registe(this, liveData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataManger.getInstance().unregiste(this);
    }
}
