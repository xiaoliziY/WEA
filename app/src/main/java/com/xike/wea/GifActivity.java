package com.xike.wea;

/**
 * Created by Eric on 2018/11/20.
 */


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GifActivity extends Activity{

    private SoundPool mSound;
    private String city;
    private String[] data = {"北京","上海","天津","重庆","香港","澳门","台湾","黑龙江","吉林","辽宁","内蒙古","河北",
            "河南","山西","山东","江苏","浙江","福建","江西","安徽","湖北","湖南","广东","广西","海南","贵州","云南",
    "四川","西藏","陕西","宁夏","甘肃","青海","新疆                                                   ＞"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_city);
        // 使通知栏透明化
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //PlaySound();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                GifActivity.this, R.layout.list_text_style, data);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        // 点击某view时的动作，调到该省份相应市级城市的页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(GifActivity.this, Choose_shi.class);
                intent.putExtra("extra_data",position);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override //用于接收上个活动返回的数据
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    city = data.getStringExtra("data_return");
                    Toast.makeText(this,city,Toast.LENGTH_SHORT).show();
                    if(!city.equals("null")){ // 选择了城市
                        Intent intent = new Intent();
                        intent.putExtra("data_return", city);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                break;
        }
    }
    private void PlaySound(){
        AudioManager mgr = (AudioManager) this.getSystemService(this.AUDIO_SERVICE);
        // 获取系统声音的当前音量
        float currentVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统声音的最大音量
        float maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量的百分比
        float volume = currentVolume / maxVolume;
        mSound = new SoundPool(2, AudioManager.STREAM_SYSTEM, 0); // 初始化SoundPool
        mSound.load(this, R.raw.duoyun, 1);  // 加载音频资源
        mSound.play(1,volume,volume,1,-1,0); // 开始播放
    }
    @Override
    public void onBackPressed(){ // 通过按Back键返回
     //   mSound.stop(1);
     //   mSound.release();
        Intent intent = new Intent();
        intent.putExtra("data_return", "null");
        setResult(RESULT_OK, intent);
        finish();
    }
}
