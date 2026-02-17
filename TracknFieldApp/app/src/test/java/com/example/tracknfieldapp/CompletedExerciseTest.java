package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ExerciseType;
import com.example.tracknfieldapp.ui.CompletedExercise;

import org.junit.Before;
import org.junit.Test;

/**
 * Κλάση δοκιμών για την  CompletedExercise.
 * Ελέγχει την καταγραφή αποτελεσμάτων ασκήσεων και τον υπολογισμό διαφορών
 */
public class CompletedExerciseTest {

    private Exercise targetExercise;
    private ExerciseType type;

    /**
     * Αρχικοποίηση δεδομένων πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        type = new ExerciseType("Sprints");
        // Στόχος: reps=10, sets=3, time=20.0, distance=100.0
        targetExercise = new Exercise(type, 10, 3, 20.0, 100.0);
    }

    /**
     *  Επιβεβαίωση ότι η άσκηση θεωρείται ολοκληρωμένη (true)
     * όταν ο αθλητής πιάνει ακριβώς τους στόχους της άσκησης.
     */
    @Test
    public void testIsCompletedSuccess() {
        // Ο αθλητής πέτυχε ακριβώς τους στόχους
        CompletedExercise completed = new CompletedExercise(targetExercise, 10, 3, 20.0, 100.0);
        
        assertTrue(completed.isCompleted());
        assertEquals(0, completed.get_reps_difference());
        assertEquals(0, completed.get_sets_difference());
        assertEquals(0.0, completed.get_time_difference(), 0.001);
        assertEquals(0.0, completed.get_distance_difference(), 0.001);//το delta θεωρεί οτι 2 αριθμοι ειναι ίσοι εαν η διαφορα τους ειναι μικροτερη απο 0.001
    }

    /**
     *  Επιβεβαίωση ότι η άσκηση θεωρείται ολοκληρωμένη (true)
     * ακόμη και όταν ο αθλητής καλύπτει τα ορια (περισσότερα reps/sets, λιγότερο χρόνο).
     */
    @Test
    public void testIsCompletedExceededExpectations() {
        // Στόχος: 10 reps, 3 sets, 20.0s | Επίδοση: 15 reps, 4 sets, 18.0s
        CompletedExercise exceeded = new CompletedExercise(targetExercise, 15, 4, 18.0, 100.0);
        
        assertTrue("Η άσκηση πρέπει να είναι completed αν ξεπεραστούν οι στόχοι", exceeded.isCompleted());
        
        // Οι διαφορές πρέπει να είναι αρνητικές ή μηδέν
        assertTrue(exceeded.get_reps_difference() < 0);
        assertTrue(exceeded.get_sets_difference() < 0);
        assertTrue(exceeded.get_time_difference() < 0);
    }

    /**
     * Επιβεβαίωση ότι η άσκηση αποτυγχάνει (false)
     * όταν ο αθλητής κάνει λιγότερα reps από τον στόχο.
     */
    @Test
    public void testIsCompletedFailureReps() {
        CompletedExercise failed = new CompletedExercise(targetExercise, 8, 3, 20.0, 100.0);
        assertFalse(failed.isCompleted());
        assertEquals(2, failed.get_reps_difference());
    }

    /**
     * Επιβεβαίωση ότι η άσκηση αποτυγχάνει (false)
     * όταν ο αθλητής κάνει λιγότερα sets από τον στόχο.
     */
    @Test
    public void testIsCompletedFailureSets() {
        CompletedExercise failed = new CompletedExercise(targetExercise, 10, 2, 20.0, 100.0);
        assertFalse(failed.isCompleted());
        assertEquals(1, failed.get_sets_difference());
    }

    /**
     * Σκοπός: Επιβεβαίωση ότι η άσκηση αποτυγχάνει (false) 
     * όταν ο αθλητής αργεί περισσότερο από τον στόχο (time difference > 0).
     */
    @Test
    public void testIsCompletedFailureTime() {
        CompletedExercise failed = new CompletedExercise(targetExercise, 10, 3, 25.0, 100.0);
        assertFalse(failed.isCompleted());
        assertTrue(failed.get_time_difference() > 0);
    }

    /**
     * Επιβεβαίωση ότι η άσκηση αποτυγχάνει (false)
     * όταν ο αθλητής καλύπτει λιγότερη απόσταση από τον στόχο.
     */
    @Test
    public void testIsCompletedFailureDistance() {
        CompletedExercise failed = new CompletedExercise(targetExercise, 10, 3, 20.0, 80.0);
        assertFalse(failed.isCompleted());
        assertEquals(20.0, failed.get_distance_difference(), 0.001);
    }

    /**
     * Έλεγχος των μεθόδων υπολογισμού διαφορών και των getters.
     */
    @Test
    public void testGettersAndDifferences() {
        CompletedExercise ce = new CompletedExercise(targetExercise, 12, 4, 25.0, 150.0);
        
        assertEquals(targetExercise, ce.getExercise());
        assertEquals(12, ce.getCompletedReps());
        assertEquals(4, ce.getCompletedSets());
        assertEquals(25.0, ce.getCompletedTime(), 0.001);
        assertEquals(150.0, ce.getCompletedDistance(), 0.001);

        // Διαφορές (target - done)
        assertEquals(-2, ce.get_reps_difference());
        assertEquals(-1, ce.get_sets_difference());
        assertEquals(5.0, ce.get_time_difference(), 0.001);
        assertEquals(-50.0, ce.get_distance_difference(), 0.001);
    }
}
