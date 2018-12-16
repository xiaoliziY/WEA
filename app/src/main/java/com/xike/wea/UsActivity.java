package com.xike.wea;

/**
 * Created by Eric on 2018/11/20.
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class UsActivity extends Activity implements View.OnClickListener{

    private ImageButton to_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        // 使通知栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        findview();
        setViewListener();
    }
    @Override
    public void onBackPressed(){ // 通过按Back键返回
        finish();
    }
    private void setViewListener(){
        to_main.setOnClickListener(this);
    }
    private void findview(){
        to_main =  (ImageButton)findViewById(R.id.to_main);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.to_main:
                finish();
                break;
        }
    }
}
