package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Coach;

import java.time.LocalDate;

/**
 * Η αρχική οθόνη της εφαρμογής.
 * Επιτρέπει στον χρήστη να επιλέξει αν είναι προπονητής ή αθλητής.
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button btnTrainerSelect;
    private Button btnAthleteSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Προσθήκη ενός default προπονητή για δοκιμές (αν η λίστα είναι άδεια)
        addDefaultCoachIfEmpty();

        btnTrainerSelect = findViewById(R.id.btnTrainerSelect);
        btnAthleteSelect = findViewById(R.id.btnAthleteSelect);

        // Κουμπί Προπονητή
        btnTrainerSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("ROLE", "COACH");
                startActivity(intent);
            }
        });

        // Κουμπί Αθλητή:
        btnAthleteSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, AthleteWelcomeActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Δημιουργεί έναν προκαθορισμένο προπονητή αν δεν υπάρχει κανένας στο σύστημα.
     */
    private void addDefaultCoachIfEmpty() {
        DAOFactory factory = DAOFactory.getInstance();
        if (factory.getCoachDAO().findAll().isEmpty()) {
            Coach defaultCoach = new Coach(
                    1, 
                    "coach@test.com", 
                    "1234", 
                    "George",
                    "Zervas",
                    LocalDate.of(1985, 1, 1)
            );
            factory.getCoachDAO().save(defaultCoach);
        }
    }
}
