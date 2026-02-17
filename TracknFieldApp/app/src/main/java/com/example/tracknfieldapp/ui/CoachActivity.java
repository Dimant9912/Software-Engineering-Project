package com.example.tracknfieldapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Coach;

/**
 * Η κύρια οθόνη για τον προπονητή.
 * Παρέχει πρόσβαση στις λειτουργίες πρόσκλησης αθλητών, δημιουργίας προγραμμάτων
 * και προβολής στατιστικών.
 */
public class CoachActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnInviteAthlete, btnCreateProgram, btnViewPrograms, btnViewAthletes, btnLogout;
    private Coach currentCoach;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnInviteAthlete = findViewById(R.id.btnInviteAthlete);
        btnCreateProgram = findViewById(R.id.btnCreateProgram);
        btnViewPrograms = findViewById(R.id.btnViewPrograms);
        btnViewAthletes = findViewById(R.id.btnViewAthletes);
        btnLogout = findViewById(R.id.btnCoachLogout);

        String coachEmail = getIntent().getStringExtra("COACH_EMAIL");
        currentCoach = DAOFactory.getInstance().getCoachDAO().findByEmail(coachEmail);

        if (currentCoach != null) {
            tvWelcome.setText("Dashboard: " + currentCoach.getFirst_name() + " " + currentCoach.getLast_name());
        }

        btnInviteAthlete.setOnClickListener(v -> {
            Intent intent = new Intent(CoachActivity.this, InviteAthleteActivity.class);
            intent.putExtra("COACH_EMAIL", coachEmail);
            startActivity(intent);
        });

        btnCreateProgram.setOnClickListener(v -> {
            Intent intent = new Intent(CoachActivity.this, AddScheduledTrainingActivity.class);
            intent.putExtra("COACH_EMAIL", coachEmail);
            startActivity(intent);
        });

        btnViewPrograms.setOnClickListener(v -> {
            Intent intent = new Intent(CoachActivity.this, ViewProgramsActivity.class);
            intent.putExtra("COACH_EMAIL", coachEmail);
            startActivity(intent);
        });

        btnViewAthletes.setOnClickListener(v -> {
            Intent intent = new Intent(CoachActivity.this, ViewAthletesActivity.class);
            intent.putExtra("COACH_EMAIL", coachEmail);
            startActivity(intent);
        });

        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                Intent intent = new Intent(CoachActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }
    }
}
