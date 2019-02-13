package et.ts.layout;


import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.R;


public class music_tab_bar_layout extends FrameLayout  {

    public music_tab_bar_layout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.music_tab_bar,this);
        ImageView image=(ImageView)findViewById(R.id.iv_menu);
        ImageView image1=(ImageView)findViewById(R.id.iv_menu1);
        ImageView image2=(ImageView)findViewById(R.id.iv_menu2);
        ImageView image3=(ImageView)findViewById(R.id.iv_menu3);
        ImageView search=(ImageView)findViewById(R.id.iv_search);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent("android.anniu.NLYCHANGER");
             getContext().sendBroadcast(intent);

            }
        });

        image1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        image2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        image3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





    }

}
