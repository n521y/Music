package et.ts.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import et.ts.bean.Music;
import et.ts.wyymusic.R;

/**
 *
 */

public class MyAdapter extends RecyclerView.Adapter {
    public static final int TYPE_HEADER = 0;
    public static final  int TYPE_CONTENT = 1;
    private List<Music> mData;
    private ImageView mHeaderView;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_CONTENT){
            return  new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null));
        }else{
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout,null));
        }
    }

    public ImageView getHeaderView() {
        return mHeaderView;
    }

    public void setData(List<Music> data) {
        mData = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(getItemViewType(position) == TYPE_CONTENT){
           MyViewHolder myViewHolder = (MyViewHolder) holder;
           Log.d("musicImage",""+mData.get(position-1).getCoverPath());



           /*myViewHolder.musicImage.;*/

           myViewHolder.musictitle.setText(mData.get(position-1).title);
           myViewHolder.musicartist.setText(mData.get(position-1).getArtist()+"-"+mData.get(position-1).getAlbum());


       }else{
           Log.i("zhouwei","初始化。。。。。");
           mHeaderView = ((HeaderViewHolder)holder).headerImage;
       }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 1 : 1 + mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEADER : TYPE_CONTENT;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView musictitle;
        public TextView musicartist;
        public ImageView musicImage;


        public MyViewHolder(View itemView) {
            super(itemView);
            musictitle = (TextView) itemView.findViewById(R.id.tv_title);
            musicartist = (TextView) itemView.findViewById(R.id.tv_artist);
            musicImage=(ImageView)itemView.findViewById(R.id.iv_cover);
        }
    }



    public static class HeaderViewHolder extends RecyclerView.ViewHolder{
        public ImageView headerImage;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerImage = (ImageView) itemView.findViewById(R.id.header_image);
        }
    }
}
