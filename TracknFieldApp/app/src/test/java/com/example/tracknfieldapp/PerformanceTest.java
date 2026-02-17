package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ExerciseType;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.Performance;
import com.example.tracknfieldapp.domain.ScheduledTraining;
import com.example.tracknfieldapp.ui.CompletedExercise;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Κλάση δοκιμών για τηνPerformance.
 * Ελέγχει τον υπολογισμό του ποσοστού επιτυχίας του αθλητή και την αντίστοιχη αξιολόγηση.
 */
public class PerformanceTest {

    private Performance performance;
    private Athlete athlete;
    private ScheduledTraining training;
    private Exercise exercise;

    /**
     * Αρχικοποίηση αθλητή και προπόνησης πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        athlete = new Athlete(1, "gz@mail.com", "1234", "george", "zervas", new Invitation("gz@mail.com"), LocalDate.now());
        performance = new Performance(athlete);
        
        ExerciseType type = new ExerciseType("Sprints");
        exercise = new Exercise(type, 1, 1, 0.0, 100.0);
        training = new ScheduledTraining(LocalDate.now(), LocalTime.now(), new ArrayList<>());
        training.addExercises(exercise);
        
        athlete.addScheduledTraining(training);
    }

    /**
     *  Έλεγχος του ποσοστού επιτυχίας όταν δεν έχει ολοκληρωθεί καμία άσκηση (0%).
     */
    @Test
    public void testSuccessRateZero() {
        assertEquals(0.0, performance.SuccessRate(), 0.001);
        assertEquals("bad", performance.getPerformance());
    }

    /**
     * Έλεγχος του ποσοστού επιτυχίας όταν όλες οι ασκήσεις έχουν ολοκληρωθεί (100%).
     */
    @Test
    public void testSuccessRateFull() {
        CompletedExercise ce = new CompletedExercise(exercise, 1, 1, 0.0, 100.0);
        training.getCompletedExercises().add(ce);
        
        assertEquals(100.0, performance.SuccessRate(), 0.001);
        assertEquals("Excellent", performance.getPerformance());
    }

    /**
     * Έλεγχος του ποσοστού επιτυχίας για μερική ολοκλήρωση (π.χ. 50%).
     */
    @Test
    public void testSuccessRatePartial() {
        Exercise exercise2 = new Exercise(new ExerciseType("Jumps"), 1, 1, 0.0, 0.0);
        training.addExercises(exercise2);
        
        CompletedExercise ce = new CompletedExercise(exercise, 1, 1, 0.0, 100.0);
        training.getCompletedExercises().add(ce);
        
        // 1/2 completed = 50%
        assertEquals(50.0, performance.SuccessRate(), 0.001);
        assertEquals("Not good", performance.getPerformance());
    }
}
