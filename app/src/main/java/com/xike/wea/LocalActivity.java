package com.xike.wea;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class LocalActivity extends AppCompatActivity {

    private SoundPool mSound;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        videoView = (VideoView) findViewById(R.id.videoView);
        final String videoPath = Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.qingtian_baitian).toString();
        videoView.setVideoPath(videoPath);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }});
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(videoPath);
                videoView.start();
            }
        });
        PlaySound();
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
        mSound.load(this, R.raw.qingtian_baitian_m, 1);  // 加载音频资源
        mSound.play(1,volume,volume,1,-1,0); // 开始播放
    }
    @Override
    public void onBackPressed(){ // 通过按Back键返回
        mSound.stop(1);
        mSound.release();
        videoView.stopPlayback(); // 停止播放且释放资源
        finish();
    }
}
