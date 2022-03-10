package com.example.nftmint.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nftmint.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    List<String> desc;
    LayoutInflater layoutInflater;
    List<String> data;

    public Adapter(Context context, List<String> data,List<String> desc)
    {
        this.layoutInflater= LayoutInflater.from(context);
        this.data=data;
        this.desc=desc;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String tittle = data.get(position);
        String tittle2 = desc.get(position);
        holder.name.setText(tittle);
        holder.name.setText(tittle2);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView2);
            desc = itemView.findViewById(R.id.textView4);
        }
    }
}
