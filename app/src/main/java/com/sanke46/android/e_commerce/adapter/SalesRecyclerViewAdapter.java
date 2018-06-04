package com.sanke46.android.e_commerce.adapter;

        import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sanke46.android.e_commerce.R;
import com.sanke46.android.e_commerce.model.Item;
import com.sanke46.android.e_commerce.ui.navigation.BasketActivity;
import com.sanke46.android.e_commerce.ui.navigation.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SalesRecyclerViewAdapter extends RecyclerView.Adapter<SalesRecyclerViewAdapter.ViewHolder> {

    BasketActivity basketActivity = new BasketActivity();
    private List<Item> itemList = basketActivity.getBasketItem();
    private ArrayList arr;
    private Context mContext;
    private int itemLayout;

    public SalesRecyclerViewAdapter(Context context, ArrayList<Item> data, int itemLayout) {
        this.arr = data;
        mContext = context;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item item = (Item) arr.get(position);

        Picasso.with(mContext).load(item.getImageUrl()).into(holder.imageView);
        holder.price.setText(item.getPrice() + " $");
        holder.price.setPaintFlags(holder.price.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.fixPrice.setText(item.getDiscontPrice() + " $");
        holder.name.setText(item.getName());
        holder.comment.setText(item.getComment());
        holder.gramm.setText(item.converGramms(String.valueOf(item.getGramms())));
        holder.kal.setText(item.getKalories() + " kal");
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.add((Item) arr.get(position));
                basketActivity.setBasketItem(itemList);
            }
        });
        holder.linerSaleClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("item", item);
                mContext.startActivity(intent);
                Log.v("SALES", "SALES");
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linerSaleClick;
        private ImageView imageView;
        private TextView price;
        private TextView fixPrice;
        private TextView name;
        private TextView comment;
        private TextView gramm;
        private TextView kal;
        private Button addToCart;

        public ViewHolder(View itemView) {
            super(itemView);
            linerSaleClick = itemView.findViewById(R.id.linerSaleClick);
            imageView = itemView.findViewById(R.id.imageSale);
            price = itemView.findViewById(R.id.price);
            kal = itemView.findViewById(R.id.kal);
            gramm = itemView.findViewById(R.id.gramm);
            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.comments);
            fixPrice = itemView.findViewById(R.id.fixPrice);
            addToCart = itemView.findViewById(R.id.buttonTwo);
        }
    }
}
