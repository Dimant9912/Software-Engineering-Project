package com.example.tracknfieldapp;

import static org.junit.Assert.*;
import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.InvitationStatus;
import com.example.tracknfieldapp.domain.ScheduledTraining;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Κλάση δοκιμών για την οντότητα Coach.
 * Καλύπτει τη λ διαχείριση αθλητών, προσκλήσεων και προγραμματισμού προπονήσεων.
 */
public class CoachTest {

    private Coach coach;
    private LocalDate birthDate;

    /**
     * Αρχικοποίηση δεδομένων προπονητή πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        birthDate = LocalDate.of(1980, 1, 1);
        coach = new Coach(1, "coach@test.com", "pass123", "Nikos", "Papadopoulos", birthDate);
    }

    /**
     * Έλεγχος της σωστής αρχικοποίησης των πεδίων του προπονητή.
     */
    @Test
    public void testCoachInitialization() {
        assertEquals(1, coach.getId());
        assertEquals("coach@test.com", coach.getEmail());
        assertEquals("Nikos", coach.getFirst_name());
        assertEquals("Papadopoulos", coach.getLast_name());
        assertTrue(coach.getAthletes().isEmpty());
    }

    /**
     * Έλεγχος της δημιουργίας και αποστολής πρόσκλησης σε αθλητή.
     */
    @Test
    public void testInviteAthlete() {
        String athleteEmail = "athlete@test.com";
        Invitation invitation = coach.inviteAthlete(athleteEmail);
        
        assertNotNull(invitation);
        assertEquals(athleteEmail, invitation.getAthlete_email());
        assertEquals(InvitationStatus.PENDING, invitation.getStatus());
    }

    /**
     * Έλεγχος επιτυχούς προσθήκης αθλητή όταν η πρόσκληση έχει γίνει αποδεκτή.
     */
    @Test
    public void testAddAthleteSuccess() {
        String athleteEmail = "athlete@test.com";
        Invitation invitation = coach.inviteAthlete(athleteEmail);
        invitation.accept();
        
        Athlete athlete = new Athlete(2, athleteEmail, "pass", "John", "Doe", invitation, LocalDate.of(2000, 1, 1));
        
        boolean added = coach.addAthlete(athlete, invitation);
        assertTrue(added);
        assertTrue(coach.getAthletes().contains(athlete));
    }

    /**
     * Έλεγχος αποτυχίας προσθήκης αθλητή όταν η πρόσκληση ΔΕΝ έχει γίνει αποδεκτή.
     */
    @Test
    public void testAddAthleteFailure_NotAccepted() {
        String athleteEmail = "athlete@test.com";
        Invitation invitation = coach.inviteAthlete(athleteEmail);
        // Η πρόσκληση παραμένει PENDING
        
        Athlete athlete = new Athlete(2, athleteEmail, "pass", "John", "Doe", invitation, LocalDate.of(2000, 1, 1));
        
        boolean added = coach.addAthlete(athlete, invitation);
        assertFalse(added);
    }

    /**
     * Έλεγχος αποτυχίας προσθήκης αθλητή όταν η πρόσκληση δεν ανήκει στον προπονητή.
     */
    @Test
    public void testAddAthleteFailure_WrongInvitation() {
        Invitation foreignInvitation = new Invitation("other@test.com");
        foreignInvitation.accept();
        Athlete athlete = new Athlete(2, "other@test.com", "pass", "John", "Doe", foreignInvitation, LocalDate.of(2000, 1, 1));
        
        boolean added = coach.addAthlete(athlete, foreignInvitation);
        assertFalse(added);
    }

    /**
     * Έλεγχος αποτυχίας προσθήκης αθλητή όταν το email του αθλητή δεν ταιριάζει με την πρόσκληση.
     */
    @Test
    public void testAddAthleteFailure_EmailMismatch() {
        Invitation invitation = coach.inviteAthlete("correct@test.com");
        invitation.accept();
        Athlete wrongAthlete = new Athlete(2, "wrong@test.com", "pass", "John", "Doe", invitation, LocalDate.of(2000, 1, 1));
        
        boolean added = coach.addAthlete(wrongAthlete, invitation);
        assertFalse(added);
    }

