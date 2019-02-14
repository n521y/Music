package et.ts.wyymusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import et.ts.adapter.FragmentPagerAdapter1;

public class Music_main extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter1 fragmentPagerAdapter1;
    private DrawerLayout drawerLayout;

    private IntentFilter intentFilter;
    private AnReceiver anReceiver;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;


    //监听点击图片发出的广播

    class AnReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }



    public void bind(){

        viewPager=(ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(fragmentPagerAdapter1);
        viewPager.setCurrentItem(0);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);
        fragmentPagerAdapter1=new FragmentPagerAdapter1(getSupportFragmentManager());
        bind();

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.nly_drawerLayout);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.anniu.NLYCHANGER");
        anReceiver = new AnReceiver();
        registerReceiver(anReceiver,intentFilter);




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(anReceiver);
    }
}
