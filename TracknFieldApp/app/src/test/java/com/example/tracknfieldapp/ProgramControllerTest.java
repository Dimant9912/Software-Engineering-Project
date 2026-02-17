package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.controller.ProgramController;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Κλάση δοκιμών για τον ProgramController.
 * Ελέγχει τη διαχείριση προσκλήσεων, την ανάθεση προπονήσεων και την έκδοση αναφορών.
 */
public class ProgramControllerTest {

    private ProgramController programController;
    private Coach coach;
    private Athlete athlete;
    private Invitation invitation;

    /**
     * Αρχικοποίηση δεδομένων πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        programController = new ProgramController();
        coach = new Coach(1, "coach@test.com", "pass", "G", "Z", LocalDate.now());
        invitation = coach.inviteAthlete("athlete@test.com");
        athlete = new Athlete(2, "athlete@test.com", "pass", "N", "P", invitation, LocalDate.now());
    }

    /**
     * Έλεγχος της δημιουργίας πρόσκλησης μέσω του Controller.
     */
    @Test
    public void testCreateInvitation() {
        Invitation invite = programController.createInvitation(coach, "new@test.com");
        assertNotNull(invite);
        assertEquals("new@test.com", invite.getAthlete_email());
    }

    /**
     *  Έλεγχος αποδοχής πρόσκλησης και προσθήκης αθλητή στην ομάδα του προπονητή.
     */
    @Test
    public void testReceiveInvitationAccepted() {
        invitation.accept();
        boolean result = programController.receiveInvitation(coach, athlete, invitation);
        assertTrue("Invitation should be accepted and athlete added", result);
        assertTrue(coach.getAthletes().contains(athlete));
    }

    /**
     * Έλεγχος αποτυχίας λήψης πρόσκλησης όταν αυτή βρίσκεται ακόμη σε κατάσταση PENDING.
     */
    @Test
    public void testReceiveInvitationPending() {
        boolean result = programController.receiveInvitation(coach, athlete, invitation);
        assertFalse(result);
    }

    /**
     *  Έλεγχος του προγραμματισμού μιας προπόνησης για έναν αθλητή.
     */
    @Test
    public void testScheduleTraining() {
        ScheduledTraining training = new ScheduledTraining(LocalDate.now(), LocalTime.now(), new ArrayList<>());
        programController.scheduleTraining(athlete, training);
        
        assertEquals(athlete, training.getAthlete());
        assertTrue(athlete.getScheduled_trainings().contains(training));
    }

    /**
     * Έλεγχος ανάκτησης των προγραμματισμένων προπονήσεων μιας συγκεκριμένης ημέρας για έναν προπονητή.
     */
    @Test
    public void testGetSpecificDayScheduledTraining() {
        invitation.accept();
        coach.addAthlete(athlete, invitation);
        
        LocalDate date = LocalDate.now();
        ScheduledTraining training = new ScheduledTraining(date, LocalTime.now(), new ArrayList<>());
        programController.scheduleTraining(athlete, training);
        
        List<ScheduledTraining> list = programController.get_specific_day_scheduled_training(coach, date);
        assertEquals(1, list.size());
        assertEquals(training, list.get(0));
    }

    /**
     *  Έλεγχος της εκτύπωσης της ημερήσιας αναφοράς προπονήσεων (κάλυψη printDailyReport).
     */
    @Test
    public void testPrintDailyReport() {
        invitation.accept();
        coach.addAthlete(athlete, invitation);
        
        LocalDate date = LocalDate.now();
        ScheduledTraining training = new ScheduledTraining(date, LocalTime.now(), new ArrayList<>());
        programController.scheduleTraining(athlete, training);
        
        // εκτύπωση και της περίπτωσης άδειας λίστας
        programController.printDailyReport(coach, date);
        programController.printDailyReport(coach, date.plusDays(1));
    }
}
