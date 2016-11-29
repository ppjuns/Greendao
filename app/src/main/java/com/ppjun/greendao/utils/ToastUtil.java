package com.ppjun.greendao.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Package :com.ppjun.greendao.utils
 * Description :
 * Author :Rc3
 * Created at :2016/11/28 15:11.
 */

public class ToastUtil {
    private static Toast toast = null;

    public static void showTextToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}
