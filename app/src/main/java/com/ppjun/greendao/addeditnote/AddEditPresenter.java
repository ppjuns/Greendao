package com.ppjun.greendao.addeditnote;

import android.content.Context;
import android.util.Log;

import com.ppjun.greendao.R;
import com.ppjun.greendao.base.NoteApplication;
import com.ppjun.greendao.db.NoteDao;
import com.ppjun.greendao.shownote.Note;
import com.ppjun.greendao.utils.ToastUtil;

import java.util.List;

/**
 * Package :com.ppjun.greendao.addeditnote
 * Description :
 * Author :Rc3
 * Created at :2016/11/24 17:12.
 */

public class AddEditPresenter implements AddEditContract.Presenter {
    private static final String TAG = "AddEditPresenter";

    private NoteDao mNoteDao;
    private AddEditContract.View mView;

    public AddEditPresenter(AddEditContract.View view) {
        this.mView = view;
        this.mView.setPresenter(this);
        mNoteDao = NoteApplication.getNoteDao();
    }

    @Override
    public void addNote(Note note) {
        mNoteDao.insert(note);
    }

    @Override
    public Note queryNote() {
        List<Note> list = mNoteDao.queryBuilder().build().list();

        return list.get(list.size() - 1);

    }

    @Override
    public void deleteNote(Context context, Long id) {
        Note note = mNoteDao.queryBuilder().where(NoteDao.Properties.Id.eq(id)).build().unique();


        mNoteDao.delete(note);
        ToastUtil.showTextToast(context, context.getResources().getString(R.string.delete_success));

        mView.finishView();

    }

    @Override
    public void updateNote(Note note, String content, String date) {
        Log.i(TAG, "AddEditPresenter.updateNote" + mNoteDao.getKey(note));


        note.setContent(content);

        note.setDate(date);
        mNoteDao.update(note);

    }
}
