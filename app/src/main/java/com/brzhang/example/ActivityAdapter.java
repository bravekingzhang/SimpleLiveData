package com.brzhang.example;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by brzhang on 2017/10/15.
 * Description :
 */

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.PageViewHolder> {

    private List<PageData> list;

    public void setList(List<PageData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.page_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PageViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getmPageName());
        holder.textView.setTag(list.get(position).getmClazz());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), (Class<?>) v.getTag());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public PageViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }

    }
}



