package com.example.tracknfieldapp.controller;

import com.example.tracknfieldapp.ui.CompletedExercise;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.Performance;
import com.example.tracknfieldapp.domain.ScheduledTraining;

/**
 * Ο WorkoutController διαχειρίζεται την εκτέλεση και την καταγραφή των
 * προπονήσεων.
 * Περιέχει μεθόδους για την καταγραφή αποτελεσμάτων ασκήσεων και τον υπολογισμό
 * της συνολικής επίδοσης του αθλητή.
 */
public class WorkoutController {

    /**
     * Καταγράφει το αποτέλεσμα μιας συγκεκριμένης άσκησης μέσα σε μια προπόνηση.
     * 
     * @param training προπόνηση στην οποία ανήκει η άσκηση.
     * @param exercise άσκηση που εκτελέστηκε.
     * @param reps     επαναλήψεις που πραγματοποιήθηκαν.
     * @param sets     σετ που πραγματοποιήθηκαν.
     * @param time     χρόνος που χρειάστηκε να εκτελεστεί η ασκηση.
     * @param dist     απόσταση που καλύφθηκε.
     */
    public void recordExerciseResult(ScheduledTraining training, Exercise exercise,
            int reps, int sets, double time, double dist) {

        CompletedExercise c1 = new CompletedExercise(exercise, reps, sets, time, dist);
        training.getCompletedExercises().add(c1);

        if (c1.isCompleted()) {
            System.out.println("Completed");
        } else {
            System.out.println("Exercise not completed");
        }
    }

    /**
     * Οριστικοποιεί την προπόνηση και εμφανίζει τα συγκεντρωτικά αποτελέσματα
     * απόδοσης.
     * 
     * @param athlete αθλητής ο οποίος έχει ολοκληρώσει την προπόνηση.
     */
    public void finishWorkout(Athlete athlete) {
        Performance p = new Performance(athlete);
        double score = p.SuccessRate();

        System.out.println("\n--- RESULTS ---");
        System.out.println("Athlete: " + athlete.getFirst_name() + " " + athlete.getLast_name());
        System.out.println("Total: " + String.format("%.2f", score) + "%");
        System.out.println("Comment: " + p.getPerformance() + "\n");
    }

    /**
     * Εκτυπώνει τις λεπτομέρειες της προπόνησης για κάθε μία άσκηση, συγκρίνοντας
     * τους στόχους με τα αποτελέσματα.
     * 
     * @param training προπόνηση που θέλουμε να εκτυπώσουμε τις λεπτομέριες.
     */
    public void printWorkoutDetails(ScheduledTraining training) {
        for (Exercise e : training.getExercises()) {
            System.out.println("Exercise : " + e.getType());
            CompletedExercise ce = findCompletedByExercise(training, e);

            if (ce != null) {
                System.out.println("Status : " + (ce.isCompleted() ? "\nDone" : "\nNot done"));
                System.out.println(" \nReps difference: " + ce.get_reps_difference());
                System.out.println("\nTime difference : " + ce.get_time_difference());
                System.out.println("\nSets difference : " + ce.get_reps_difference());
                System.out.println("\nDistance difference: " + ce.get_distance_difference());
            } else {
                System.out.println("Not even tried" + "\n");
            }
        }
    }

    /**
     * Αναζητά αν υπάρχει καταγεγραμμένο αποτέλεσμα για μια συγκεκριμένη άσκηση.
     * 
     * @param training προπόνηση με λίστα των ασκήσεων που έχουν ολοκληρωθεί.
     * @param exercise άσκηση που αναζείται.
     *                 ΕπιστρέφειΤο αντικείμενο CompletedExercise αν βρεθεί, αλλιώς
     *                 null.
     * @return αντικέιμενο CompleteExercise αν βρεθεί, αλλιώς null
     */
    private CompletedExercise findCompletedByExercise(ScheduledTraining training, Exercise exercise) {
        for (CompletedExercise ce : training.getCompletedExercises()) {
            if (ce.getExercise().equals(exercise)) {
                return ce;
            }
        }
        return null;
    }
}
