package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ExerciseType;

import java.util.List;

/**
 * Η οθόνη για την προσθήκη  ασκήσεων σε μια προπόνηση.
 */
public class AddExerciseActivity extends AppCompatActivity {

    private EditText etExerciseName, etSets, etReps, etTime, etDistance, etComments;
    private Button btnAddExercise, btnFinishExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        etExerciseName = findViewById(R.id.etExerciseName);
        etSets = findViewById(R.id.etSets);
        etReps = findViewById(R.id.etReps);
        etTime = findViewById(R.id.etTime);
        etDistance = findViewById(R.id.etDistance);
        etComments = findViewById(R.id.etExerciseComments);
        btnAddExercise = findViewById(R.id.btnAddExercise);
        btnFinishExercise = findViewById(R.id.btnFinishExercise);

        btnAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveExercise()) {
                    clearFields();
                    Toast.makeText(AddExerciseActivity.this, "Η άσκηση προστέθηκε!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFinishExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Καθαρίζει τα πεδία εισαγωγής.
     */
    private void clearFields() {
        etExerciseName.setText("");
        etSets.setText("");
        etReps.setText("");
        etTime.setText("");
        etDistance.setText("");
        etComments.setText("");
    }

    /**
     * Αποθηκεύει την άσκηση στην τρέχουσα προπόνηση και επιστρέφει true αν η αποθήκευση ήταν επιτυχής.

     */
    private boolean saveExercise() {
        try {
            String name = etExerciseName.getText().toString();
            int sets = Integer.parseInt(etSets.getText().toString());
            int reps = Integer.parseInt(etReps.getText().toString());
            double time = Double.parseDouble(etTime.getText().toString());
            double distance = Double.parseDouble(etDistance.getText().toString());

            if (name.isEmpty()) return false;

            ExerciseType type = new ExerciseType(name);
            Exercise e = new Exercise(type, reps, sets, time, distance);

            String email = getIntent().getStringExtra("ATHLETE_EMAIL");
            Athlete athlete = DAOFactory.getInstance().getAthleteDAO().findByEmail(email);
            
            if (athlete == null) {
                List<Athlete> all = DAOFactory.getInstance().getAthleteDAO().findAll();
                if (!all.isEmpty()) athlete = all.get(0);
            }

            if (athlete != null && !athlete.getScheduled_trainings().isEmpty()) {
                athlete.getScheduled_trainings().get(athlete.getScheduled_trainings().size()-1).addExercises(e);
                return true;
            }
            
            return false;
        } catch (Exception e) {
            Toast.makeText(this, "Συμπληρώστε όλα τα πεδία με αριθμούς", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
