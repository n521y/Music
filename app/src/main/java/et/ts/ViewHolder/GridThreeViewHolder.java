package et.ts.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import et.ts.View.SquareImageView;
import et.ts.ui.NeteasePlaylistActivity;
import et.ts.wyymusic.R;


public class GridThreeViewHolder extends RecyclerView.ViewHolder {

    public SquareImageView iv_icon;
    public TextView tv_content;


    public GridThreeViewHolder(View itemView) {
        super(itemView);
        final Context context = itemView.getContext();
        final Activity activity = (Activity) context;
        iv_icon = (SquareImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);

        iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "hh22222222h" + context, Toast.LENGTH_SHORT).show();
                NeteasePlaylistActivity.start(activity, iv_icon, true);
            }
        });

        tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NeteasePlaylistActivity.start(activity, iv_icon, false);
            }
        });


    }

}





