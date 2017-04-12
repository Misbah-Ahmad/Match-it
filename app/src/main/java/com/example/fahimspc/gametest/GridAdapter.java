package com.example.fahimspc.gametest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 *
 * Created by fahim chowdhury on 2/10/2017.
 *
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> picIds;

    GridAdapter(Context context, ArrayList<Integer> picIds){
        this.context  = context;
        this.picIds = picIds;
    }

    @Override
    public int getCount() {
        return picIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return picIds.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ImageView imageView;
        if(view==null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {

            imageView = (ImageView) view;
        }
        imageView.setImageResource(picIds.get(position));
        return imageView;
    }
}
