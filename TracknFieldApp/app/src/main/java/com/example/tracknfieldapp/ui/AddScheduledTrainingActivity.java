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
 * Η οθόνη για την προσθήκη μιας νέας προγραμματισμένης προπόνησης από τον προπονητή.
 * Επιτρέπει στον προπονητή να ορίσει την ημερομηνία και να προσθέσει ασκήση-εις.
 */
public class AddScheduledTrainingActivity extends AppCompatActivity {

    private EditText etSessionDate;
    private Button btnAddSession, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scheduled_training);

        etSessionDate = findViewById(R.id.etSessionDate);
        btnAddSession = findViewById(R.id.btnAddSession);
        btnBack = findViewById(R.id.btnAddSessionBack);

        btnAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateStr = etSessionDate.getText().toString().trim();

                if (dateStr.isEmpty()) {
                    Toast.makeText(AddScheduledTrainingActivity.this, "Παρακαλώ εισάγετε ημερομηνία", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    LocalDate date = LocalDate.parse(dateStr);
                    
                    ScheduledTraining newTraining = new ScheduledTraining(date, LocalTime.of(18, 0), new ArrayList<>());
                    
                    List<Athlete> allAthletes = DAOFactory.getInstance().getAthleteDAO().findAll();
                    if (!allAthletes.isEmpty()) {
                        Athlete athlete = allAthletes.get(0);
                        newTraining.setAthlete(athlete);
                        athlete.addScheduledTraining(newTraining);
                        
                        Toast.makeText(AddScheduledTrainingActivity.this, "Η προπόνηση προστέθηκε!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddScheduledTrainingActivity.this, AddExerciseActivity.class);
                        intent.putExtra("ATHLETE_EMAIL", athlete.getEmail());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddScheduledTrainingActivity.this, "Δεν βρέθηκε αθλητής στη βάση!", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(AddScheduledTrainingActivity.this, "Λάθος μορφή ημερομηνίας (YYYY-MM-DD)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
