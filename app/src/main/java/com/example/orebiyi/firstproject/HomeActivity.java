package com.example.orebiyi.firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity {

    Button logout;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        auth=FirebaseAuth.getInstance();

        logout=findViewById(R.id.logut);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.success(HomeActivity.this, "Logout Successfully",
                        Toasty.LENGTH_SHORT).show();
                //signout method
                auth.signOut();

                //intent to move to the login page
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);

                //finish this page so that this can not be show when back button is pressed
                finish();
            }
        });

    }
}