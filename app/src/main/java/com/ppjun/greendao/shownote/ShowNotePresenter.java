package com.ppjun.greendao.shownote;

import android.content.Context;
import android.os.Bundle;

import com.ppjun.greendao.addeditnote.AddEditActivity;
import com.ppjun.greendao.base.NoteApplication;
import com.ppjun.greendao.db.NoteDao;
import com.ppjun.greendao.utils.IntentUtil;

import java.util.List;

/**
 * Package :com.ppjun.greendao.shownote
 * Description :
 * Author :Rc3
 * Created at :2016/11/23 11:30.
 */

public class ShowNotePresenter implements ShowNoteConstract.Presenter {

    private ShowNoteConstract.View mView;
    private NoteDao mNoteDao;

    public ShowNotePresenter(ShowNoteConstract.View view) {


        this.mView = view;
        this.mView.setPresenter(this);
        mNoteDao = NoteApplication.getNoteDao();
    }

    @Override
    public void add(Note note) {
        mNoteDao.insert(note);

    }



    @Override
    public List<Note> query() {
        List<Note> list = mNoteDao.queryBuilder().build().list();
        return list;

    }

    @Override
    public void toAddEditView(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isAdd", true);
        IntentUtil.startActivity(context, AddEditActivity.class, bundle);
    }

    @Override
    public void onDestroy() {
        this.mView = null;
    }
}