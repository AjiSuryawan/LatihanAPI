package com.example.latihanapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edUsername;
    EditText edPassword;
    Button btnLogin;
    ProgressBar pbLoading;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getClient().create(ApiService.class);

        edUsername = (EditText) findViewById(R.id.edusername);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call API
                btnLogin.setVisibility(View.GONE);
                pbLoading.setVisibility(View.VISIBLE);
                Call<LoginResponse> callRequest = apiService.loginUser(edUsername.getText().toString(), edPassword.getText().toString());
                callRequest.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null ){
                            LoginResponse loginResponse = response.body();
                            Toast.makeText(MainActivity.this, "status login "+loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            btnLogin.setVisibility(View.VISIBLE);
                            pbLoading.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}