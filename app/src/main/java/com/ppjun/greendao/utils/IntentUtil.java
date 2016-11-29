package com.ppjun.greendao.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Package :com.ppjun.greendao.utils
 * Description :
 * Author :Rc3
 * Created at :2016/11/25 10:13.
 */

public class IntentUtil {
public static void startActivity(Context context,Class clz){
    Intent intent=new Intent(context,clz);
    context.startActivity(intent);
}

    public static void startActivity(Context context, Class clz, Bundle bundle){
        Intent intent=new Intent(context,clz);
        intent.putExtra("note",bundle);
         context.startActivity(intent);
    }
}
