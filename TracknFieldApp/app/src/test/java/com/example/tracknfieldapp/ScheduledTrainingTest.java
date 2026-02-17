package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ExerciseType;
import com.example.tracknfieldapp.domain.ScheduledTraining;
import com.example.tracknfieldapp.ui.CompletedExercise;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Κλάση δοκιμών για την ScheduledTraining.
 * Ελέγχει τη διαχείριση ασκήσεων, αρχικοποίηση προπονήσεων και κατάσταση ολοκλήρωσής τους.
 */
public class ScheduledTrainingTest {

    private ScheduledTraining scheduledTraining;
    private LocalDate date;
    private LocalTime time;
    private ExerciseType pushUps;
    private Exercise pushUpExercise;

    /**
     * Αρχικοποίηση δεδομένων πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        date = LocalDate.now();
        time = LocalTime.of(10, 0);
        pushUps = new ExerciseType("Push Ups");
        pushUpExercise = new Exercise(pushUps, 10, 3, 0.0, 0.0);
        
        scheduledTraining = new ScheduledTraining(date, time, new ArrayList<>());
    }

    /**
     * Έλεγχος της ορθής αρχικοποίησης των πεδίων μιας προγραμματισμένης προπόνησης.
     */
    @Test
    public void testScheduledTrainingInitialization() {
        assertEquals(date, scheduledTraining.getDate());
        assertEquals(time, scheduledTraining.getTime());
        assertTrue(scheduledTraining.getExercises().isEmpty());
        assertTrue(scheduledTraining.getCompletedExercises().isEmpty());
    }

    /**
     *  Έλεγχος της προσθήκης και αφαίρεσης ασκήσεων από την προπόνηση.
     */
    @Test
    public void testAddAndRemoveExercise() {
        scheduledTraining.addExercises(pushUpExercise);
        assertEquals(1, scheduledTraining.getExercises().size());
        assertTrue(scheduledTraining.getExercises().contains(pushUpExercise));
        
        scheduledTraining.removeExercises(pushUpExercise);
        assertTrue(scheduledTraining.getExercises().isEmpty());
    }

    /**
     * Επιβεβαίωση της μεθόδου ελέγχου ολοκλήρωσης της προπόνησης.
     */
    @Test
    public void testIsTrainingFinished() {
        assertFalse(scheduledTraining.isTrainingFinished());

        scheduledTraining.addExercises(pushUpExercise);
        
        CompletedExercise completed = new CompletedExercise(pushUpExercise, 10, 3, 0.0, 0.0);
        scheduledTraining.getCompletedExercises().add(completed);
        
        assertTrue(scheduledTraining.isTrainingFinished());
    }
}
