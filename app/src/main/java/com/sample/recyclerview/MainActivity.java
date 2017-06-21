package com.sample.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @InjectView(R.id.btn_one)
    Button btnOne;
    @InjectView(R.id.btn_two)
    Button btnTwo;
    @InjectView(R.id.btn_three)
    Button btnThree;
    @InjectView(R.id.btn_four)
    Button btnFour;
    @InjectView(R.id.btn_five)
    Button btnFive;
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    private List<String> datas;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        //初始化数据
        initData();
        //创建适配器
        recyclerAdapter = new RecyclerAdapter(this, datas);
        recycler.setAdapter(recyclerAdapter);
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycler.setItemAnimator(new DefaultItemAnimator());

        /**
         *	设置布局：
         * 第一个参数：上下文
         * 第二参数：方向
         * 第三个参数：排序低到高还是高到低显示，false 是低到高显示
         *
         */
        //recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            datas.add("content" + i);

        }
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                recyclerAdapter.addData(0, "new content");
                recycler.scrollToPosition(0);
                break;
            case R.id.btn_two:
                recyclerAdapter.removeData(0);
                break;
            case R.id.btn_three:
                //list类型
                recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            //grid类型
            case R.id.btn_four:
                recycler.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
                break;
            //瀑布类型
            case R.id.btn_five:
                recycler.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
