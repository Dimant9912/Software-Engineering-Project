package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;

/**
 * Η κύρια οθόνη του αθλητή
 * Επιτρέπει στον αθλητή να δει το πρόγραμμα προπονήσεών του, να καταγράψει μια προπόνηση
 * και να παρακολουθήσει την πρόοδο και την απόδοσή του.
 */
public class AthleteActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnViewCalendar, btnLogWorkout, btnPerformance, btnLogout;
    private Athlete currentAthlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete);

        tvWelcome = findViewById(R.id.tvAthleteWelcome);
        btnViewCalendar = findViewById(R.id.btnViewCalendar);
        btnLogWorkout = findViewById(R.id.btnLogWorkout);
        btnPerformance = findViewById(R.id.btnPerformance);
        btnLogout = findViewById(R.id.btnLogout);

        String email = getIntent().getStringExtra("ATHLETE_EMAIL");
        currentAthlete = DAOFactory.getInstance().getAthleteDAO().findByEmail(email);

        if (currentAthlete != null) {
            tvWelcome.setText("Καλώς ήρθες, " + currentAthlete.getFirst_name() + "!");
        }

        btnViewCalendar.setOnClickListener(v -> {
            Intent intent = new Intent(AthleteActivity.this, ViewProgramsActivity.class);
            intent.putExtra("ATHLETE_EMAIL", email);
            startActivity(intent);
        });

        btnLogWorkout.setOnClickListener(v -> {
            Intent intent = new Intent(AthleteActivity.this, LogWorkoutActivity.class);
            intent.putExtra("ATHLETE_EMAIL", email);
            startActivity(intent);
        });

        btnPerformance.setOnClickListener(v -> {
            Intent intent = new Intent(AthleteActivity.this, AthleteProgressActivity.class);
            intent.putExtra("ATHLETE_EMAIL", email);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(AthleteActivity.this, WelcomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
