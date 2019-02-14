package et.ts.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import et.ts.fragment.ViewPagefragment1;
import et.ts.fragment.ViewPagefragment2;
import et.ts.fragment.ViewPagefragment3;
import et.ts.wyymusic.Music_main;

public class FragmentPagerAdapter1 extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private ViewPagefragment1 myFragment1 = null;
    private ViewPagefragment2 myFragment2 = null;
    private ViewPagefragment3 myFragment3 = null;

    public FragmentPagerAdapter1(FragmentManager fm) {
        super(fm);
        myFragment1=new ViewPagefragment1();
        myFragment2=new ViewPagefragment2();
        myFragment3=new ViewPagefragment3();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment=null;
        switch(i){
            case Music_main
                    .PAGE_ONE:
                fragment=myFragment1;
                break;
            case Music_main
                    .PAGE_TWO:
                fragment=myFragment2;
                break;
            case Music_main
                    .PAGE_THREE:
                fragment=myFragment3;
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
