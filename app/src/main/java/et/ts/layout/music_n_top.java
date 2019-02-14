package et.ts.layout;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import et.ts.wyymusic.R;

public class music_n_top extends LinearLayout {
    public music_n_top(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.music_n_top,this);
        ImageView image=(ImageView)findViewById(R.id.back);
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("android.intent.ABCDE");
                Log.d("ymy1","image2");
                getContext().startActivity(intent);
            }
        });



    }
}
