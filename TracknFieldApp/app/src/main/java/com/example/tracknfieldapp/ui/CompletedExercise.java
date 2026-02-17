package com.example.tracknfieldapp.ui;

import com.example.tracknfieldapp.domain.Exercise;

/**
 * Η κλάση CompletedExercise αντιπροσωπεύει την εκτέλεση μιας άσκησης από έναν αθλητή.
 * Συγκρίνει τα πραγματικά αποτελέσματα με τους στόχους που είχε θέσει ο προπονητής.
 */
public class CompletedExercise {

    private Exercise exercise; // Η αρχική άσκηση (στόχος)
    private int completedReps; // Τι έκανε στην πραγματικότητα
    private int completedSets;
    private double completedTime;
    private double completedDistance;

    /**
     * constructor για την ολοκληρωμένη άσκηση.
     *  exercise Η αρχική άσκηση (στόχος).
     *  completedReps Οι επαναλήψεις που πραγματοποιήθηκαν.
     *  completedSets Τα σετ που πραγματοποιήθηκαν.
     * completedTime Ο χρόνος που χρειάστηκε.
     *  completedDistance Η απόσταση που καλύφθηκε.
     */
    public CompletedExercise(Exercise exercise, int completedReps, int completedSets, double completedTime, double completedDistance) {
        this.exercise = exercise;
        this.completedReps = completedReps;
        this.completedSets = completedSets;
        this.completedTime = completedTime;
        this.completedDistance = completedDistance;
    }

    public Exercise getExercise() { return exercise; }
    public int getCompletedReps() { return completedReps; }
    public int getCompletedSets() { return completedSets; }
    public double getCompletedDistance() { return completedDistance; }
    public double getCompletedTime() { return completedTime; }

    /** επιστρέφεται Η διαφορά στα σετ (στόχος - πραγματικό). */
    public int get_sets_difference() {
        return exercise.getSets() - completedSets;
    }

    /** επιστρέφεται Η διαφορά στις επαναλήψεις (στόχος - πραγματικό). */
    public int get_reps_difference() {
        return exercise.getReps() - completedReps;
    }

    /** επιστρέφεται Η διαφορά στον χρόνο (πραγματικό - στόχος). */
    public double get_time_difference(){
        return completedTime - exercise.getTime();
    }

    /** επιστρέφεται Η διαφορά στην απόσταση (στόχος - πραγματικό). */
    public double get_distance_difference(){
        return exercise.getDistance() - completedDistance;
    }

    /**
     * Ελέγχει αν η άσκηση θεωρείται επιτυχώς ολοκληρωμένη βάσει των στόχων και αν ναι,επιστρεφει true,αλλιως false
     */
    public boolean isCompleted() {
        if ((get_sets_difference() <= 0) && (get_reps_difference() <= 0) && (get_time_difference() <= 0.0) && get_distance_difference() <= 0) {
            System.out.println("\nExercise is completed\n");
            return true;
        }
        System.out.println(("\nExercise not completed\n"));
        return false;
    }
}
