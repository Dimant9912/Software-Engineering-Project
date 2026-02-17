package com.example.tracknfieldapp.ui;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuWrapperICS;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; 

import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Invitation; 
import java.time.LocalDate;

/**
 * Η κλάση MainActivity διαχειρίζεται τη διαδικασία σύνδεσης (Login) των χρηστών.
 * Επιτρέπει σε προπονητές και αθλητές να εισέλθουν στο σύστημα ή σε νέους αθλητές
 * να ξεκινήσουν την εγγραφή τους μέσω κωδικού πρόσκλησης Που έχει σταλθεί απο εναν προπονητή.
 */

public class MainActivity extends AppCompatActivity {

    public static MenuWrapperICS registeredAthletes;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnBack; 
    private TextView tvError, tvRegisterLink;
    
    
    private String role = "COACH"; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createDummyData(); 

        
        if (getIntent().hasExtra("ROLE")) {
            role = getIntent().getStringExtra("ROLE");
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvError = findViewById(R.id.tvError);
        tvRegisterLink = findViewById(R.id.tvRegisterLink);



        // ΑΛΛΑΓΗ ΕΜΦΑΝΙΣΗΣ ΑΝΑΛΟΓΑ ΜΕ ΤΟΝ ΡΟΛΟ
        if (role.equals("ATHLETE")) {
            btnLogin.setText("ΕΙΣΟΔΟΣ ΑΘΛΗΤΗ"); 

            tvRegisterLink.setVisibility(View.GONE); 
        } else {
            btnLogin.setText("ΕΙΣΟΔΟΣ ΠΡΟΠΟΝΗΤΗ");
            tvRegisterLink.setVisibility(View.VISIBLE);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = etEmail.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                DAOFactory factory = DAOFactory.getInstance();

                if (role.equals("COACH")) {
                    //ΠΡΟΠΟΝΗΤΗΣ
                    Coach c = factory.getCoachDAO().findByEmail(inputEmail);
                    if (c != null && c.getPassword().equals(inputPassword)) {
                        Intent intent = new Intent(MainActivity.this, CoachActivity.class);
                        intent.putExtra("COACH_EMAIL", c.getEmail());
                        intent.putExtra("COACH_NAME", c.getLast_name());
                        startActivity(intent);
                        finish();
                    } else {
                        showError("Λάθος στοιχεία Προπονητή");
                    }

                } else {
                    //ΑΘΛΗΤΗΣ
                    Athlete a = factory.getAthleteDAO().findByEmail(inputEmail);
                    if (a != null && a.getPassword().equals(inputPassword)) {
                        Intent intent = new Intent(MainActivity.this, AthleteActivity.class);
                        intent.putExtra("ATHLETE_EMAIL", a.getEmail());
                        startActivity(intent);
                        finish();
                    } else {
                        showError("Λάθος στοιχεία Αθλητή");
                    }
                }
            }
        });

        
        tvRegisterLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void showError(String message) {
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(message);
    }

    
    private void createDummyData() {
        DAOFactory factory = DAOFactory.getInstance();
        
        if (factory.getCoachDAO().findAll().isEmpty()) {
            Coach c = new Coach(1, "coach@test.com", "1234", "Dimitrios", "Antoniou", LocalDate.now());
            factory.getCoachDAO().save(c);
        }

        if (factory.getAthleteDAO().findAll().isEmpty()) {
            Invitation dummyInvite = new Invitation("athlete@test.com");
            dummyInvite.accept();
            Athlete a = new Athlete(100, "athlete@test.com", "1234", "Antoniou", "Giorgos", dummyInvite, LocalDate.now());
            factory.getAthleteDAO().save(a);
        }
    }
}