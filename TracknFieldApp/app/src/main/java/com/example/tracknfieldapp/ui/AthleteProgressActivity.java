package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Performance;

/**
 *παρακολούθηση της προόδου του αθλητή.
 * Υπολογίζει και εμφανίζει το ποσοστό επιτυχίας και την απόδοσης.
 */
public class AthleteProgressActivity extends AppCompatActivity {

    private TextView tvSuccessRate;
    private TextView tvPerformanceComment;
    private Button btnUpdate;
    private Button btnBack;
    private Athlete currentAthlete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_progress);

        tvSuccessRate = findViewById(R.id.tvSuccessRate);
        tvPerformanceComment = findViewById(R.id.tvPerformanceComment);
        btnUpdate = findViewById(R.id.btnUpdateProgress);
        btnBack = findViewById(R.id.btnBackFromProgress);

        String email = getIntent().getStringExtra("ATHLETE_EMAIL");
        currentAthlete = DAOFactory.getInstance().getAthleteDAO().findByEmail(email);

        if (currentAthlete != null) {
            updateUI();
        }

        btnUpdate.setOnClickListener(v -> {
            if (currentAthlete != null) {
                updateUI();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }

    /**
     * Ενημερώνει τα στοιχεία της οθόνης με τα τρέχοντα δεδομένα απόδοσης του αθλητή.
     */
    private void updateUI() {
        Performance performance = new Performance(currentAthlete);
        
        double successRate = performance.SuccessRate();
        String comment = performance.getPerformance();

        tvSuccessRate.setText(String.format("%.1f%%", successRate));
        tvPerformanceComment.setText(comment);
    }
}
