package com.ppjun.greendao.shownote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppjun.greendao.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Package :com.ppjun.greendao.shownote
 * Description :
 * Author :Rc3
 * Created at :2016/11/23 16:35.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    Context mContext;
    int mLayoutId;
    List<Note> mList;
    OnitemClickListener mListener;

    public NoteAdapter(Context context, int layoutId, List<Note> list) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mList = list;
    }

   public void setList(List<Note> list) {
       this.mList = list;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {
        holder.mContent.setText(mList.get(position).getContent());
        holder.mDate.setText(mList.get(position).getDate());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mListener.onItemClick(position);
          }
      });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnitemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnitemClickListener {
        void onItemClick(int position);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_show_content)
        TextView mContent;
        @BindView(R.id.tv_show_date)
        TextView mDate;

        public NoteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
