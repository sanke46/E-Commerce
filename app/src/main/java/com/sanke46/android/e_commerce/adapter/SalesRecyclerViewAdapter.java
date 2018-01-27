package com.sanke46.android.e_commerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.ImageSales;

import java.util.ArrayList;

/**
 * Created by ilafedoseev on 27/01/2018.
 */

public class SalesRecyclerViewAdapter extends RecyclerView.Adapter<SalesRecyclerViewAdapter.ViewHolder> {

    private ArrayList arr = new ArrayList<ImageSales>();

    public SalesRecyclerViewAdapter(ArrayList arr) {
        this.arr = arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageSales imageSales = (ImageSales) arr.get(position);
        holder.imageView.setImageResource(imageSales.getImageSrc());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSale);
        }
    }


}
