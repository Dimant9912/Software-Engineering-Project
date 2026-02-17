package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Η οθόνη για την έναρξη καταγραφής μιας νέας προπόνησης από τον αθλητή.
 */
public class LogWorkoutActivity extends AppCompatActivity {

    private EditText etWorkoutDate;
    private Button btnNextStep, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_workout);

        etWorkoutDate = findViewById(R.id.etWorkoutDate);
        btnNextStep = findViewById(R.id.btnNextStep);
        btnBack = findViewById(R.id.btnLogBack);

        etWorkoutDate.setText(LocalDate.now().toString());

        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateStr = etWorkoutDate.getText().toString();
                
                try {
                    LocalDate date = LocalDate.parse(dateStr);
                    
                    String athleteEmail = getIntent().getStringExtra("ATHLETE_EMAIL");
                    Athlete athlete = DAOFactory.getInstance().getAthleteDAO().findByEmail(athleteEmail);
                    
                    if (athlete == null) {
                        List<Athlete> all = DAOFactory.getInstance().getAthleteDAO().findAll();
                        if (!all.isEmpty()) {
                            athlete = all.get(0);
                        }
                    }

                    if (athlete == null) {
                        Toast.makeText(LogWorkoutActivity.this, "Σφάλμα: Δεν βρέθηκε αθλητής", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ScheduledTraining newSession = new ScheduledTraining(date, LocalTime.now(), new ArrayList<>());
                    athlete.addScheduledTraining(newSession);

                    Intent intent = new Intent(LogWorkoutActivity.this, AddExerciseActivity.class);
                    intent.putExtra("ATHLETE_EMAIL", athlete.getEmail());
                    startActivity(intent);
                    finish();

                } catch (Exception e) {
                    Toast.makeText(LogWorkoutActivity.this, "Λάθος μορφή ημερομηνίας (YYYY-MM-DD)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
