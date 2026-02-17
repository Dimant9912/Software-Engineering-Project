package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.Invitation;

/**
 * Η οθόνη για την αποστολή πρόσκλησης από έναν προπονητή σε έναν αθλητή.
 * Δημιουργεί έναν μοναδικό κωδικό πρόσκλησης που ο αθλητής θα χρησιμοποιήσει για την εγγραφή του.
 */
public class InviteAthleteActivity extends AppCompatActivity {

    private EditText etAthleteEmail;
    private Button btnSendInvite, btnInviteBack;
    private TextView tvInviteResult;
    private Coach currentCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_athlete);

        etAthleteEmail = findViewById(R.id.etAthleteEmail);
        btnSendInvite = findViewById(R.id.btnSendInvite);
        btnInviteBack = findViewById(R.id.btnInviteBack);
        tvInviteResult = findViewById(R.id.tvInviteResult);

        String coachEmail = getIntent().getStringExtra("COACH_EMAIL");
        DAOFactory factory = DAOFactory.getInstance();
        currentCoach = factory.getCoachDAO().findByEmail(coachEmail);

        btnSendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String athleteEmail = etAthleteEmail.getText().toString().trim();
                if (athleteEmail.isEmpty()) {
                    Toast.makeText(InviteAthleteActivity.this, "Γράψε το email του αθλητή!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (currentCoach != null) {
                    // 1. Δημιουργία πρόσκλησης
                    Invitation invite = currentCoach.inviteAthlete(athleteEmail);
                    
                    // 2. Αποθήκευση μέσω DAO
                    factory.getInvitationDAO().save(invite);
                    
                    tvInviteResult.setText("ΚΩΔΙΚΟΣ: " + invite.getInvitationCode() + "\nEmail Αθλητή: " + athleteEmail);
                    tvInviteResult.setVisibility(View.VISIBLE);
                    Toast.makeText(InviteAthleteActivity.this, "Η πρόσκληση στάλθηκε και αποθηκεύτηκε!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InviteAthleteActivity.this, "Σφάλμα: Δεν βρέθηκε ο προπονητής", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnInviteBack.setOnClickListener(v -> finish());
    }
}
