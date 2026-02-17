package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.ExerciseType;
import java.util.ArrayList;
import java.util.List;

/**
 * Υλοποίηση του ExerciseTypeDAO που αποθηκεύει τα δεδομένα στη μνήμη.
 */
public class ExerciseTypeMemoryDAO implements ExerciseTypeDAO {
    private static List<ExerciseType> types = new ArrayList<>();

    /**
     * Αναζητά τύπο άσκησης βάση ονόματος και αν βρεθεί τον επιστρέφει, αλλιώς null.
     * 
     * @param name όνομα τύπου άσκησης.
     * @return τύπο άσκησης αν βρεθεί, αλλιώς null.
     */

    @Override
    public ExerciseType find(String name) {
        for (ExerciseType type : types) {
            if (type.getTypeName().equalsIgnoreCase(name))
                return type;
        }
        return null;
    }

    /**
     * Προσθήκη νέου τύπου άσκησης.
     * 
     * @param type τύπος άσκησης που θα αποθηκευτεί.
     */

    @Override
    public void save(ExerciseType type) {
        types.add(type);
    }

    /**
     * Επιστρέφει λίστα με όλους τους τύπους ασκήσεων.
     * 
     * @return λίστα με όλους τους τύπους ασκήσεων.
     */

    @Override
    public List<ExerciseType> findAll() {
        return new ArrayList<>(types);
    }

    /**
     * Διαγράφει έναν τύπο άσκησης.
     * 
     * @param type τύπος άσκησης για διαγραφή.
     */

    @Override
    public void delete(ExerciseType type) {
        types.remove(type);
    }
}
