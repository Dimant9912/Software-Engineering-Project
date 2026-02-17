package com.example.tracknfieldapp;

import static org.junit.Assert.*;
import com.example.tracknfieldapp.controller.WorkoutController;
import com.example.tracknfieldapp.domain.*;
import com.example.tracknfieldapp.ui.CompletedExercise;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Κλάση δοκιμών για τον WorkoutController.
 * Ελέγχει την καταγραφή αποτελεσμάτων ασκήσεων, την ολοκλήρωση προπονήσεων και την εμφάνιση λεπτομερειών.
 */
public class WorkoutControllerTest {

    private WorkoutController workoutController;
    private Athlete athlete;
    private ScheduledTraining training;
    private Exercise exercise;

    @Before
    public void setUp() {
        workoutController = new WorkoutController();
        Invitation invite = new Invitation("athlete@test.com");
        athlete = new Athlete(1, "athlete@test.com", "pass", "John", "Doe", invite, LocalDate.now());
        
        ExerciseType type = new ExerciseType("Sprint");
        exercise = new Exercise(type, 1, 1, 10.0, 100.0);
        
        training = new ScheduledTraining(LocalDate.now(), LocalTime.now(), new ArrayList<>());
        training.addExercises(exercise);
        athlete.addScheduledTraining(training);
    }

    /**
     * Σκοπός: Έλεγχος καταγραφής επιτυχούς αποτελέσματος άσκησης (if branch: c1.isCompleted()).
     */
    @Test
    public void testRecordExerciseResult_Success() {
        workoutController.recordExerciseResult(training, exercise, 1, 1, 10.0, 100.0);
        
        assertEquals(1, training.getCompletedExercises().size());
        assertTrue(training.getCompletedExercises().get(0).isCompleted());
    }

    /**
     * Σκοπός: Έλεγχος καταγραφής αποτυχημένου αποτελέσματος άσκησης (else branch: c1.isCompleted()).
     */
    @Test
    public void testRecordExerciseResult_Failure() {
        // Λιγότερες επαναλήψεις από τον στόχο
        workoutController.recordExerciseResult(training, exercise, 0, 1, 10.0, 100.0);
        
        assertEquals(1, training.getCompletedExercises().size());
        assertFalse(training.getCompletedExercises().get(0).isCompleted());
    }

    /**
     * Σκοπός: Έλεγχος ολοκλήρωσης προπόνησης και εμφάνισης αποτελεσμάτων (κάλυψη finishWorkout).
     */
    @Test
    public void testFinishWorkout() {
        workoutController.recordExerciseResult(training, exercise, 1, 1, 10.0, 100.0);
        workoutController.finishWorkout(athlete);
        // Η μέθοδος εκτυπώνει στην κονσόλα. Επιβεβαιώνουμε ότι η κλήση ολοκληρώνεται.
    }

    /**
     * Σκοπός: Έλεγχος εμφάνισης λεπτομερειών προπόνησης για ολοκληρωμένες και μη ασκήσεις.
     */
    @Test
    public void testPrintWorkoutDetails() {
        // Περίπτωση 1: Άσκηση που δεν επιχειρήθηκε (else branch του findCompletedByExercise)
        workoutController.printWorkoutDetails(training);
        
        // Περίπτωση 2: Άσκηση που ολοκληρώθηκε (if ce != null)
        workoutController.recordExerciseResult(training, exercise, 1, 1, 10.0, 100.0);
        workoutController.printWorkoutDetails(training);

        // Περίπτωση 3: Άσκηση που απέτυχε
        ExerciseType type2 = new ExerciseType("Other");
        Exercise ex2 = new Exercise(type2, 10, 1, 0, 0);
        training.addExercises(ex2);
        workoutController.recordExerciseResult(training, ex2, 5, 1, 0, 0);
        workoutController.printWorkoutDetails(training);
    }
}
