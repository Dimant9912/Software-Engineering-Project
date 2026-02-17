package com.example.tracknfieldapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.tracknfieldapp.dao.AthleteMemoryDAO;
import com.example.tracknfieldapp.dao.CoachMemoryDAO;
import com.example.tracknfieldapp.dao.DAOFactory;
import com.example.tracknfieldapp.dao.ExerciseTypeMemoryDAO;
import com.example.tracknfieldapp.dao.InvitationMemoryDAO;
import com.example.tracknfieldapp.dao.ScheduledTrainingMemoryDAO;
import com.example.tracknfieldapp.domain.Athlete;
import com.example.tracknfieldapp.domain.Coach;
import com.example.tracknfieldapp.domain.ExerciseType;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.ScheduledTraining;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Συγκεντρωτική κλάση δοκιμών για όλα τα Memory DAOs.
 */
public class MemoryDAOTest {

    private AthleteMemoryDAO athleteDAO;
    private CoachMemoryDAO coachDAO;
    private InvitationMemoryDAO invitationDAO;
    private ExerciseTypeMemoryDAO exerciseTypeDAO;
    private ScheduledTrainingMemoryDAO scheduledTrainingDAO;

    @Before
    public void setUp() {
        athleteDAO = new AthleteMemoryDAO();
        coachDAO = new CoachMemoryDAO();
        invitationDAO = new InvitationMemoryDAO();
        exerciseTypeDAO = new ExerciseTypeMemoryDAO();
        scheduledTrainingDAO = new ScheduledTrainingMemoryDAO();

        // Καθαρισμός static λιστών για ανεξάρτητα tests
        clearAllDAOs();
    }

    private void clearAllDAOs() {
        for (Athlete a : athleteDAO.findAll()) athleteDAO.delete(a);
        for (Coach c : coachDAO.findAll()) coachDAO.delete(c);
        for (Invitation i : invitationDAO.findAll()) invitationDAO.delete(i);
        for (ExerciseType et : exerciseTypeDAO.findAll()) exerciseTypeDAO.delete(et);
        for (ScheduledTraining st : scheduledTrainingDAO.findAll()) scheduledTrainingDAO.delete(st);
    }

    /**
     *  Έλεγχος AthleteMemoryDAO (Save, FindByEmail, Delete, All).
     * Καλύπτει και την περίπτωση που ο αθλητής δεν βρίσκεται.
     */
    @Test
    public void testAthleteDAO() {
        Athlete a = new Athlete(1, "a@test.com", "p", "F", "L", null, LocalDate.now());
        athleteDAO.save(a);
        
        // Έλεγχος εύρεσης
        assertEquals(a, athleteDAO.findByEmail("a@test.com"));
        
        // Έλεγχος μη εύρεσης (κάλυψη branch)
        assertNull(athleteDAO.findByEmail("wrong@test.com"));
        
        assertEquals(1, athleteDAO.findAll().size());
        athleteDAO.delete(a);
        assertNull(athleteDAO.findByEmail("a@test.com"));
    }

    /**
     * Έλεγχος CoachMemoryDAO (Save, FindByEmail, Delete, All).
     * Καλύπτει και την περίπτωση που ο προπονητής δεν βρίσκεται.
     */
    @Test
    public void testCoachDAO() {
        Coach c = new Coach(1, "c@test.com", "p", "F", "L", LocalDate.now());
        coachDAO.save(c);
        
        // Έλεγχος εύρεσης
        assertEquals(c, coachDAO.findByEmail("c@test.com"));
        
        // Έλεγχος μη εύρεσης (κάλυψη branch)
        assertNull(coachDAO.findByEmail("nonexistent@test.com"));
        
        assertEquals(1, coachDAO.findAll().size());
        coachDAO.delete(c);
        assertNull(coachDAO.findByEmail("c@test.com"));
    }

    /**
     * Έλεγχος InvitationMemoryDAO (Save, Find, Delete, All).
     * Καλύπτει και την περίπτωση που η πρόσκληση δεν βρίσκεται.
     */
    @Test
    public void testInvitationDAO() {
        Invitation i = new Invitation("i@test.com");
        invitationDAO.save(i);
        
        // Έλεγχος εύρεσης
        assertEquals(i, invitationDAO.find("i@test.com"));
        
        // Έλεγχος μη εύρεσης (κάλυψη branch)
        assertNull(invitationDAO.find("other@test.com"));
        
        assertEquals(1, invitationDAO.findAll().size());
        invitationDAO.delete(i);
        assertNull(invitationDAO.find("i@test.com"));
    }

    /**
     *  Έλεγχος ExerciseTypeMemoryDAO (Save, Find, Delete, All).
     * Καλύπτει και την περίπτωση που ο τύπος άσκησης δεν βρίσκεται.
     */
    @Test
    public void testExerciseTypeDAO() {
        ExerciseType et = new ExerciseType("Run");
        exerciseTypeDAO.save(et);
        
        // Έλεγχος εύρεσης
        assertEquals(et, exerciseTypeDAO.find("Run"));
        
        // Έλεγχος μη εύρεσης (κάλυψη branch)
        assertNull(exerciseTypeDAO.find("Swim"));
        
        assertEquals(1, exerciseTypeDAO.findAll().size());
        exerciseTypeDAO.delete(et);
        assertNull(exerciseTypeDAO.find("Run"));
    }

    /**
     *  Έλεγχος ScheduledTrainingMemoryDAO (Save, Delete, All, FindByAthlete).
     * Καλύπτει την εύρεση προπονήσεων ανά email αθλητή.
     */
    @Test
    public void testScheduledTrainingDAO() {
        Athlete a = new Athlete(1, "athlete@test.com", "p", "F", "L", null, LocalDate.now());
        ScheduledTraining st = new ScheduledTraining(LocalDate.now(), LocalTime.now(), new ArrayList<>());
        st.setAthlete(a);
        
        scheduledTrainingDAO.save(st);
        assertEquals(1, scheduledTrainingDAO.findAll().size());
        
        // Έλεγχος findByAthlete (επιτυχία)
        List<ScheduledTraining> found = scheduledTrainingDAO.findByAthlete("athlete@test.com");
        assertEquals(1, found.size());
        assertEquals(st, found.get(0));
        
        // Έλεγχος findByAthlete (αποτυχία/κενή λίστα)
        assertTrue(scheduledTrainingDAO.findByAthlete("other@test.com").isEmpty());
        
        scheduledTrainingDAO.delete(st);
        assertEquals(0, scheduledTrainingDAO.findAll().size());
    }

    /**
     *  Έλεγχος του DAOFactory για την ορθή επιστροφή των DAOs.
     */
    @Test
    public void testDAOFactory() {
        DAOFactory factory = DAOFactory.getInstance();
        assertNotNull(factory.getAthleteDAO());
        assertNotNull(factory.getCoachDAO());
        assertNotNull(factory.getInvitationDAO());
        assertNotNull(factory.getExerciseTypeDAO());
        assertNotNull(factory.getScheduledTrainingDAO());
    }
}
