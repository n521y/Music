package et.ts.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

/**
 * Created by jingbin on 2016/11/22.
 */

public class ScrollShapeUIApplication extends Application {

    private static ScrollShapeUIApplication cloudReaderApplication;

    public static ScrollShapeUIApplication getInstance() {
        Log.d("getInstance","hhhhhhhhhh"+cloudReaderApplication);
        return cloudReaderApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        Log.d("getInstance","aaaaaaaaaa521");

        super.onCreate();
        cloudReaderApplication = this;
    }



}
