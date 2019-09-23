package com.sanke46.android.e_commerce.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilafedoseev on 24/07/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private ArrayList arr = new ArrayList<Item>();

    public RecyclerViewAdapter(ArrayList<Item> data) {
        this.arr = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list3, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = (Item) arr.get(position);
        holder.image.setImageResource(item.getImageId());
        holder.name.setText(item.getName());
//        holder.comment.setText(item.getComment());
        holder.price.setText((item.getPrice()) + " $");
//        holder.buttonOne.setText(item.getButtonOne());
        holder.buttonTwo.setText(item.getButtonTwo());
        holder.buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.add((Item) arr.get(position));
                basketActivity.setBasketItem(itemList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
//        private TextView comment;
        private TextView price;
//        private Button buttonOne;
        private Button buttonTwo;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
//            comment = (TextView) itemView.findViewById(R.id.comment);
            price = (TextView) itemView.findViewById(R.id.price);
//            buttonOne = (Button) itemView.findViewById(R.id.buttonOne);
            buttonTwo = (Button) itemView.findViewById(R.id.buttonTwo);
        }


    }
}
