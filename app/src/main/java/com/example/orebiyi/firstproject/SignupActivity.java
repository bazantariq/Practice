package com.example.orebiyi.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText password;
    MaterialButton signup;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();


        name= findViewById(R.id.edit_name);
        email= findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        signup=findViewById(R.id.signup_button);
        login=findViewById(R.id.login_text);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etName = name.getText().toString().trim();
                String etEmail = email.getText().toString().trim();
                String etPassword = password.getText().toString().trim();

                if (etName.isEmpty()) {
                    // email.setError("Email is required");
                    Toasty.error(SignupActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etEmail.isEmpty()) {
                    // email.setError("Email is required");
                    Toasty.error(SignupActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etPassword.isEmpty()) {
                    //password.setError("Password is required");
                    Toasty.error(SignupActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                //check email pattern
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail).matches()) {
                    //email.setError("Invalid email pattern");
                    Toasty.error(SignupActivity.this, "Invalid email pattern", Toast.LENGTH_SHORT).show();
                    return;
                }
                //password length
                if (etPassword.length() < 6) {
                    //password.setError("Password must be at least 6 characters");
                    Toasty.error(SignupActivity.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(etEmail,etPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Toasty.success(SignupActivity.this,"Signup Success!",
                                            Toasty.LENGTH_SHORT).show();
                                    Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    Toasty.error(SignupActivity.this,"Something Went Wrong",
                                            Toasty.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }
}