package com.example.tracknfieldapp.domain;

/**
 * Άσκηση που αποτελεί μέρος μιας προπόνησης.
 * Περιλαμβάνει τους στόχους που πρέπει να πετύχει ο αθλητής.
 */
public class Exercise {

    private ExerciseType type;
    private int reps;
    private int sets;
    private double time;
    private double distance;

    /**
     * Κατασκευαστής της κλάσης.
     * 
     * @param type     τύπος της άσκησης.
     * @param reps     αριθμός των επαναλήψεων.
     * @param sets     αριθμός των σετ.
     * @param time     στόχος χρόνου.
     * @param distance στόχος απόστασης.
     */
    public Exercise(ExerciseType type, int reps, int sets, double time, double distance) {
        this.type = type;
        this.reps = reps;
        this.sets = sets;
        this.time = time;
        this.distance = distance;
    }

    /**
     * Επιστέφει τον αριθμό επαναλήψεων.
     * 
     * @return αριθμός επαναλήψεων.
     */
    public int getReps() {
        return reps;
    }

    /**
     * Επιστρέφει τον αριθμό των σετ.
     * 
     * @return αριθμός σετ.
     */

    public int getSets() {
        return sets;
    }

    /**
     * Επιστρέφει τον στόχο χρόνου.
     * 
     * @return στόχος χρόνου.
     */

    public double getTime() {
        return time;
    }

    /**
     * Επιστρέφει τον στόχο απόστασης.
     * 
     * @return στόχος απόστασης.
     */

    public double getDistance() {
        return distance;
    }

    /**
     * Επιστρέφει τον τύπο της άσκησης.
     * 
     * @return τύπος άσκησης.
     */

    public ExerciseType getType() {
        return type;
    }

    /** @param reps καινούριος αριθμός επαναλήψεων. */

    public void setReps(int reps) {
        this.reps = reps;
    }

    /** @param sets κανούριος αριθμός σετ. */

    public void setSets(int sets) {
        this.sets = sets;
    }

    /** @param time καινούριος στόχος χρόνου. */

    public void setTime(double time) {
        this.time = time;
    }

    /** @param distance καινούριος στόχος απόστασης. */

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /** @param type καινούριος τύπος άσκησης */

    public void setType(ExerciseType type) {
        this.type = type;
    }
}
