package et.ts.wyymusic;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;
import et.ts.fragment.MusicQieHuan;
import et.ts.service.MusicService;
import et.ts.util.LrcHandle;
import et.ts.view.WordView;

public class MainActivity extends AppCompatActivity {


    public boolean ab=false;
    private TextView musicStatus, musicTime, musicTotal;
    private SeekBar seekBar;
    private List mTimeList;
    private WordView wordView;
    private MusicQieHuan musicQieHuan;
    private Button btnPlayOrPause, btnpre,btnnext;
    private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
    private boolean tag1 = false;
    private boolean tag2 = false;
    private MusicService musicService;










    public SeekBar getSeekBar(){

        return seekBar;
    }

    public MusicService getMusicService(){

        return musicService;
    }

    public List getMTimeList(){

        return mTimeList;
    }



    //  在Activity中调用 bindService 保持与 Service 的通信
    private void bindServiceConnection() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);
        bindService(intent, serviceConnection, this.BIND_AUTO_CREATE);
    }

    //  回调onServiceConnected 函数，通过IBinder 获取 Service对象，实现Activity与 Service的绑定
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MyBinder) (service)).getService();
            Log.i("musicService", musicService + "");
            //获取总时长
            musicTotal.setText(time.format(musicService.mediaPlayer.getDuration()));
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    //  通过 Handler 更新 UI 上的组件状态
    public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {

            musicTime.setText(time.format(musicService.mediaPlayer.getCurrentPosition()));
            seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
            seekBar.setMax(musicService.mediaPlayer.getDuration());
            musicTotal.setText(time.format(musicService.mediaPlayer.getDuration()));
            //0.2秒更新组件
            handler.postDelayed(runnable, 200);

        }
    };



    private void findViewById() {
        musicTime = (TextView) findViewById(R.id.MusicTime);
        musicTotal = (TextView) findViewById(R.id.MusicTotal);
        seekBar = (SeekBar) findViewById(R.id.MusicSeekBar);
        btnPlayOrPause = (Button) findViewById(R.id.BtnPlayorPause);
        /*btnStop = (Button) findViewById(R.id.BtnStop);
        btnQuit = (Button) findViewById(R.id.BtnQuit);*/
        btnnext=(Button)findViewById(R.id.music_next);
        btnpre=(Button)findViewById(R.id.music_pre);
        musicStatus = (TextView) findViewById(R.id.MusicStatus);
        musicQieHuan=new MusicQieHuan();
        wordView=(WordView)findViewById(R.id.text);
        LrcHandle lrcHandler = new LrcHandle();
        lrcHandler.readLRC("/sdcard/wangyiMusic/成都_歌词.lrc");
        mTimeList = lrcHandler.getTime();
        for(int i=0;i<mTimeList.size();i++){
            Log.d("niu","nnnnnnnnnnnn"+mTimeList.get(i));
        }
        }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.music_detial,fragment);
        transaction.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);









        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {

            findViewById();
            bindServiceConnection();
            replaceFragment(musicQieHuan);
            myListener();

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (fromUser == true) {
                        musicService.mediaPlayer.seekTo(progress);
                        Log.d("niu",""+progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


        }




    }



    private void myListener() {
        btnPlayOrPause.setOnClickListener(new View.OnClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d("niu","musicService.mediaPlayer");
                if (musicService.mediaPlayer != null) {
                    seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
                    seekBar.setMax(musicService.mediaPlayer.getDuration());
                }
                //  由tag的变换来控制事件的调用
                if (musicService.tag != true) {
                    btnPlayOrPause.setText("PAUSE");
                    musicStatus.setText("Playing");
                    musicService.playOrPause();
                    musicService.tag = true;
                    ab=true;
                    Log.d("niu","musicService.tagr"+tag1);

                } else {
                    btnPlayOrPause.setText("PLAY");
                    musicStatus.setText("Paused");
                    musicService.playOrPause();
                    ab=false;
                    musicService.tag = false;
                }
                if (tag2 == false) {
                    handler.post(runnable);
                    tag2 = true;
                }
            }
        });

       /* btnStop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                musicStatus.setText("Stopped");
                btnPlayOrPause.setText("PLAY");
                musicService.stop();
                animator.pause();
                musicService.tag = false;
            }
        });*/

        //  停止服务时，必须解除绑定，写入btnQuit按钮中
        /*btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                unbindService(serviceConnection);
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                stopService(intent);
                try {
                    MainActivity.this.finish();
                } catch (Exception e) {

                }
            }
        });*/


        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayOrPause.setText("PAUSE");
                musicStatus.setText("Playing");
                musicService.playOrPause();
                musicService.tag = true;
                musicService.pre();
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayOrPause.setText("PAUSE");
                musicStatus.setText("Playing");
                musicService.playOrPause();
                musicService.tag = true;
                musicService.next();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case 1:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                    findViewById();
                    bindServiceConnection();
                    myListener();
                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser == true) {
                                musicService.mediaPlayer.seekTo(progress);
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });


                }
                else {
                    Toast.makeText(this,"permission densy",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    //  获取并设置返回键的点击事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}



