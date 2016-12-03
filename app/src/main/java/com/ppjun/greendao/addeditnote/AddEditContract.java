package com.ppjun.greendao.addeditnote;

import android.content.Context;

import com.ppjun.greendao.base.BasePresenter;
import com.ppjun.greendao.base.BaseView;
import com.ppjun.greendao.shownote.Note;

/**
 * Package :com.ppjun.greendao.addeditnote
 * Description :
 * Author :Rc3
 * Created at :2016/11/24 17:06.
 */

public interface AddEditContract {


    interface View extends BaseView<Presenter> {
       void  finishView();
    }

    interface Presenter extends BasePresenter {
        void addNote(Note note);

        Note queryNote();

        void deleteNote(Context context, Long id);

        void updateNote(Note note, String content, String date);
    }
}