    /**
     * Έλεγχος δημιουργίας νέας προγραμματισμένης προπόνησης για έναν αθλητή που ανήκει στον προπονητή.
     */
    @Test
    public void testNewScheduledTrainingSuccess() {
        String athleteEmail = "athlete@test.com";
        Invitation invitation = coach.inviteAthlete(athleteEmail);
        invitation.accept();
        Athlete athlete = new Athlete(2, athleteEmail, "pass", "John", "Doe", invitation, LocalDate.of(2000, 1, 1));
        coach.addAthlete(athlete, invitation);
        
        ScheduledTraining training = new ScheduledTraining(LocalDate.now(), LocalTime.NOON, new ArrayList<>());
        coach.newScheduledTraining(athlete, training);
        
        assertTrue(athlete.getScheduled_trainings().contains(training));
    }

    /**
     * Έλεγχος: Επιβεβαίωση ότι ΔΕΝ ανατίθεται προπόνηση αν ο αθλητής δεν ανήκει στον προπονητή.
     */
    @Test
    public void testNewScheduledTrainingAthleteNotFound() {
        Athlete stranger = new Athlete(3, "stranger@test.com", "pass", "Jane", "Doe", null, LocalDate.now());
        ScheduledTraining training = new ScheduledTraining(LocalDate.now(), LocalTime.NOON, new ArrayList<>());
        
        coach.newScheduledTraining(stranger, training);
        
        // ΔΙΟΡΘΩΣΗ: Πρέπει να είναι FALSE, γιατί ο κώδικας σωστά δεν προσθέτει την προπόνηση
        assertFalse("Η προπόνηση ΔΕΝ πρέπει να προστεθεί σε ξένο αθλητή", 
                    stranger.getScheduled_trainings().contains(training));
    }

    /**
     * Έλεγχος εμφάνισης αναφοράς προσκλήσεων για όλες τις καταστάσεις.
     */
    @Test
    public void testGetInvitationsReport() {
        coach.inviteAthlete("accepted@test.com").accept();
        coach.inviteAthlete("pending@test.com");
        coach.inviteAthlete("rejected@test.com").reject();
        
        coach.getInvitationsReport();
    }

    /**
     * Έλεγχος εύρεσης προγραμμάτων αθλητών βάση συγκεκριμένης ημερομηνίας.
     */
    @Test
    public void testGetScheduledProgramsByDate() {
        LocalDate targetDate = LocalDate.of(2023, 12, 25);
        LocalDate otherDate = LocalDate.of(2023, 12, 26);
        
        // Αθλητής 1: Έχει προπόνηση τη σωστή μέρα
        Invitation i1 = coach.inviteAthlete("a1@test.com");
        i1.accept();
        Athlete a1 = new Athlete(2, "a1@test.com", "pass", "A1", "L1", i1, LocalDate.of(2000, 1, 1));
        coach.addAthlete(a1, i1);
        ScheduledTraining st1 = new ScheduledTraining(targetDate, LocalTime.NOON, new ArrayList<>());
        a1.addScheduledTraining(st1);
        
        // Αθλητής 2: Έχει προπόνηση άλλη μέρα
        Invitation i2 = coach.inviteAthlete("a2@test.com");
        i2.accept();
        Athlete a2 = new Athlete(3, "a2@test.com", "pass", "A2", "L2", i2, LocalDate.of(2000, 1, 1));
        coach.addAthlete(a2, i2);
        ScheduledTraining st2 = new ScheduledTraining(otherDate, LocalTime.NOON, new ArrayList<>());
        a2.addScheduledTraining(st2);
        
        List<ScheduledTraining> result = coach.getScheduledProgramsByDate(targetDate);
        assertEquals(1, result.size());
        assertEquals(st1, result.get(0));
    }

    /**
     * Έλεγχος της μεθόδου login.
     */
    @Test
    public void testCoachLogin() {
        assertTrue(coach.login("pass123"));
        assertFalse(coach.login("wrong"));
    }
}