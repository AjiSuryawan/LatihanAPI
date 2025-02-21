package com.example.latihanapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity {

    EditText edusername;
    EditText edpassword;
    EditText edfullname;
    EditText edemail;
    Button btnregister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_page);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        edusername = findViewById(R.id.edusername);
        edpassword = findViewById(R.id.edpassword);
        edfullname = findViewById(R.id.edfullname);
        edemail = findViewById(R.id.edemail);
        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show progress bar
                // button gone
                Call<RegisterResponse> callRequest = apiService.registerUser(edusername.getText().toString(),
                        edpassword.getText().toString(),edfullname.getText().toString(),edemail.getText().toString());
                callRequest.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful() && response.body() != null ){
                            // gone progress bar
                            // button show
                            RegisterResponse registerResponse = response.body();
                            System.out.println();
                            Toast.makeText(RegisterPage.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterPage.this, "register error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}