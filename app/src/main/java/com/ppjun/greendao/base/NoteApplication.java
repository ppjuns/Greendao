package com.ppjun.greendao.base;

import android.app.Application;

import com.ppjun.greendao.db.DaoMaster;
import com.ppjun.greendao.db.DaoSession;
import com.ppjun.greendao.db.NoteDao;

import static com.ppjun.greendao.utils.Constants.DB_NAME;

/**
 * Package :com.ppjun.greendao.base
 * Description :
 * Author :Rc3
 * Created at :2016/11/24 14:48.
 */

public class NoteApplication extends Application {

    static DaoSession mDaoSession;

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
    public static NoteDao getNoteDao(){return mDaoSession.getNoteDao();}

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        mDaoSession = daoMaster.newSession();
    }



}
