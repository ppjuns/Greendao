package com.ppjun.greendao.utils;

import com.ppjun.greendao.db.DaoMaster;
import com.ppjun.greendao.db.DaoSession;
import com.ppjun.greendao.db.NoteDao;

/**
 * Package :com.ppjun.greendao.utils
 * Description :
 * Author :Rc3
 * Created at :2016/11/24 14:38.
 */

public class DBManager {

    public static   DBManager mInstance;
    DaoMaster.DevOpenHelper helper;
    DaoMaster daoMaster;
    DaoSession daoSession;
    NoteDao noteDao;

    private DBManager() {




        daoMaster = new DaoMaster(helper.getWritableDb());
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();
    }

    public static DBManager getInstance() {
        if (mInstance == null) {

            synchronized (DBManager.class) {
                DBManager mIns = mInstance;
                if (mIns == null) {
                    mIns = new DBManager();
                    mInstance = mIns;

                }
            }
        }
        return mInstance;
    }


}
