package com.example.tracknfieldapp.domain;

import com.example.tracknfieldapp.ui.CompletedExercise;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Προγραμματισμένη προπόνηση για έναν αθλητή.
 * Περιέχει τη λίστα των ασκήσεων που πρέπει να γίνουν και τα αποτελέσματα των
 * ασκήσεων που ολοκληρώθηκαν.
 */
public class ScheduledTraining {
    private LocalDate date;
    private LocalTime time;
    private List<Exercise> exercises;
    private List<CompletedExercise> completedExercises;
    private Athlete athlete;

    /**
     * Κατασκευαστής κλάσης.
     * 
     * @param date      ημερομηνία της προπόνησης.
     * @param time      ώρα έναρξης.
     * @param exercises λίστα ασκήσεων.
     */
    public ScheduledTraining(LocalDate date, LocalTime time, List<Exercise> exercises) {
        this.date = date;
        this.time = time;
        this.exercises = exercises != null ? exercises : new ArrayList<>();
        this.completedExercises = new ArrayList<>();
    }

    /**
     * Κατασκευαστής μόνο με ημερομηνία (χρησιμοποιεί την τρέχουσα ώρα).
     * 
     * @param date ημερομηνία προπόνησης.
     */
    public ScheduledTraining(LocalDate date) {
        this(date, LocalTime.now(), new ArrayList<>());
    }

    /** @param athlete αθλητής που πρέπει να εκτελέσει την προπόνηση */

    public void setAthlete(Athlete athlete) {
        this.athlete = athlete;
    }

    /**
     * Επιστρέφει τον αθλητή που εκτελέι το πρόγραμμα.
     * 
     * @return αθλητή που εκτελέι το πρόγραμμα.
     */
    public Athlete getAthlete() {
        return athlete;
    }

    /**
     * Επιστρέφει την ημερομινία της προπόνησης.
     * 
     * @return ημερομηνία της προπόνησης.
     */

    public LocalDate getDate() {
        return date;
    }

    /**
     * Επιστρέφει την ώρα που αρχίζει η προπόνηση.
     * 
     * @return ώρα που αρχίζει η προπόνηση.
     */
    public LocalTime getTime() {
        return time;
    }

    /** @param date νέα ημερομηνία προπόνησης */

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /** @param time νέα ώρα που ξεκινάει η προπόνηση. */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Προσθήκη νέας άσκησης στην προγραμματισμένη προπόνηση.
     * 
     * @param e άσκηση που θα προστεθεί.
     */
    public void addExercises(Exercise e) {
        if (e != null) {
            this.exercises.add(e);
        }
    }

    /**
     * Αφαιρεί μια άσκηση από την προπόνηση.
     * 
     * @param e άσκηση που θα αφαιρεθεί.
     */
    public void removeExercises(Exercise e) {
        if (e != null) {
            this.exercises.remove(e);
        }
    }

    /**
     * Επιστρέφει λίστα με προγραμματισμένες ασκήσεις.
     * 
     * @return λίστα με προγραμματισμένες ασκήσεις.
     */

    public List<Exercise> getExercises() {
        return exercises;
    }

    /**
     * Επιστρέφει λίστα εκτελέσιμων και καταγεγραμμένων ασκήσεων.
     * 
     * @return λίστα εκτελέσιμων και καταγεγραμμένων ασκήσεων.
     */
    public List<CompletedExercise> getCompletedExercises() {
        return completedExercises;
    }

    /** @param exercises νέα λίστα ασκήσεων */

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Ελέγχει αν η προπόνηση έχει ολοκληρωθεί (αν όλες οι ασκήσεις έχουν
     * εκτελεστεί). και επσιτρέφει true,αλλιως false.
     * 
     * @return true όλες οι ασκήσεις ολοκληρώθηκαν αλλιώς false.
     */
    public boolean isTrainingFinished() {
        if (exercises.isEmpty())
            return false;

        int completedCount = 0;
        for (CompletedExercise ce : completedExercises) {
            if (ce.isCompleted()) {
                completedCount++;
            }
        }
        return completedCount == exercises.size();
    }
}
