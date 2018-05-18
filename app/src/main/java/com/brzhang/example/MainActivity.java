package com.brzhang.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.brzhang.livedata.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private ActivityAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        initData();
    }

    private void setupView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPageAdapter = new ActivityAdapter();
        mRecyclerView.setAdapter(mPageAdapter);
        mPageAdapter.setList(initPageList());
    }

    private List<PageData> initPageList() {
        List<PageData> pageDatas = new ArrayList<>();
        pageDatas.add(new PageData("Handler测试", CalPrimeActivity.class));
        pageDatas.add(new PageData("手势测试", GestureActivity.class));
        pageDatas.add(new PageData("LiveData测试", LiveDataTestActivity.class));
        return pageDatas;
    }
}
