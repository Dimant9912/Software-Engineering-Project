package com.example.tracknfieldapp.domain;

import com.example.tracknfieldapp.ui.CompletedExercise;
import java.util.List;

/**
 * Η κλάση Performance χρησιμοποιείται για τον υπολογισμό της απόδοσης ενός
 * αθλητή, η οποία βασίζεται στο ποσοστό ασκήσεων που ολοκληρώθηκαν επιτυχως.
 */
public class Performance {
    private Athlete athlete;
    private double athlete_performance;

    /**
     * Κατασκευαστής της κλάσης.
     * 
     * @param athlete αθλητής που μας αφορά η απόδοσή του
     */
    public Performance(Athlete athlete) {
        this.athlete_performance = 0;
        this.athlete = athlete;
    }

    /**
     * Υπολογίζει το ποσοστό επιτυχίας του αθλητή σε όλες τις προγραμματισμένες
     * προπονήσεις,και το επιστρέφει(μορφη double)
     * 
     * @return το ποσοστό επιτυχίας
     */
    public double SuccessRate() {
        List<ScheduledTraining> allScheduledTrainings = athlete.getScheduled_trainings();
        int totalExercisesPlanned = 0;
        int totalExercisesCompleted = 0;

        if (allScheduledTrainings.isEmpty())
            return 0.0;

        for (ScheduledTraining training : allScheduledTrainings) {
            totalExercisesPlanned += training.getExercises().size();
            for (CompletedExercise ce : training.getCompletedExercises()) {
                if (ce.isCompleted()) {
                    totalExercisesCompleted++;
                }
            }
        }

        if (totalExercisesPlanned == 0)
            return 0.0;
        this.athlete_performance = ((double) totalExercisesCompleted / totalExercisesPlanned) * 100;
        return this.athlete_performance;
    }

    /**
     * Επιστρέφει ένα σχόλιο για την απόδοση του αθλητή.
     * Το σχόλιο βασίζεται στο ποσοτό επιτυχίας.
     * 
     * @return επιστρέφει σχόλιο για την απόδοση.
     */
    public String getPerformance() {
        double rate = SuccessRate();
        if (rate >= 90)
            return "Excellent";
        if (rate >= 70)
            return "Good";
        if (rate >= 50)
            return "Not good";
        return "bad";
    }
}
