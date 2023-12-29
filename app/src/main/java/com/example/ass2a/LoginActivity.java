package com.example.ass2a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Retrieve the email from SharedPreferences and populate the EditText
        String savedEmail = sharedPreferences.getString("email", "");
        inputEmail.setText(savedEmail);

        Button btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(v -> checkCredentials());

        TextView btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Save the email to SharedPreferences when the activity is stopped
        String email = inputEmail.getText().toString().trim();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    private void checkCredentials() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(inputEmail, "Please enter a valid email address.");
        } else if (TextUtils.isEmpty(password) || password.length() < 7) {
            showError(inputPassword, "Password must be at least 7 characters.");
        } else {
            // Valid credentials, proceed to the next activity
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            openMainActivity();
        }
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showError(EditText input, String message) {
        input.setError(message);
        input.requestFocus();
    }
}
