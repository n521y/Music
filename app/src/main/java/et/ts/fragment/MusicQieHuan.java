package et.ts.fragment;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import et.ts.view.WordView;
import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.R;

public class MusicQieHuan extends Fragment {
    private Activity activity;
    private View view;
    private ObjectAnimator animator;
    private MusicGeCi musicGeCi;
    private ImageView image;
    /*private Timer timer=new Timer();
    private TimerTask timerTask;*/

    public MusicQieHuan() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=(MainActivity)getActivity();
    }

    private View.OnClickListener lyricListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switchToFragment(R.id.music_detial,musicGeCi,true);

        }
    };



    public ImageView getImage(){
        return image;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        musicGeCi=new MusicGeCi();
        view=inflater.inflate(R.layout.fragment_music_qiehuan,container,false);
        image=(ImageView)view.findViewById(R.id.Image);
        image.setOnClickListener(lyricListener);

        Log.d("niu","222222222ssssss"+((MainActivity) activity).ab);

        ObjectAnimatorMethod(image);

        Log.d("niu","onCreateViewMusicQieHuan");
        return view;
    }


    public MediaPlayer getMediaPlayer(){
        return ((MainActivity) activity).getMusicService().mediaPlayer;
    }

    @Override
    public void onResume() {
        super.onResume();
        timer.schedule(timerTask,10,500);
        Log.d("niu","onResumeMusicQieHuan");


    }


    private void  ObjectAnimatorMethod(ImageView image){
        animator = ObjectAnimator.ofFloat(image, "rotation", 0f, 360.0f);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);


    }

    private void switchToFragment(int containerViewId, Fragment dfragment, boolean isAddedStack){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.add(containerViewId,dfragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if(isAddedStack){
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    Handler handler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(animator.isRunning()==false){
                    animator.start();

                }else if(animator.isPaused()==true){
                    animator.resume();
                }

            }else {
                animator.pause();
                Log.d("niu","animator2"+animator.isStarted());
                Log.d("niu","animator2"+animator.isStarted());
                Log.d("niu","animator2start"+animator.isRunning());
            }
            super.handleMessage(msg);
        }
    };
    private Timer timer=new Timer();
    private TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Log.d("niu","timer"+((MainActivity) activity).ab);

            if(((MainActivity) activity).ab ){
                Message message=new Message();
                message.what=1;
                handler.sendMessage(message);
            }else{
                Message message=new Message();
                message.what=0;
                handler.sendMessage(message);

            }

        }
    };

}
