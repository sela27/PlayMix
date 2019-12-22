package com.example.playmix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.LinkedList;

public class GameAdapter extends ArrayAdapter<Game> {

    LinkedList<Game> games;
    public GameAdapter(@NonNull Context context, int resource,LinkedList<Game> games) {
        super(context, resource);
        this.games = games;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        /*
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.items_list_item, null);
        }

        Item it = items[position];
        if (it != null) {
            ImageView iv = (ImageView) v.findViewById(R.id.list_item_image);
            if (iv != null) {
                iv.setImageDrawable(it.getImage());
            }
        }

        return v;   */
        return convertView;
    }
}

