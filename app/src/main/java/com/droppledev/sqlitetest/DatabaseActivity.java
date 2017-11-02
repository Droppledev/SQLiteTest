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
                if (etId.getText().toString().equals("")){
                    etId.setError("ID diperlukan untuk edit data!");
                }
                else {
                    name = etName.getText().toString();
                    location = etLocation.getText().toString();
                    id = Integer.parseInt(etId.getText().toString());
                    dbHandler.updateBiodata(new Biodata(id, name, location));
                    Toast.makeText(DatabaseActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etId.getText().toString().equals("")){
                    etId.setError("ID diperlukan untuk delete data!");
                }
                else {
                    id = Integer.parseInt(etId.getText().toString());
                    dbHandler.deleteBiodata(new Biodata(id));
                }
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etId.getText().toString().equals("")){
                    etId.setError("ID diperlukan untuk get data!");
                }
                else {
                    id = Integer.parseInt(etId.getText().toString());
                    ArrayList<Biodata> biodataList = dbHandler.getAllBiodata();
                    for (Biodata biodata : biodataList) {
                        if (biodata.getId() == id) {
                            etName.setText(biodata.getName());
                            etLocation.setText(biodata.getLocation());
                            break;
                        }
                    }
                }

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
