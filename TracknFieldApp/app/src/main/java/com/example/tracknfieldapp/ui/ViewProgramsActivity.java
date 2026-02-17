package com.example.tracknfieldapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tracknfieldapp.R;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Η οθόνη για την προβολή των προγραμματισμένων προπονήσεων μέσω ημερολογίου.
 */
public class ViewProgramsActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ListView lvScheduledTrainings;
    private List<ScheduledTraining> dayTrainings = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_programs);

        calendarView = findViewById(R.id.calendarView);
        lvScheduledTrainings = findViewById(R.id.lvScheduledTrainings);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvScheduledTrainings.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                LocalDate selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                updateTrainingsList(selectedDate);
            }
        });

        lvScheduledTrainings.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ViewProgramsActivity.this, ProgramDetailsActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Ενημερώνει τη λίστα των προπονήσεων για την επιλεγμένη ημερομηνία.
     */
    private void updateTrainingsList(LocalDate date) {
        List<String> trainingDisplayNames = new ArrayList<>();
        dayTrainings.clear();

        DAOFactory factory = DAOFactory.getInstance();
        List<Athlete> athletes = factory.getAthleteDAO().findAll();

        for (Athlete athlete : athletes) {
            ScheduledTraining st = athlete.getTrainingByDate(date);
            if (st != null) {
                dayTrainings.add(st);
                trainingDisplayNames.add("Αθλητής: " + athlete.getFirst_name() + " " + athlete.getLast_name() + " - " + st.getTime());
            }
        }

        adapter.clear();
        adapter.addAll(trainingDisplayNames);
        adapter.notifyDataSetChanged();
    }
}
