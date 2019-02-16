package et.ts.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import et.ts.Decoration.SpacesItemDecoration;
import et.ts.Listener.OnItemClickListener;
import et.ts.adapter.RecyclerAdapter;
import et.ts.bean.Music;
import et.ts.ui.NeteasePlaylistActivity;
import et.ts.util.CommonUtils;
import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.Music_ListActivity;
import et.ts.wyymusic.R;

public class ViewPagefragment2 extends Fragment implements OnBannerListener ,OnItemClickListener {




    private Banner mBanner;
    private MyImageLoader mMyImageLoader;
    private ArrayList<Integer> imagePath;
    private ArrayList<String> imageTitle;
    //获取fragment2的组件
    private LinearLayout linearLayout1;


    private RecyclerView ry;
    private GridLayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    private static List<Music> mList;

    /**
     * 模拟本地数据
     */
    static {
        mList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "推荐歌单";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "先不要降温！我没钱买衣服";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "推荐MV";
            mList.add(music);
        }

        for (int i = 0; i < 4; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_TWO;
            music.imageId = R.drawable.ic_cover;
            music.title = "Perfect Day";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "精选专栏";
            mList.add(music);
        }

        for (int i = 0; i < 3; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_LIST;
            music.imageId = R.drawable.ic_cover;
            music.title = "去看《银翼杀手2049》前，你应该知道的三件事";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "最新音乐";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "[BGM]一定听过的神级背景配乐";
            mList.add(music);
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.viewpage2,container,false);
        mBanner = view.findViewById(R.id.banner);
        linearLayout1=view.findViewById(R.id.fragment_page2_1);






        //每日推介的监听器
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(view.getContext(),Music_ListActivity.class);

                Log.d("linearLayout1", "onClick: "+view.getContext());
                startActivity(intent);
            }
        });













        initData();
        initView();
        ry=(RecyclerView)view.findViewById(R.id.ry);
        layoutManager=new GridLayoutManager(view.getContext(),6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {

                int type=mList.get(i).type;
                if (type == Music.TYPE.TYPE_GRID_THREE) {
                    return 2;
                } else if (type == Music.TYPE.TYPE_GRID_TWO) {
                    return 3;
                } else if (type == Music.TYPE.TYPE_LIST) {
                    return 6;
                } else if (type == Music.TYPE.TYPE_TITLE) {
                    return 6;
                }

                return 0;
            }
        });

        ry.setLayoutManager(layoutManager);
        ry.addItemDecoration(new SpacesItemDecoration(2));

        // 填充数据
        mAdapter = new RecyclerAdapter(view.getContext(), mList);
        mAdapter.setOnItemClickListener(this);
        ry.setAdapter(mAdapter);
        Log.d("ymy","ViewPagefragment2");
        return view;

    }




    private void initData() {
        imagePath = new ArrayList<>();
        imageTitle = new ArrayList<>();
        imagePath.add(R.drawable.ic_timg);
        imagePath.add(R.drawable.ic_timg1);
        imagePath.add(R.drawable.ic_timg3);
        imageTitle.add("我是海鸟一号");
        imageTitle.add("我是海鸟二号");
        imageTitle.add("我是海鸟三号");
    }



    private void initView() {
        mMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(mMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(imageTitle);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }


    @Override
    public void OnBannerClick(int position) {

      Log.d("nn","mmmmmmmmmmmmmmmmmmmmm"+position);
    }



    @Override
    public void OnItemClick(int position) {
        String title = mList.get(position).title;
        Log.d("ymy",""+title);

    }




    //图片加载类
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

}
