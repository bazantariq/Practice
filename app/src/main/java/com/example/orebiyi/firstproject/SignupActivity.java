package com.example.orebiyi.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignupActivity extends AppCompatActivity {

    EditText name, email, password, cpassword;
    Button signup;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        //attachment with ids
        name= findViewById(R.id.fullname);
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        cpassword= findViewById(R.id.cpassword);
        signup= findViewById(R.id.bt_signup);
        login= findViewById(R.id.tvlogin);

        //Intent - used to navigate between activities
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //define your action when user click
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        //signup button on click listner
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignupActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}