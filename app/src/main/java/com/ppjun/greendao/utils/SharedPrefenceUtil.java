package com.ppjun.greendao.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Package :com.ppjun.greendao.utils
 * Description :
 * Author :Rc3
 * Created at :2016/11/25 14:59.
 */

public class SharedPrefenceUtil {
    private static  SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences("init", Context.MODE_PRIVATE);
    }

    public static  boolean isInit(Context context){
        SharedPreferences sharedPreferences=getSharedPreferences(context);
        boolean isInit=sharedPreferences.getBoolean("init",false);
        return isInit;

    }
    public static void setInit(Context context){
        SharedPreferences sharedPreferences=getSharedPreferences(context);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("init",true);
        editor.commit();

    }
}
