package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.ExerciseType;
import java.util.List;

/**
 * Διεπαφή για την πρόσβαση στα δεδομένα των τύπων ασκήσεων.
 */
public interface ExerciseTypeDAO {
    /**
     * Αποθηκεύει έναν τύπο άσκησης.
     * 
     * @param exercise_type τύπος άσκησης για αποθήκευση.
     */
    void save(ExerciseType exercise_type);

    /**
     * Επιστρέφει λίστα με όλους τους τύπους ασκήσεων.
     * 
     * @return λίστα με όλους τους τύπους ασκήσεων.
     * 
     */
    List<ExerciseType> findAll();

    /**
     * Αναζητά τύπο άσκησης με βάση το όνομά του και τον επιστρέφει αν βρεθεί,αλλιως
     * επιστρέφει null
     * 
     * @param name όνομα άσκησης που αναζητάμε.
     * @return όνομα άσκησεις αν βρεθεί, αλλιώς null.
     */
    ExerciseType find(String name);

    /**
     * Διαγράφει έναν τύπο άσκησης.
     * 
     * @param ExerciseType type τύπος άσκησης για διαγραφή.
     */
    void delete(ExerciseType type);
}
