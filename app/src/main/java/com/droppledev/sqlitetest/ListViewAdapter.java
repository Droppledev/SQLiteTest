package com.droppledev.sqlitetest;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ayasfn on 10/26/2017.
 */

public class ListViewAdapter extends ArrayAdapter<Biodata> {
    public ListViewAdapter(Activity context, ArrayList<Biodata> biodataList){
        super(context,0,biodataList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Biodata currentBiodata = getItem(position);

        TextView tvId = (TextView) listItemView.findViewById(R.id.tvId);
        tvId.setText(Integer.toString(currentBiodata.getId()));

        TextView tvNama = (TextView) listItemView.findViewById(R.id.tvNama);
        tvNama.setText(currentBiodata.getName());

        TextView tvLokasi = (TextView) listItemView.findViewById(R.id.tvLokasi);
        tvLokasi.setText(currentBiodata.getLocation());


        return listItemView;
    }


}
