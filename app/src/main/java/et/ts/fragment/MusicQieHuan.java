package et.ts.fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import et.ts.wyymusic.MainActivity;
import et.ts.wyymusic.R;

public class MusicQieHuan extends Fragment {

    private MusicGeCi musicGeCi;
    private View.OnClickListener lyricListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity mainActivity=(MainActivity)getActivity();
            mainActivity.ab=true;
            switchToFragment(R.id.music_detial,musicGeCi,true);

        }
    };




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        musicGeCi=new MusicGeCi();
        View view=inflater.inflate(R.layout.fragment_music_qiehuan,container,false);
        ImageView image=(ImageView)view.findViewById(R.id.Image);
        image.setOnClickListener(lyricListener);
        return view;
    }

    private void switchToFragment(int containerViewId,Fragment dfragment,boolean isAddedStack){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.add(containerViewId,dfragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if(isAddedStack){
            transaction.addToBackStack(null);
            transaction.commit();
        }




    }

}
