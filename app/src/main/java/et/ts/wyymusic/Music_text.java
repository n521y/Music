/*
package et.ts.wyymusic;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.io.IOException;
import java.util.List;

import et.ts.util.LrcHandle;
import et.ts.view.WordView;

public class Music_text extends Activity {
    private WordView mWordView;
    private List mTimeList;
    private MediaPlayer mPlayer;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_txt);

Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mPlayer.stop();
                finish();
            }
        });


        mWordView = (WordView) findViewById(R.id.text);

        mPlayer = new MediaPlayer();
        mPlayer.reset();
        LrcHandle lrcHandler = new LrcHandle();
        try {
            lrcHandler.readLRC("/sdcard/wangyiMusic/成都_歌词.lrc");
            mTimeList = lrcHandler.getTime();
            mPlayer.setDataSource("/sdcard/wangyiMusic/cd.mp3");
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        final Handler handler = new Handler();
        mPlayer.start();
        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (mPlayer.isPlaying()) {
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            mWordView.invalidate();
                        }
                    });
                    try {
                        int jian=(int)mTimeList.get(i+1)-(int)mTimeList.get(i);
                        Thread.sleep(jian);
                    } catch (InterruptedException e) {
                    }
                    i++;
                    if (i == mTimeList.size() - 1) {
                        mPlayer.stop();
                        break;
                    }
                }
            }
        }).start();
    }
}
*/
