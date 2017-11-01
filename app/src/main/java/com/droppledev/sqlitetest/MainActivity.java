package com.droppledev.sqlitetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        ArrayList<Biodata> biodataList = dbHandler.getAllBiodata();
        ArrayList<Biodata> list = new ArrayList<>();
        for (Biodata biodata: biodataList){
            list.add(new Biodata(biodata.getId(),biodata.getName(),biodata.getLocation()));
        }
        //list.add(new Biodata(1,"coba","tc"));
        ListViewAdapter itemsAdapter = new ListViewAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);



    }
}
