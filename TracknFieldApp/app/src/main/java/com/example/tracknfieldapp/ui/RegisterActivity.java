package com.example.tracknfieldapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Coach;

import java.time.LocalDate;

/**
 * Η οθόνη εγγραφής για νέους προπονητές.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword;
    private Button btnRegisterConfirm, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.etRegFirstName);
        etLastName = findViewById(R.id.etRegLastName);
        etEmail = findViewById(R.id.etRegEmail);
        etPassword = findViewById(R.id.etRegPassword);
        btnRegisterConfirm = findViewById(R.id.btnRegisterConfirm);
        btnBack = findViewById(R.id.btnRegisterBack);

        btnRegisterConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = etFirstName.getText().toString().trim();
                String lName = etLastName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                if (fName.isEmpty() || lName.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Συμπλήρωσε όλα τα πεδία!", Toast.LENGTH_SHORT).show();
                    return;
                }

                DAOFactory factory = DAOFactory.getInstance();
                if (factory.getCoachDAO().findByEmail(email) != null) {
                    Toast.makeText(RegisterActivity.this, "Το email χρησιμοποιείται ήδη!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int randomId = (int) (Math.random() * 10000);
                
                Coach newCoach = new Coach(randomId, email, pass, fName, lName, LocalDate.of(1990, 1, 1));
                factory.getCoachDAO().save(newCoach);

                Toast.makeText(RegisterActivity.this, "Εγγραφή επιτυχής!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
