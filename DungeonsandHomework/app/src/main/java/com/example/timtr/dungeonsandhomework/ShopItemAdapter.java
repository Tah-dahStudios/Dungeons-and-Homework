package com.example.timtr.dungeonsandhomework;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import models.Item;

/**
 * Created by Alex Chen on 2017-11-18.
 */

public class ShopItemAdapter extends ArrayAdapter<Item> {
    public ShopItemAdapter(@NonNull Context context, Item[] resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = getListItemView(convertView, parent);
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.list_item_name);
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.list_item_price);

        Item item = getItem(position);
        nameTextView.setText(item.getName());
        priceTextView.setText(String.format("%d coins", item.getPrice()));

        return listItemView;
    }

    private View getListItemView(@Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView != null) {
            return convertView;
        }
        return LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
    }

}
