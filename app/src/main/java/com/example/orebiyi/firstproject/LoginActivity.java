package com.example.orebiyi.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email, password;
    MaterialButton login;

    String stEmail, stPassword;

    @Override
    //oncreate method. the first method which is invoked/called when application starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //attached xml file with this java file
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // ids register
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.login);


        //implement onclick listener on login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting values from edit text and assigning to strings when user click on login button
                stEmail = email.getText().toString().trim();
                stPassword = password.getText().toString().trim();

                //toast message to show the values of email and password or you can show success message
                Toast.makeText(LoginActivity.this, stEmail + "\n" + stPassword, Toast.LENGTH_SHORT).show();

                //or you can show success message with toasty library
                Toasty.success(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();

                // moving to home page with the help of intent after successful login
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}