package com.ppjun.greendao.addeditnote;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ppjun.greendao.R;
import com.ppjun.greendao.shownote.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Package :com.ppjun.greendao.addeditnote
 * Description :
 * Author :Rc3
 * Created at :2016/11/24 17:05.
 */

public class AddEditActivity extends AppCompatActivity implements AddEditContract.View {
    private static final String TAG = "AddEditActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.ed_note_content)
    EditText mNoteContent;
    AddEditContract.Presenter mPresenter;
    /* 两种状态编辑和新增，编辑*/
    boolean canEdit;//编辑的√和编辑完的dlete
    boolean isAdd;//新增和编辑
    Note note;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addedit);
        ButterKnife.bind(this);
        mContext = this;
        initViews();
        getNote();
    }

    private void initViews() {

        mPresenter = new AddEditPresenter(this);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);


        mToolBar.setNavigationIcon(R.drawable.ic_left);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!canEdit) {
            menu.clear();

            getMenuInflater().inflate(R.menu.edit_menu, menu);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        if (itemId == R.id.menu_check) {
            if (TextUtils.isEmpty(mNoteContent.getText().toString())) {
                finish();
                return true;
            }

            Calendar calendar=Calendar.getInstance();

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM-dd  HH:mm");
            String time=simpleDateFormat.format(calendar.getTimeInMillis());
            if (isAdd) {

                mPresenter.addNote(new Note(null, mNoteContent.getText().toString(), time));


            } else {

                mPresenter.updateNote(note, mNoteContent.getText().toString(), time);


            }
            if (mNoteContent.isCursorVisible()) {
                mNoteContent.setCursorVisible(false);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mNoteContent.getWindowToken(), 0);
            }
            canEdit = false;
            invalidateOptionsMenu();
            note=mPresenter.queryNote();

        }else if(itemId==R.id.ic_delete){

            mPresenter
                    .deleteNote(mContext,note.getId());

        }
        return true;
    }

    @Override
    public void setPresenter(AddEditContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void getNote() {

        isAdd = getIntent().getBundleExtra("note").getBoolean("isAdd");

        if (!isAdd) {
            //不可编辑
            note = getIntent().getBundleExtra("note").getParcelable("content");
            if (note == null) {
                return;
            }

            mNoteContent.setText(note.getContent());
            mNoteContent.setCursorVisible(false);
            mNoteContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mNoteContent.setCursorVisible(true);
                    canEdit = true;
                    invalidateOptionsMenu();
                }
            });

        }else{
            canEdit = true;
        }

    }


}
