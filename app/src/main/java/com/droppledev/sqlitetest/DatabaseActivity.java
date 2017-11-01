package com.droppledev.sqlitetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droppledev.sqlitetest.DBHandler;
import com.droppledev.sqlitetest.R;

import java.util.ArrayList;
import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    private EditText etId,etName,etLocation;
    private Button btnAdd,btnEdit,btnDelete,btnShow,btnGet;
    private DBHandler dbHandler;
    private String name,location;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        dbHandler = new DBHandler(this);
        Log.d("oncreate", "onCreate: ");
        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnShow = (Button) findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                location = etLocation.getText().toString();
                dbHandler.addBiodata(new Biodata(name,location));
                Toast.makeText(DatabaseActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                location = etLocation.getText().toString();
                id = Integer.parseInt(etId.getText().toString());
                dbHandler.updateBiodata(new Biodata(id,name,location));
                Toast.makeText(DatabaseActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Integer.parseInt(etId.getText().toString());
                dbHandler.deleteBiodata(new Biodata(id));
                Toast.makeText(DatabaseActivity.this, "Success", Toast.LENGTH_SHORT).show();

            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = Integer.parseInt(etId.getText().toString());
                ArrayList<Biodata> biodataList = dbHandler.getAllBiodata();
                //Log.d("btnGet", "Belum For ");
                for (Biodata biodata: biodataList){
                    if (biodata.getId() == id) {
                        etName.setText(biodata.getName(),TextView.BufferType.EDITABLE);
                        etLocation.setText(biodata.getLocation(),TextView.BufferType.EDITABLE);
                        //Log.d("btnGet", "masuk if ");
                        //Toast.makeText(DatabaseActivity.this, "masuk if", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                //Log.d("btnGet", "sudah For ");

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatabaseActivity.this,MainActivity.class));
            }
        });

    }
}
