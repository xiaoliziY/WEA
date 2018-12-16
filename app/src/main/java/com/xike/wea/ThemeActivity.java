package com.xike.wea;

/**
 * Created by Eric on 2018/11/20.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemeActivity extends Activity implements View.OnClickListener{

    public int themeId;
    private ImageButton return_main;
    private ImageView back_img;
    private ImageButton pic1,pic2,pic3,pic4,pic5,pic6,pic7,pic8, pic9,pic10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_theme);
        // 使通知栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Intent intent = getIntent();
        themeId = intent.getIntExtra("extra_data",1);
        findview();
        setViewListener();
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("data_return", themeId);
        setResult(RESULT_OK, intent);
        finish();
    }
    private void setViewListener(){
        return_main.setOnClickListener(this);
        pic1.setOnClickListener(this);pic2.setOnClickListener(this);
        pic3.setOnClickListener(this);pic4.setOnClickListener(this);
        pic5.setOnClickListener(this);pic6.setOnClickListener(this);
        pic7.setOnClickListener(this);pic8.setOnClickListener(this);
        pic9.setOnClickListener(this);pic10.setOnClickListener(this);
    }
    private void findview(){
        return_main =  (ImageButton)findViewById(R.id.return_main);
        back_img = (ImageView)findViewById(R.id.back_img);
        pic1 = (ImageButton)findViewById(R.id.pic1);pic2 = (ImageButton)findViewById(R.id.pic2);
        pic3 = (ImageButton)findViewById(R.id.pic3);pic4 = (ImageButton)findViewById(R.id.pic4);
        pic5 = (ImageButton)findViewById(R.id.pic5);pic6 = (ImageButton)findViewById(R.id.pic6);
        pic7 = (ImageButton)findViewById(R.id.pic7);pic8 = (ImageButton)findViewById(R.id.pic8);
        pic9 = (ImageButton)findViewById(R.id.pic9);pic10 = (ImageButton)findViewById(R.id.pic10);
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.return_main:
                Intent intent = new Intent();
                intent.putExtra("data_return", themeId);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.pic1:
                themeId = 1;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic2:
                themeId = 2;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic3:
                themeId = 3;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic4:
                themeId = 4;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic5:
                themeId = 5;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic6:
                themeId = 6;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic7:
                themeId = 7;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic8:
                themeId = 8;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic9:
                themeId = 9;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pic10:
                themeId = 10;
                Toast.makeText(ThemeActivity.this,"主题更改...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
