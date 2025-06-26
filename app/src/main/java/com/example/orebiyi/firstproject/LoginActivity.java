package com.example.orebiyi.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth;

    TextInputEditText email;
    TextInputEditText password;
    MaterialButton login;
    TextView signup;


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

        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we will get the data from email and password input fields.
                String etEmail = email.getText().toString().trim();
                String etPassword = password.getText().toString().trim();



                //check if email and password are empty?
                if (etEmail.isEmpty()) {
                    // email.setError("Email is required");
                    Toasty.error(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPassword.isEmpty()) {
                    //password.setError("Password is required");
                    Toasty.error(LoginActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                //check email pattern
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail).matches()) {
                    //email.setError("Invalid email pattern");
                    Toasty.error(LoginActivity.this, "Invalid email pattern", Toast.LENGTH_SHORT).show();
                    return;
                }
                //password length
                if (etPassword.length() < 6) {
                    //password.setError("Password must be at least 6 characters");
                    Toasty.error(LoginActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(etEmail, etPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(i);
                                } else {
                                    Toasty.error(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        signup = findViewById(R.id.signup_text);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();
        if(user != null){
            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }
}