package com.sanke46.android.e_commerce.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import com.bumptech.glide.Glide;

/**
 * Created by ilafedoseev on 27/01/2018.
 */

public class SalesRecyclerViewAdapter extends RecyclerView.Adapter<SalesRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Item> arr = new ArrayList<>();
    private int itemLayout;

    public SalesRecyclerViewAdapter(Context context, ArrayList<Item> arr, int itemLayout) {
        this.context = context;
        this.arr = arr;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item item = arr.get(position);
//        holder.imageView.setImageResource();
//        Glide.with(context).load(item.getImageUrl()).into(holder.imageView);
//        GlideU.load(item.getImageUrl()).into(holder.imageView);
        Picasso.with(context).load(item.getImageUrl()).into(holder.imageView);
        holder.price.setText(item.getPrice() + " $");
        holder.fixPrice.setText(item.getDiscontPrice() + " $");
        holder.name.setText(item.getName());
        holder.comment.setText(item.getComment());
        holder.gramm.setText(item.getGramms() + " g");
        holder.kal.setText(item.getKalories() + " kal");
        Log.v("ADAPTER", item.getKalories() + " !!!!!");
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView price;
        private TextView fixPrice;
        private TextView name;
        private TextView comment;
        private TextView gramm;
        private TextView kal;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSale);
            price = itemView.findViewById(R.id.price);
            kal = itemView.findViewById(R.id.kal);
            gramm = itemView.findViewById(R.id.gramm);
            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.comments);
            fixPrice = itemView.findViewById(R.id.fixPrice);
        }
    }
}
