package et.ts.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import et.ts.wyymusic.R;

public class music_n_top_layout extends LinearLayout {

    public music_n_top_layout(Context context,  AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.music_n_top,this);
    }
}
