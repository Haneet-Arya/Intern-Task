package org.codejudge.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.codejudge.android.R;
import org.codejudge.android.dataModel.Rest;

import java.util.ArrayList;
import java.util.List;

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.RCVHolder> {
    List<Rest.Restaurants> list = new ArrayList<>();
    Context context;
    public RCVAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public RCVHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RCVHolder(LayoutInflater.from(context)
                .inflate(R.layout.recv_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RCVHolder rcvHolder, int i) {
        Rest.Restaurants temp = list.get(i);
        rcvHolder.textView.setText(temp.getName());
        Glide.with(context).load(temp.getIcon()).into(rcvHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void update(List<Rest.Restaurants> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    class RCVHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public RCVHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.restImage);
            textView = itemView.findViewById(R.id.restName);
        }
    }
}
