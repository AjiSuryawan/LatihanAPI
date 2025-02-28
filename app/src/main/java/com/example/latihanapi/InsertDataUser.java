package com.example.latihanapi;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InsertDataUser extends AppCompatActivity {

    EditText edName;
    EditText edEmail;
    Button btnSave;
    Button btnShowData;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_data_user);

        dbHelper = new DatabaseHelper(this);
        edEmail= findViewById(R.id.edEmail);
        edName= findViewById(R.id.edName);
        btnSave= findViewById(R.id.btnSave);
        btnShowData= findViewById(R.id.btnShowData);
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch and Display Users
                Cursor cursor = dbHelper.getAllUsers();
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String email = cursor.getString(2);
                        Log.d("DB_USER", "ID: " + id + ", Name: " + name + ", Email: " + email);
                    } while (cursor.moveToNext());
                    cursor.close();
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = dbHelper.insertUser(edName.getText().toString(), edEmail.getText().toString());
                if (inserted) {
                    Toast.makeText(InsertDataUser.this, edName.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(InsertDataUser.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}