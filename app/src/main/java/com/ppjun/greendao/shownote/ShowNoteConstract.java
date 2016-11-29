package com.ppjun.greendao.shownote;

import android.content.Context;

import com.ppjun.greendao.base.BasePresenter;
import com.ppjun.greendao.base.BaseView;

import java.util.List;

/**
 * Package :com.ppjun.greendao.shownote
 * Description :
 * Author :Rc3
 * Created at :2016/11/23 11:17.
 */

public interface ShowNoteConstract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void add(Note note);

        void delete(int id);

        void update();

        List<Note> query();

        void toAddEditView(Context context);
    }
}
