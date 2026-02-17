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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Η οθόνη για τη δημιουργία ενός νέου προγράμματος προπόνησης για έναν αθλητή.
 */
public class CreateProgramActivity extends AppCompatActivity {

    private EditText etAthleteEmail, etTrainingDate, etTrainingTime;
    private Button btnSaveTraining, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_program);

        etAthleteEmail = findViewById(R.id.etAthleteEmail);
        etTrainingDate = findViewById(R.id.etTrainingDate);
        etTrainingTime = findViewById(R.id.etTrainingTime);
        btnSaveTraining = findViewById(R.id.btnSaveTraining);
        btnBack = findViewById(R.id.btnBackFromCreate);

        btnSaveTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etAthleteEmail.getText().toString().trim();
                String dateStr = etTrainingDate.getText().toString().trim();
                String timeStr = etTrainingTime.getText().toString().trim();

                if (email.isEmpty() || dateStr.isEmpty() || timeStr.isEmpty()) {
                    Toast.makeText(CreateProgramActivity.this, "Συμπληρώστε όλα τα πεδία!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    LocalDate date = LocalDate.parse(dateStr);
                    LocalTime time = LocalTime.parse(timeStr);
                    
                    Athlete athlete = DAOFactory.getInstance().getAthleteDAO().findByEmail(email);
                    if (athlete == null) {
                        Toast.makeText(CreateProgramActivity.this, "Ο αθλητής δεν βρέθηκε!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ScheduledTraining newTraining = new ScheduledTraining(date, time, new ArrayList<>());
                    newTraining.setAthlete(athlete);
                    athlete.addScheduledTraining(newTraining);

                    Toast.makeText(CreateProgramActivity.this, "Η προπόνηση αποθηκεύτηκε!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateProgramActivity.this, AddExerciseActivity.class);
                    intent.putExtra("ATHLETE_EMAIL", athlete.getEmail());
                    startActivity(intent);
                    finish();

                } catch (DateTimeParseException e) {
                    Toast.makeText(CreateProgramActivity.this, "Λάθος μορφή! Ημ: YYYY-MM-DD, Ώρα: HH:MM", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
