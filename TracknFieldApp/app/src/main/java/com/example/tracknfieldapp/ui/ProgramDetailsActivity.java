package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import java.util.ArrayList;
import java.util.List;

/**
 * προβολή των λεπτομερειών μιας συγκεκριμένης προπόνησης.
 * Εμφανίζει τη λίστα των ασκήσεων και τις παραμέτρους τους (sets, reps, time, distance).
 */
public class ProgramDetailsActivity extends AppCompatActivity {

    private TextView tvSessionDate;
    private ListView lvExercises;
    private Button btnBack;
    private ScheduledTraining selectedSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_details);

        tvSessionDate = findViewById(R.id.tvSessionDateDetails);
        lvExercises = findViewById(R.id.lvExercisesDetails);
        btnBack = findViewById(R.id.btnBackFromDetails);

        List<Athlete> allAthletes = DAOFactory.getInstance().getAthleteDAO().findAll();
        if (!allAthletes.isEmpty()) {
            Athlete athlete = allAthletes.get(0);
            if (!athlete.getScheduled_trainings().isEmpty()) {
                selectedSession = athlete.getScheduled_trainings().get(0);
            }
        }

        if (selectedSession != null) {
            tvSessionDate.setText("Προπόνηση: " + selectedSession.getDate());
            
            List<String> exerciseDetails = new ArrayList<>();
            for (Exercise ex : selectedSession.getExercises()) {
                exerciseDetails.add(ex.getType().getTypeName() + 
                        "\nSets: " + ex.getSets() + " | Reps: " + ex.getReps() +
                        "\nΧρόνος: " + ex.getTime() + "s | Απόσταση: " + ex.getDistance() + "m");
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseDetails);
            lvExercises.setAdapter(adapter);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
