package et.ts.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import java.util.List;


import et.ts.fragment.MusicGeCi;
import et.ts.util.LrcHandle;
import et.ts.view.WordView;
import et.ts.wyymusic.R;


public class MusicService extends Service {
    private WordView wordView;
    private List mTimeList;
    private MusicGeCi musicGeCi;

    String [] mMusics=new String[]{
            "/sdcard/wangyiMusic/cd.mp3",
            "/sdcard/wangyiMusic/ce.mp3",
            "/sdcard/wangyiMusic/cf.mp3",
            "/sdcard/wangyiMusic/cg.mp3",
            "/sdcard/wangyiMusic/ch.mp3"
    };
    private  int currentMusicId=0;
    private  int myMusicSize=mMusics.length;

    public MediaPlayer mediaPlayer;
    public boolean tag = false;

    public MusicService() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(mMusics[currentMusicId]);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  通过 Binder 来保持 Activity 和 Service 的通信
    public MyBinder binder = new MyBinder();
    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void playOrPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();

        }
    }

    public void pre(){
        currentMusicId=((currentMusicId-1)+myMusicSize)%myMusicSize;
        stop();
        Log.d("niuliangyi","hhhhhhhhhh"+currentMusicId);
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public void next(){
        currentMusicId=((currentMusicId+1)+myMusicSize)%myMusicSize;
        stop();
        Log.d("niuliangyi","hhhhhhhhhh"+currentMusicId);
        try {
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(mMusics[currentMusicId]);
                mediaPlayer.prepare();
                mediaPlayer.seekTo(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}

