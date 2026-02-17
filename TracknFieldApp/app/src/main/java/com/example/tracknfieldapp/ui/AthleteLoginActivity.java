package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;

public class AthleteLoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnBack;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_login);

        etEmail = findViewById(R.id.etAthleteLoginEmail);
        etPassword = findViewById(R.id.etAthleteLoginPassword);
        btnLogin = findViewById(R.id.btnAthleteLoginConfirm);
        btnBack = findViewById(R.id.btnAthleteLoginBack);
        tvError = findViewById(R.id.tvAthleteLoginError);

        // Κουμπί Σύνδεσης
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = etEmail.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                // Ψάχνουμε ΜΟΝΟ στους αθλητές
                DAOFactory factory = DAOFactory.getInstance();
                Athlete foundAthlete = factory.getAthleteDAO().findByEmail(inputEmail);

                if (foundAthlete != null && foundAthlete.getPassword().equals(inputPassword)) {
                    // ΕΠΙΤΥΧΙΑ! Πάμε στο Dashboard του Αθλητή
                    Intent intent = new Intent(AthleteLoginActivity.this, AthleteActivity.class);
                    intent.putExtra("ATHLETE_EMAIL", foundAthlete.getEmail());
                    startActivity(intent);
                    finish(); // Κλείνουμε το login
                } else {
                    // ΑΠΟΤΥΧΙΑ
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });

        // Κουμπί Πίσω
        btnBack.setOnClickListener(v -> finish());
    }
}