package com.xike.wea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Choose_shi extends Activity {

    private int position_one;
    private String city;
    private String[][] datas = {{"北京","朝阳"},{"上海","松江"},{"天津","北辰"},{"重庆","长寿"},
            {"香港"},{"澳门"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        position_one = intent.getIntExtra("extra_data",1);
        setContentView(R.layout.activity_choose_shi);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Choose_shi.this, R.layout.list_text_style, datas[position_one]);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        // 点击某view时的动作，选择到某城市，并将其返回给上一个活动
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                city = datas[position_one][position];
                Intent intent = new Intent();
                intent.putExtra("data_return", city);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){ // 通过按Back键返回，此时并没有做出城市选择
        Intent intent = new Intent();
        intent.putExtra("data_return", "null");
        setResult(RESULT_OK, intent);
        finish();
    }
}
