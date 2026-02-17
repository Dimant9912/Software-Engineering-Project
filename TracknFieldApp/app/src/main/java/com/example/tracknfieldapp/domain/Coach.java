package com.example.tracknfieldapp.domain;

import java.time.LocalDate;
import java.util.*;

/**
 * Η κλάση Coach αντιπροσωπεύει έναν προπονητή στο σύστημα.
 * Ο προπονητής μπορεί να προσκαλεί αθλητές και να τους αναθέτει προπονήσεις.
 */
public class Coach extends User {

    private Set<Athlete> athletes; // λίστα με τους αθλητές που επιβλέπει
    private List<Invitation> SentInvitations; // ιστορικό προσκλήσεων

    /**
     * κατασκευαστής της κλάσης
     * 
     * @param id        "ταυτότητα" προπονητή, μοναδική
     * @param email     email προπονητή
     * @param password  κωδικός πρόσβασης
     * @param firstname όνομα προπονητή
     * @param lastname  επώνυμο προπονητή
     * @param BirthDate ημερομηνία γέννησης προπονητή
     */

    public Coach(int id, String email, String password, String firstname, String lastname, LocalDate BirthDate) {
        super(id, email, password, firstname, lastname, BirthDate);
        this.athletes = new HashSet<>();
        this.SentInvitations = new ArrayList<>();
    }

    /**
     * Επιστρέφει όλους του αθλητές που διαχερίζεται ο προπονητής
     * 
     * @return όλους τους αθλητές του προπονητή
     */

    public Set<Athlete> getAthletes() {
        return athletes;
    }

    /**
     * Προσθέτει έναν αθλητή στη λίστα επίβλεψης του προπονητή, αν η πρόσκληση έχει
     * γίνει αποδεκτή.
     * 
     * @param athlete    αθλητής προς προσθήκη.
     * @param invitation πρόσκληση που χρησιμοποιήθηκε.
     *                   Επιστρέφει true αν ο αθλητής προστέθηκε επιτυχώς.
     * @return true για επιτυχή προσθήκη του αθλητή, αλλιώς false.
     */
    public boolean addAthlete(Athlete athlete, Invitation invitation) {
        if (SentInvitations.contains(invitation) && invitation.getAthlete_email().equals(athlete.getEmail())
                && invitation.getStatus() == InvitationStatus.ACCEPTED) {
            this.athletes.add(athlete);
            System.out.println("Athlete : " + athlete.getFirst_name() + " added successfully! ");
            return true;
        }
        System.out.println("Athlete did not added!!");
        return false;
    }

    /**
     * Δημιουργεί και στέλνει μια νέα πρόσκληση σε έναν αθλητή.
     * 
     * @param athleteEmail email του αθλητή που θα σταλεί η πρόσκληση.
     *                     Επιστρέφει την πρόσκληση.
     * @return επιστρέφει την πρόσκληση.
     */
    public Invitation inviteAthlete(String athleteEmail) {
        Invitation newInvitation = new Invitation(athleteEmail);
        System.out
                .println("Invitation sent to  " + athleteEmail + " with code :  " + newInvitation.getInvitationCode());
        this.SentInvitations.add(newInvitation);
        return newInvitation;
    }

    /**
     * Αναθέτει μια νέα προγραμματισμένη προπόνηση σε έναν αθλητή.
     * 
     * @param athlete αθλητής που του ανατίθεται η νέα προπόνηση.
     * @param t       προγραμματισμένη προπόνηση προς αναθέτηση.
     */
    public void newScheduledTraining(Athlete athlete, ScheduledTraining t) {
        if (this.athletes.contains(athlete)) {
            athlete.addScheduledTraining(t);
        } else {
            System.out.println("Error , athlete : " + athlete.getFirst_name() + "is not included in list");
        }
    }

    /**
     * Εμφανίζει μια αναφορά με την κατάσταση όλων των προσκλήσεων που έχουν σταλεί.
     */
    public void getInvitationsReport() {
        int accepted_invitations = 0;
        int pending_invitations = 0;
        int rejected_invitations = 0;
        for (Invitation i : SentInvitations) {
            if (i.getStatus() == InvitationStatus.ACCEPTED)
                accepted_invitations++;
            else if (i.getStatus() == InvitationStatus.PENDING)
                pending_invitations++;
            else if (i.getStatus() == InvitationStatus.REJECTED)
                rejected_invitations++;
        }
        System.out.println("Accepted : " + accepted_invitations);
        System.out.println("Pending : " + pending_invitations);
        System.out.println("Rejected : " + rejected_invitations);
    }

    /**
     * Επιστρέφει τα προγραμματισμένα προγράμματα όλων των αθλητών για μια
     * συγκεκριμένη ημερομηνία.
     * 
     * @param date ημερομηνία αναζήτησης
     *             Eπιστρέφει λίστα με τις προπονήσεις της ημέρας.
     * @return λίστα με προπονήσεις ημέρας.
     */
    public List<ScheduledTraining> getScheduledProgramsByDate(LocalDate date) {
        List<ScheduledTraining> specific_date_scheduled_programs = new ArrayList<>();
        for (Athlete a : athletes) {
            ScheduledTraining s = a.getTrainingByDate(date);
            if (s != null) {
                specific_date_scheduled_programs.add(s);
            }
        }
        return specific_date_scheduled_programs;
    }
}
