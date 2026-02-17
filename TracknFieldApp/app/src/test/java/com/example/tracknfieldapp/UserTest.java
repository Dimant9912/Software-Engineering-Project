package com.example.tracknfieldapp;

import static org.junit.Assert.*;
import com.example.tracknfieldapp.domain.User;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;

/**
 * Κλάση δοκιμών για την  User.
 * Ελέγχει τη βασική λειτουργικότητα των χρηστών, όπως getters, setters και login.
 */
public class UserTest {

    private User user;
    private LocalDate birthDate;

    /**
     * Δημιουργία μιας συγκεκριμένης υλοποίησης του User για δοκιμή, 
     * καθώς η κλάση User είναι abstract.
     */
    @Before
    public void setUp() {
        birthDate = LocalDate.of(1990, 5, 15);
        // Χρησιμοποιούμε μια ανώνυμη κλάση για να ελέγξουμε την abstract User
        user = new User(10, "user@test.com", "pass123", "John", "Smith", birthDate) {};
    }

    /**
     *  Έλεγχος getters
     */
    @Test
    public void testUserInitialization() {
        assertEquals(10, user.getId());
        assertEquals("user@test.com", user.getEmail());
        assertEquals("pass123", user.getPassword());
        assertEquals("John", user.getFirst_name());
        assertEquals("Smith", user.getLast_name());
        assertEquals(birthDate, user.getBirthDate());
    }

    /**
     *  Έλεγχος των setters
     */
    @Test
    public void testSetters() {
        LocalDate newDate = LocalDate.of(1985, 10, 20);
        user.setId(20);
        user.setEmail("new@test.com");
        user.setPassword("newpass");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setBirthDate(newDate);

        assertEquals(20, user.getId());
        assertEquals("new@test.com", user.getEmail());
        assertEquals("newpass", user.getPassword());
        assertEquals("Jane", user.getFirst_name());
        assertEquals("Doe", user.getLast_name());
        assertEquals(newDate, user.getBirthDate());
    }

    /**
     * Έλεγχος της  login για σωστό και λανθασμένο κωδικό,
     * καθώς και για την περίπτωση null κωδικού.
     */
    @Test
    public void testLogin() {
        assertTrue(user.login("pass123"));
        assertFalse(user.login("wrong"));
        assertFalse(user.login(null));
        
        user.setPassword(null);
        assertFalse(user.login("any"));
    }

    /**
     *  Έλεγχος της συμπεριφοράς των πεδίων όταν ανατίθενται null τιμές.
     */
    @Test
    public void testNullFields(){
        user.setEmail(null);
        user.setPassword(null);
        user.setFirst_name(null);
        user.setLast_name(null);
        user.setBirthDate(null);

        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getFirst_name());
        assertNull(user.getLast_name());
        assertNull(user.getBirthDate());
    }
}
