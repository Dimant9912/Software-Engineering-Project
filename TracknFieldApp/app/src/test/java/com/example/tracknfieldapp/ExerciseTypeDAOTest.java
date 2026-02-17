package com.example.tracknfieldapp;

import static org.junit.Assert.*;

import com.example.tracknfieldapp.domain.ExerciseType;

import org.junit.Before;
import org.junit.Test;

/**
 * Κλάση δοκιμών για την  ExerciseType.
 * Ελέγχει την ορθή δημιουργία και τροποποίηση των τύπων ασκήσεων.
 */
public class ExerciseTypeDAOTest {

    private ExerciseType exerciseType;
    private String name = "Sprints";

    /**
     * Αρχικοποίηση δεδομένων πριν από κάθε δοκιμή.
     */
    @Before
    public void setUp() {
        exerciseType = new ExerciseType(name);
    }

    /**
     *  Έλεγχος της ορθής δημιουργίας ενός ExerciseType και της ανάκτησης του ονόματός του.
     */
    @Test
    public void testExerciseTypeCreation() {
        assertNotNull(exerciseType);
        assertEquals(name, exerciseType.getTypeName());
    }

    /**
     *  Έλεγχος της δυνατότητας αλλαγής του ονόματος ενός τύπου άσκησης.
     */
    @Test
    public void testSetTypeName() {
        exerciseType.setTypeName("Jumping");
        assertEquals("Jumping", exerciseType.getTypeName());
    }
}
