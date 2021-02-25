package com.example.studentcook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    // SOURCE FOR GET DATA FROM RECYCLERVIEW
    private ArrayList<String> mImg = new ArrayList<>();
    private ArrayList<String> mImgName = new ArrayList<>();
    // -------------------------------------

    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> img, ArrayList<String> imgName) {
        this.mImg = img;
        this.mImgName = imgName;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create Class from layout_listitem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // здесь описывается все что ложится в холдер и действия над элементами холдера
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // помещаем картинку во ViewHolder с помощью библиотеки Gride
        Glide.with(mContext)
                .asBitmap()
                .load(mImg.get(position))
                .into(holder.ivItem);

        // помещаем текст во ViewHolder
        holder.tvItem.setText(mImgName.get(position));
    }

    @Override
    public int getItemCount() {
        return mImgName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layoutItem;
        ImageView ivItem;
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            ivItem = itemView.findViewById(R.id.ivItem);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}
