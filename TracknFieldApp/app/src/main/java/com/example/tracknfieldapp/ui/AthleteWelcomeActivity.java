package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;

/**
 * Η οθόνη υποδοχής μονο για τους αθλητές.
 * Επιτρέπει στον αθλητή να επιλέξει μεταξύ σύνδεσης (Login) και εγγραφής (Sign Up).
 */

public class AthleteWelcomeActivity extends AppCompatActivity {

    private Button btnAthleteLogin, btnAthleteSignUp, btnAthleteBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_welcome);

        btnAthleteLogin = findViewById(R.id.btnAthleteLogin);
        btnAthleteSignUp = findViewById(R.id.btnAthleteSignUp);
        btnAthleteBack = findViewById(R.id.btnAthleteBack);

        //ΚΟΥΜΠΙ LOGIN 
        btnAthleteLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AthleteWelcomeActivity.this, AthleteLoginActivity.class);
                startActivity(intent);
                
            }
        });

        //ΚΟΥΜΠΙ ΕΓΓΡΑΦΗΣ
        btnAthleteSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AthleteWelcomeActivity.this, AthleteRegisterActivity.class);
                startActivity(intent);
            }
        });

        btnAthleteBack.setOnClickListener(v -> finish());
    }
}