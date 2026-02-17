package com.example.tracknfieldapp.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * Η κλάση Athlete αντιπροσωπεύει έναν αθλητή στο σύστημα.
 * Η κλάση επεκτείνει την κλάση User.
 * Ο αθλητής συνδέεται με τον προπονητή μέσω πρόσκλησης και έχει ένα πρόγραμμα
 * προπονήσεων.
 */
public class Athlete extends User {

    private final Invitation invite;
    private List<ScheduledTraining> scheduled_trainings;

    /**
     * Κατασκευαστής της κλάσης
     * 
     * @param id        "ταυτότητα" αθλητή η οποία είναι μοναδική
     * @param email     το email το οποίο χρησιμεύει για την συνδεση (login)
     * @param password  κωδικός πρόσβασης
     * @param firstname όνομα αθλητή
     * @param lastname  επώνυμο αθλητή
     * @param invite    πρόσκληση από τον προπονητή
     * @param BirthDate ημερομηνία γέννησης αθλητή.
     */

    public Athlete(int id, String email, String password, String firstname, String lastname, Invitation invite,
            LocalDate BirthDate) {
        super(id, email, password, firstname, lastname, BirthDate);
        this.invite = invite;
        this.scheduled_trainings = new ArrayList<>();
    }

    /**
     * Επιστρέφει την πρόσκληση για τον αθλητή
     * 
     * @return την πρόσκληση του αθλητή
     */

    public Invitation get_Invite() {
        return invite;
    }

    /**
     * Ο αθλητής απαντά στην πρόσκληση.
     * 
     * @param i      πρόσκληση που πρέπει να απαντηθεί
     * @param accept true για αποδοχή, false για απόρριψη
     */

    public void respondToInvitation(Invitation i, boolean accept) {
        if (accept) {
            i.accept();
            System.out.println("Invitation accepted");
        } else {
            i.reject();
            System.out.println("Invitation rejected");
        }
    }

    /**
     * Επιστρέφει λίστα με τις προγραμματισμένες προπονήσεις.
     * 
     * @return τη λίστα με τις προγραμματισμένες προπονήσεις.
     */
    public List<ScheduledTraining> getScheduled_trainings() {
        return scheduled_trainings;
    }

    /**
     * Προσθέτει μία προπόνηση στο πρόγραμμα.
     * 
     * @param scheduled_training προπόνηση που θα προστεθεί.
     */

    public void addScheduledTraining(ScheduledTraining scheduled_training) {
        this.scheduled_trainings.add(scheduled_training);
    }

    /**
     * Αφαιρεί μια προπόνηση από το πρόγραμμα.
     * 
     * @param scheduled_training η προπόνηση που θα αφαιρεθεί.
     */
    public void removeScheduledTraining(ScheduledTraining scheduled_training) {
        this.scheduled_trainings.remove(scheduled_training);
    }

    /**
     * Επιστρέφει την προπόνηση για μια συγκεκριμένη ημερομηνία.
     * 
     * @param date ημερομηνία για την προπόνηση
     * @return επιστρέφει την προπόνηση για συγκεκριμένη ημερομηνία αλλιώς null αν
     *         δεν υπάρχει.
     */
    public ScheduledTraining getTrainingByDate(LocalDate date) {
        for (ScheduledTraining training : this.scheduled_trainings) {
            if (training.getDate().equals(date)) {
                return training;
            }
        }
        return null;
    }
}
