package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Coach;

import java.util.ArrayList;
import java.util.List;

/**
 * Η οθόνη για την προβολή των αθλητών που επιβλέπει ο προπονητής.
 */
public class ViewAthletesActivity extends AppCompatActivity {

    private ListView lvMyAthletes;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_athletes);

        lvMyAthletes = findViewById(R.id.lvMyAthletes);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        String coachEmail = getIntent().getStringExtra("COACH_EMAIL");
        DAOFactory factory = DAOFactory.getInstance();
        Coach currentCoach = factory.getCoachDAO().findByEmail(coachEmail);

        List<String> athleteNames = new ArrayList<>();
        List<Athlete> allAthletes = factory.getAthleteDAO().findAll();
        
        for (Athlete a : allAthletes) {
            athleteNames.add(a.getFirst_name() + " " + a.getLast_name() + " (" + a.getEmail() + ")");
        }

        if (athleteNames.isEmpty()) {
            Toast.makeText(this, "Δεν έχεις αθλητές στην ομάδα σου ακόμα.", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                athleteNames
        );
        lvMyAthletes.setAdapter(adapter);
    }
}
