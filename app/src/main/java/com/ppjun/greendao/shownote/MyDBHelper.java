package com.ppjun.greendao.shownote;

import android.content.Context;
import android.util.Log;

import com.ppjun.greendao.db.DaoMaster;
import com.ppjun.greendao.db.NoteDao;
import com.ppjun.greendao.utils.Constants;

import org.greenrobot.greendao.database.Database;

/**
 * Package :com.ppjun.greendao.shownote
 * Description :
 * Author :Rc3
 * Created at :2016/11/23 14:21.
 */

public class MyDBHelper extends DaoMaster.OpenHelper {
    public MyDBHelper(Context context) {
        super(context, Constants.DB_NAME,null);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        Log.i("TAG","Upgrade");
        NoteDao.createTable(db,true);
        db.execSQL("ALTER TABLE NOTE ADD COLUMN alias");
    }


}