package com.kodecaptain.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kodecaptain.myapplication.data.DataManagement;

public class TasbeehAdaptor extends RecyclerView.Adapter<TasbeehAdaptor.TasbeehViewHolder> {
    DataManagement dataManagement;

    public TasbeehAdaptor(Context applicationContext) {
        dataManagement = new DataManagement(applicationContext);
    }

    @NonNull
    @Override
    public TasbeehViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tasbeeh_item, parent, false);
        return new TasbeehViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasbeehViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Tasbeeh t = dataManagement.getTasbeehList().get(position);
        holder.setTextTitle(t.title);
        holder.setTextCount(t.count);
        holder.itemView.setOnClickListener(v -> {
            Intent ni = new Intent(context, CounterActivity.class);
            ni.putExtra(Keys.ID, t.id);
            ni.putExtra(Keys.TITLE, t.title);
            ni.putExtra(Keys.COUNT, t.count);
            context.startActivity(ni);
        });
    }

    @Override
    public int getItemCount() {
        return this.dataManagement.getTasbeehList().size();
    }

    class TasbeehViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textCount;

        public TasbeehViewHolder(View itemView) {
            super(itemView);
            this.textTitle = itemView.findViewById(R.id.text_tasbeeh_item_title);
            this.textCount = itemView.findViewById(R.id.text_tasbeeh_item_count);
        }

        public void setTextTitle(String title) {
            this.textTitle.setText(title);
        }

        public void setTextCount(int count) {
            this.textCount.setText(String.valueOf(count));
        }
    }
}
