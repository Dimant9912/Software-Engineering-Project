package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.InvitationStatus;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Κλάση δοκιμών για την  Athlete.
 * Καλύπτει τη λειτουργικότητα των στοιχείων του αθλητή, τη διαχείριση προπονήσεων
 * και την ανταπόκριση σε προσκλήσεις.
 */
public class AthleteTest {

    private Athlete athlete;
    private Invitation invite;
    private LocalDate birthDate;

    /**
     * Αρχικοποίηση δεδομένων πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        birthDate = LocalDate.of(2000, 1, 1);
        invite = new Invitation("athlete@test.com");
        athlete = new Athlete(1, "athlete@test.com", "password123", "George", "Zervas", invite, birthDate);
    }

    /**
     *  Έλεγχος της σωστής αρχικοποίησης των πεδίων του αθλητή κατά τη δημιουργία του.
     */
    @Test
    public void testAthleteInitialization() {
        assertEquals(1, athlete.getId());
        assertEquals("athlete@test.com", athlete.getEmail());
        assertEquals("George", athlete.getFirst_name());
        assertEquals("Zervas", athlete.getLast_name());
        assertEquals(birthDate, athlete.getBirthDate());
        assertEquals(invite, athlete.get_Invite());
        assertTrue(athlete.getScheduled_trainings().isEmpty());
    }

    /**
     *  Έλεγχος της επιτυχούς αποδοχής μιας πρόσκλησης και της αλλαγής του status σε ACCEPTED.
     */
    @Test
    public void testRespondToInvitationAccept() {
        athlete.respondToInvitation(invite, true);
        assertEquals(InvitationStatus.ACCEPTED, invite.getStatus());
    }

    /**
     *  Έλεγχος της απόρριψης μιας πρόσκλησης και της αλλαγής του status σε REJECTED.
     */
    @Test
    public void testRespondToInvitationReject() {
        athlete.respondToInvitation(invite, false);
        assertEquals(InvitationStatus.REJECTED, invite.getStatus());
    }

    /**
     *  Έλεγχος της πλήρους διαδικασίας διαχείρισης προπονήσεων (προσθήκη, εύρεση, αφαίρεση).
     */
    @Test
    public void testScheduledTrainingManagement() {
        LocalDate trainingDate = LocalDate.now();
        LocalTime trainingTime = LocalTime.of(10, 0);
        ScheduledTraining training = new ScheduledTraining(trainingDate, trainingTime, new ArrayList<>());
        
        athlete.addScheduledTraining(training);
        assertEquals(1, athlete.getScheduled_trainings().size());
        
        ScheduledTraining found = athlete.getTrainingByDate(trainingDate);
        assertNotNull(found);
        assertEquals(trainingTime, found.getTime());
        
        athlete.removeScheduledTraining(training);
        assertTrue(athlete.getScheduled_trainings().isEmpty());
        assertNull(athlete.getTrainingByDate(trainingDate));
    }
    
    /**
     *  Έλεγχος της λειτουργίας login με χρήση σωστού και λανθασμένου κωδικού.
     */
    @Test
    public void testLogin() {
        assertTrue(athlete.login("password123"));
        assertFalse(athlete.login("wrongpassword"));
    }

    /**
     *  Έλεγχος αν η μέθοδος login επιστρέφει true για έναν νέο αθλητή με συγκεκριμένο κωδικό.
     */
    @Test
    public void testAthleteLoginSuccess() {
        Invitation i = new Invitation("athlete@test.com");
        Athlete localAthlete = new Athlete(1, "athlete@test.com", "12345", "George", "Z.", i, LocalDate.of(1990, 1, 1));

        boolean result = localAthlete.login("12345");
        assertTrue("Το login θα έπρεπε να πετύχει με τον σωστό κωδικό", result);
    }

    /**
     *  Έλεγχος αν η μέθοδος αναζήτησης επιστρέφει τη σωστή προπόνηση όταν υπάρχουν πολλαπλές εγγραφές.
     */
    @Test
    public void testFindCorrectTraining(){
        LocalDate date1 = LocalDate.of(2023, 10, 1);
        LocalDate date2 = LocalDate.of(2023, 10, 2);
        ScheduledTraining s1 = new ScheduledTraining(date1, LocalTime.of(10, 0), new ArrayList<>());
        ScheduledTraining s2 = new ScheduledTraining(date2, LocalTime.of(10, 0), new ArrayList<>());
        athlete.addScheduledTraining(s1);
        athlete.addScheduledTraining(s2);
        assertEquals(s1, athlete.getTrainingByDate(date1));
        assertNotEquals(s2, athlete.getTrainingByDate(date1));
    }

    /**
     *  Επιβεβαίωση της σωστής εύρεσης προπόνησης βάσει ημερομηνίας.
     */
    @Test
    public void test_getScheduledTraining_byDate(){
        LocalDate date1 = LocalDate.of(2023, 10, 1);
        ScheduledTraining s1 = new ScheduledTraining(date1, LocalTime.of(10, 0), new ArrayList<>());
        athlete.addScheduledTraining(s1);
        assertEquals(s1, athlete.getTrainingByDate(date1));
    }

    /**
     * Έλεγχος ότι η αναζήτηση επιστρέφει null όταν δεν υπάρχει προπόνηση τη συγκεκριμένη ημερομηνία.
     */
    @Test
    public void test_getTrainingByDate_NotFound_With_Other_Trainings() {
        LocalDate date1 = LocalDate.of(2023, 10, 1);
        LocalDate search_date = LocalDate.of(2023, 11, 1);
        ScheduledTraining s1 = new ScheduledTraining(date1, LocalTime.of(10, 0), new ArrayList<>());
        athlete.addScheduledTraining(s1);

        assertNull(athlete.getTrainingByDate(search_date));
    }

    /**
     * Σκοπός: Έλεγχος ότι η μέθοδος getScheduled_trainings επιστρέφει μια μη-μηδενική λίστα.
     */
    @Test
    public void test_getScheduledTrainings_List() {
        assertNotNull(athlete.getScheduled_trainings());
        assertEquals(0, athlete.getScheduled_trainings().size());
    }
}
