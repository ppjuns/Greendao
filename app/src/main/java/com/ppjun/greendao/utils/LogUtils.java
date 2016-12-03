package com.ppjun.greendao.utils;


import android.util.Log;

/**
 * Package :com.ppjun.greendao.utils
 * Description :
 * Author :Rc3
 * Created at :2016/12/2 10:54.
 */

public final class LogUtils {


     private static boolean isDebug = true;


    public static void setDebug(boolean isdebug) {
          isDebug = isdebug;

      }

    public static void i(String tag, String msg) {
         if (isDebug)
        Log.i(tag, msg);


    }

    public static void d(String tag, String msg) {
          if (isDebug)
        Log.d(tag, msg);


    }

    public static void e(String tag, String msg) {
     if (isDebug)
        Log.e(tag, msg);


    }


}
