package com.xike.wea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button update;
    private Button city;
    private Button location;
    private Button theme;
    private Button set;
    private ImageView back;
    public int theme_id = 1;
    public int get_theme_id = 1;
    WeatherData weatherData = new WeatherData();
    Integer[] Id = {R.drawable.picc1,R.drawable.picc2,R.drawable.picc3,R.drawable.picc4,R.drawable.picc5,R.drawable.picc6,
            R.drawable.picc7,R.drawable.picc8,R.drawable.picc9,R.drawable.picc10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 使通知栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        findview();
        setViewListener();
        load_theme(); // 加载主题背景
    }
    @Override //用于接收上个活动返回的数据
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:  // 选择主题的请求码
                if(resultCode == RESULT_OK){
                    get_theme_id = data.getIntExtra("data_return",0);
                    if(get_theme_id != theme_id){
                        theme_id = get_theme_id;
                        writeTheme();
                        back.setImageResource(Id[get_theme_id-1]);
                        Toast.makeText(MainActivity.this,"主题已更改", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                }
                break;
        }
    }
    private void setViewListener(){
        update.setOnClickListener(this);
        location.setOnClickListener(this);
        city.setOnClickListener(this);
        theme.setOnClickListener(this);
        set.setOnClickListener(this);
        back.setOnClickListener(this);
    }
    private void findview(){
        update = (Button)findViewById(R.id.update);
        location =  (Button)findViewById(R.id.location);
        city =  (Button)findViewById(R.id.city);
        theme =  (Button)findViewById(R.id.theme);
        set =  (Button)findViewById(R.id.set);
        back = (ImageView)findViewById(R.id.back_img);
    }
    public void getWeather(){  // 获取天气数据信息
        int tmp = isNetworkConnected(this);
        if(tmp == 0){
            Toast.makeText(MainActivity.this,"网络未连接", Toast.LENGTH_SHORT).show();
        }
        if(tmp == 2){
            Toast.makeText(MainActivity.this,"当前网络不可用", Toast.LENGTH_SHORT).show();
        }
        if(tmp == 1){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        weatherData = DataUtil.TestData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.set:
                startActivity(new Intent(MainActivity.this, UsActivity.class ));
                break;
            case R.id.theme:
                Intent intent1 = new Intent(MainActivity.this, ThemeActivity.class);
                intent1.putExtra("extra_data",theme_id);
                startActivityForResult(intent1,1);
                break;
            case R.id.location:
                startActivity(new Intent(MainActivity.this, LocalActivity.class ));
                break;
            case R.id.city:
                Intent intent2 = new Intent(MainActivity.this, GifActivity.class);
                startActivityForResult(intent2,2);
                break;
        }
    }
    public void load_theme(){   // 刚进入App，加载theme主题
        String path = this.getFilesDir().getPath() + "//";
        File file = new File(path + "theme");
        if(!file.exists()){
            back.setImageResource(Id[0]);
            FileOutputStream out = null;
            BufferedWriter writer = null;
            try{
                out = openFileOutput("theme", Context.MODE_PRIVATE);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                writer.write(String.valueOf(theme_id));
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                try{
                    writer.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        else {
            FileInputStream in = null;
            BufferedReader reader = null;
            StringBuilder content = new StringBuilder();
            try{
                in = openFileInput("theme");
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while((line = reader.readLine()) != null){
                    content.append(line);
                }
                String tmp = content.toString();
                theme_id = Integer.parseInt(tmp);
                back.setImageResource(Id[Integer.parseInt(tmp)-1]);
            }catch(IOException e){
                e.printStackTrace();
            }finally {
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public void writeTheme(){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("theme", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(String.valueOf(get_theme_id));
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                writer.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    public int isNetworkConnected(Context context) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return 1;
            }
            else {
                return 0;
            }
    }
}
