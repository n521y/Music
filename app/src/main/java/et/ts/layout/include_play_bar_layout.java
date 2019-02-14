package et.ts.layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.Music_main;
import et.ts.wyymusic.R;

public class include_play_bar_layout extends FrameLayout {



    public include_play_bar_layout( Context context,  AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.include_play_bar,this);
        ImageView image=(ImageView)findViewById(R.id.iv_play_bar_cover);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.nly.ABCD");
                Log.d("ymy1","image");
                getContext().startActivity(intent);
            }
        });

    }
}
