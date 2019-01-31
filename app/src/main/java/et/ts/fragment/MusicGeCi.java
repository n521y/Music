package et.ts.fragment;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.List;
import et.ts.view.WordView;
import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.R;

public class MusicGeCi extends Fragment {
private Activity activity;
public WordView wordView;
private View view;
private SeekBar ylseekBar;
private List mTimeList;
private MediaPlayer mediaPlayer;
private int max,current;
private AudioManager am;
private SeekBar mSeekBar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=(MainActivity)getActivity();
        initYL();

    }

    //音量改变
    private void initYL(){
        am=(AudioManager)activity.getSystemService(Context.AUDIO_SERVICE);
        max = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        current = am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_music_geci,container,false);
        wordView=view.findViewById(R.id.text);
        ylseekBar=view.findViewById(R.id.yl_SeekBar);
        ylseekBar.setProgress(current);
        ylseekBar.setMax(max);
        activity=(MainActivity)getActivity();
        mSeekBar=((MainActivity) activity).getSeekBar();
        mediaPlayer=((MainActivity) activity).getMusicService().mediaPlayer;
        mTimeList=((MainActivity) activity).getMTimeList();
        initView(view);
        if(mediaPlayer.getCurrentPosition()==0){

            xxx();
        }else {
            int in=0;
            for(int i=0;i<=mTimeList.size();i++){
                int a=(int) mTimeList.get(i);
                if(mSeekBar.getProgress()>=a){
                    in=i;
                    break;
                }

            }
            xbx(in);


        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //音量拖动条的监听器
        ylseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    int currentValm=am.getStreamVolume(AudioManager.STREAM_MUSIC);
                    Toast.makeText(activity,"11111111111111"+currentValm,Toast.LENGTH_SHORT).show();
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



    private  void initView(View view){
        view.findViewById(R.id.text).setOnClickListener(backListener);

    }

    //歌词的显示

    private void xxx(){


        final Handler handler1 = new Handler();

        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (mediaPlayer.isPlaying()) {
                    handler1.post(new Runnable() {

                        @Override
                        public void run() {
                            wordView.invalidate();
                        }
                    });
                    try {
                        int jian=(int)mTimeList.get(i+1)-(int)mTimeList.get(i);
                        Thread.sleep(jian);
                    } catch (InterruptedException e) {
                    }
                    i++;
                    if (i == mTimeList.size() - 1) {
                        mediaPlayer.stop();
                        break;
                    }
                }
            }
        }).start();

    }



    private void xbx(final int a){


        final Handler handler1 = new Handler();

        new Thread(new Runnable() {
            int i =a;

            @Override
            public void run() {
                while (mediaPlayer.isPlaying()) {
                    handler1.post(new Runnable() {

                        @Override
                        public void run() {
                            wordView.invalidate();
                        }
                    });
                    try {
                        int jian=(int)mTimeList.get(i+1)-(int)mTimeList.get(i);
                        Thread.sleep(jian);
                    } catch (InterruptedException e) {
                    }
                    i++;
                    if (i == mTimeList.size() - 1) {
                        mediaPlayer.stop();
                        break;
                    }
                }
            }
        }).start();

    }




    private View.OnClickListener backListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().popBackStack();
        }
    };
}
