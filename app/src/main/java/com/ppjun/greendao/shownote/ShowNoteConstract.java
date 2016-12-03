package com.ppjun.greendao.shownote;

import android.content.Context;
import android.view.Menu;

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
        void onItemClick();

        void onCreateMenu(Menu menu);

        void onRefeshAdapter();


    }

    interface Presenter extends BasePresenter {
        void add(Note note);



        List<Note> query();

        void toAddEditView(Context context);

        void onDestroy();
    }
}
