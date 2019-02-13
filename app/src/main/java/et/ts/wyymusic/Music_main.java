package et.ts.wyymusic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Music_main extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private IntentFilter intentFilter;
    private AnReceiver anReceiver;


    //监听点击图片发出的广播

    class AnReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);
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
