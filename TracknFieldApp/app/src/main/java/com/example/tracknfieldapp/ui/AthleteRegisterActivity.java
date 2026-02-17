package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Invitation;

import java.time.LocalDate;
import java.util.List;

public class AthleteRegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etFirstName, etLastName, etInviteCode;
    private Button btnConfirm, btnBack;

    public static List<Athlete> registeredAthletes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athlete_register);

        etEmail = findViewById(R.id.etAthleteRegEmail);
        etPassword = findViewById(R.id.etAthleteRegPassword);
        etFirstName = findViewById(R.id.etAthleteRegFirstName);
        etLastName = findViewById(R.id.etAthleteRegLastName);
        etInviteCode = findViewById(R.id.etInviteCode);
        btnConfirm = findViewById(R.id.btnAthleteRegisterConfirm);
        btnBack = findViewById(R.id.btnAthleteRegisterBack);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail == null || etInviteCode == null) {
                    Toast.makeText(AthleteRegisterActivity.this, "Σφάλμα UI", Toast.LENGTH_SHORT).show();
                    return;
                }

                String fName = etFirstName.getText().toString().trim();
                String lName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();
                String code = etInviteCode.getText().toString().trim();

                if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || pass.isEmpty() || code.isEmpty()) {
                    Toast.makeText(AthleteRegisterActivity.this, "Συμπλήρωσε όλα τα πεδία!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DAOFactory factory = DAOFactory.getInstance();
                Invitation foundInvite = factory.getInvitationDAO().find(email);

                if (foundInvite != null && foundInvite.getInvitationCode().equals(code)) {
                    int randomId = (int) (Math.random() * 10000);

                    Athlete newAthlete = new Athlete(
                            randomId,
                            email,
                            pass,
                            fName,
                            lName,
                            foundInvite,
                            LocalDate.now()
                    );


                    factory.getInvitationDAO().delete(foundInvite);

                    Toast.makeText(AthleteRegisterActivity.this, "Εγγραφή επιτυχής! Κάνε Login.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(AthleteRegisterActivity.this, "Λάθος Email ή Κωδικός Πρόσκλησης", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
