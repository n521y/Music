package et.ts.util;

import android.content.res.Resources;

import et.ts.app.ScrollShapeUIApplication;


/**
 *
 * 获取原生资源
 */
public class CommonUtils {


    private static Resources getResoure() {
        return ScrollShapeUIApplication.getInstance().getResources();
    }

    public static String[] getStringArray(int resid) {
        return getResoure().getStringArray(resid);
    }

    public static String getString(int resid) {
        return getResoure().getString(resid);
    }

    public static float getDimens(int resId) {
        return getResoure().getDimension(resId);
    }

}
