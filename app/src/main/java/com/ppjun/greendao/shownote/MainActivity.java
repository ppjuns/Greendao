package com.ppjun.greendao.shownote;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.ppjun.greendao.R;
import com.ppjun.greendao.addeditnote.AddEditActivity;
import com.ppjun.greendao.utils.IntentUtil;
import com.ppjun.greendao.utils.SharedPrefenceUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ShowNoteConstract.View {

    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_add)
    FloatingActionButton mAdd;
    Context mContext;
    ShowNoteConstract.Presenter mPresenter;
    List<Note> mList;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = new ShowNotePresenter(this);


        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPrefenceUtil.isInit(mContext)) {
            mList.clear();
            mList.addAll(mPresenter.query());
            for (int i = 0; i < mList.size(); i++) {
                Log.i(TAG, "MainActivity.onResume" + mList.get(i).toString());
            }

            Log.i(TAG, "MainActivity.onResume" + mList.size());
            adapter.notifyDataSetChanged();
        } else {
            SharedPrefenceUtil.setInit(mContext);
        }


    }

    private void initViews() {
        setSupportActionBar(mToolbar);
        addAnimator(0f, 1f);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mList = new ArrayList<Note>();


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        if (SharedPrefenceUtil.isInit(mContext)) {
            mList = mPresenter.query();

        } else {

            Log.i(TAG, "MainActivity.initViews first");
            Note tipNote = new Note(null, "使用指南", "现在");
            mList.add(tipNote);
            mPresenter.add(tipNote);
            Log.i(TAG, "MainActivity.initViews" + mPresenter.query().get(0).getId());
        }

        adapter = new NoteAdapter(mContext, R.layout.item_note, mList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            boolean isScroll = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!isScroll) {
                    isScroll = true;
                    //animatorMiss();
                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isScroll) {
                    // animatorShow();
                    isScroll = false;
                }
            }
        });

        adapter.setOnItemClickListener(new NoteAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(int position) {
                Note note = mList.get(position);
                for (int i = 0; i < mList.size(); i++) {
                    Log.i(TAG, "MainActivity.onResume" + mList.get(i).toString());
                }
                Log.i(TAG, "MainActivity.onItemClick" + note.getId());
                Bundle bundle = new Bundle();
                bundle.putBoolean("isAdd", false);
                bundle.putParcelable("content", note);
                IntentUtil.startActivity(mContext, AddEditActivity.class, bundle);
            }
        });


    }

    @Override
    public void setPresenter(ShowNoteConstract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                List<Note> newList = new ArrayList<Note>();

                for (Note note : mList) {
                    if (note.getContent().contains(newText)) {
                        newList.add(note);

                    }
                }
                adapter.setList(newList);
                mList=newList;
                adapter.notifyDataSetChanged();

                return true;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_add)
    void add(View view) {
        mPresenter.toAddEditView(mContext);


    }


    private void addAnimator(float from, float to) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(mAdd, "scaleX", from, to);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(mAdd, "scaleY", from, to);
        set.setDuration(300);
        set.setInterpolator(new DecelerateInterpolator());
        set.play(objectAnimatorX).with(objectAnimatorY);
        set.start();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.i(TAG, "MainActivity.handleIntent" + query);
        }

    }
}
