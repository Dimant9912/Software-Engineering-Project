package com.example.tracknfieldapp.controller;

import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.InvitationStatus;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Ο ProgramController διαχειρίζεται τις λειτουργίες προγραμματισμού και
 * προσκλήσεων.
 * Είναι υπεύθυνος για την αποστολή προσκλήσεων από προπονητές σε αθλητές και
 * τον προγραμματισμό των προπονήσεων.
 */
public class ProgramController {

    /**
     * Δημιουργεί μια πρόσκληση από έναν προπονητή προς έναν αθλητή.
     * 
     * @param coach        προπονητής που στέλνει την πρόσκληση.
     * @param athleteEmail email αθλητή προς τον οποίο απευθύνεται η πρόσκληση.
     *                     Επιστρέφεται Το αντικείμενο της πρόσκλησης που
     *                     δημιουργήθηκε.
     * @return το αντικείμενο της πρόσκλησης που δημιουργήθηκε.
     */
    public Invitation createInvitation(Coach coach, String athleteEmail) {
        Invitation invitation = coach.inviteAthlete(athleteEmail);
        System.out.println("Invitation sent to  " + athleteEmail +
                " with code: " + invitation.getInvitationCode());
        return invitation;
    }

    /**
     * Επεξεργάζεται την αποδοχή μιας πρόσκλησης και συνδέει τον αθλητή με τον
     * προπονητή.
     * 
     * @param coach      προπονητής του αθλητή
     * @param athlete    αθλητής που αποδέχεται την πρόσκληση
     * @param invitation πρόσκληση (αντικείμενο) προς έλεγχο
     *                   Επιστρεφει true αν η σύνδεση έγινε επιτυχώς, false σε
     *                   αντίθετη περίπτωση.
     * @return true αν η σύνδεση ολοκληρώθηκε επιτιχώς αλλιώς false
     */
    public boolean receiveInvitation(Coach coach, Athlete athlete, Invitation invitation) {
        if (invitation.getStatus() == InvitationStatus.ACCEPTED) {
            return coach.addAthlete(athlete, invitation);
        } else {
            System.out.println("Receiving invitation failed. Status :  " + invitation.getStatus());
            return false;
        }
    }

    /**
     * Προγραμματίζει μια νέα προπόνηση για έναν συγκεκριμένο αθλητή.
     * 
     * @param athlete  αθλητής για τον οποίο προγραμματίζεται η προπόνηση.
     * @param training προπόνηση
     */
    public void scheduleTraining(Athlete athlete, ScheduledTraining training) {
        training.setAthlete(athlete);
        athlete.getScheduled_trainings().add(training);
    }

    /**
     * Επιστρέφει όλες τις προγραμματισμένες προπονήσεις των αθλητών ενός προπονητή
     * για μια συγκεκριμένη ημερομηνία.
     * 
     * @return όλες τις προγραμματισμένες προπονήσεις.
     *         Επιστρεφει μια λίστα με τις προγραμματισμένες προπονήσεις της ημέρας.
     * @return μία λίστα με τις προγραμματισμένες προπονήσεις της ημέρας
     * @param coach προπονητής
     * @param date  συγκεκριμένη ημερομηνία για την οποία ζητείται το πρόγραμμα
     */
    public List<ScheduledTraining> get_specific_day_scheduled_training(Coach coach, LocalDate date) {
        List<ScheduledTraining> specific_date_training = new ArrayList<>();

        for (Athlete athlete : coach.getAthletes()) {
            ScheduledTraining st = athlete.getTrainingByDate(date);
            if (st != null) {
                specific_date_training.add(st);
            }
        }
        return specific_date_training;
    }

    /**
     * Εκτυπώνει μια αναφορά με τις προγραμματισμένες προπονήσεις των αθλητών για
     * μια συγκεκριμένη ημέρα.
     * 
     * @param coach προπονητής που θέλει την αναφορά
     * @param date  συγκεκριμένη ημερομηνία
     */
    public void printDailyReport(Coach coach, LocalDate date) {
        List<ScheduledTraining> s = get_specific_day_scheduled_training(coach, date);

        System.out.println("\n=Specific Date Report :  " + date + " \n");

        if (s.isEmpty()) {
            System.out.println("Δεν υπάρχει καμία προγραμματισμένη προπόνηση για κανέναν αθλητή.");
            return;
        }

        for (ScheduledTraining st : s) {
            String athleteName = st.getAthlete().getFirst_name() + " " + st.getAthlete().getLast_name();
            System.out.println("Athlete: " + athleteName);
        }
    }
}
