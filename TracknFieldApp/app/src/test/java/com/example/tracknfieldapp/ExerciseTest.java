package com.example.tracknfieldapp;

import static org.junit.Assert.*;
import com.example.tracknfieldapp.domain.Exercise;
import com.example.tracknfieldapp.domain.ExerciseType;
import org.junit.Before;
import org.junit.Test;

/**
 * Κλάση δοκιμών για την  Exercise.
 * Ελέγχει την ορθή ανάθεση και ανάκτηση των στοιχείων μιας άσκησης.
 */
public class ExerciseTest {

    private Exercise exercise;
    private ExerciseType type;

    @Before
    public void setUp() {
        type = new ExerciseType("Push-ups");
        exercise = new Exercise(type, 15, 3, 0.0, 0.0);
    }

    /**
     *  Έλεγχος  getters
     */
    @Test
    public void testExerciseInitialization() {
        assertEquals(type, exercise.getType());
        assertEquals(15, exercise.getReps());
        assertEquals(3, exercise.getSets());
        assertEquals(0.0, exercise.getTime(), 0.001);
        assertEquals(0.0, exercise.getDistance(), 0.001);
    }

    /**
     *  Έλεγχος των setters
     */
    @Test
    public void testSetters() {
        ExerciseType newType = new ExerciseType("Squats");
        exercise.setType(newType);
        exercise.setReps(20);
        exercise.setSets(4);
        exercise.setTime(60.5);
        exercise.setDistance(100.0);

        assertEquals(newType, exercise.getType());
        assertEquals(20, exercise.getReps());
        assertEquals(4, exercise.getSets());
        assertEquals(60.5, exercise.getTime(), 0.001);
        assertEquals(100.0, exercise.getDistance(), 0.001);
    }
}
