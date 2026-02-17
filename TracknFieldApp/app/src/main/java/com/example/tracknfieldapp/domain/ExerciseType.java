package com.example.tracknfieldapp.domain;

/**
 * Τύπος άσκησης (π.χ. "Τρέξιμο 1000μ", "Άλμα εις μήκος").
 */
public class ExerciseType {
    private String typeName;

    /**
     * Κατασκευαστής κλάσης.
     * 
     * @param typeΝame όνομα του τύπου της άσκησης.
     */
    public ExerciseType(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Επιστρέφει το όνομα του τύπου της άσκησης
     * 
     * @return όνομα του τύπου της άσκησης.
     */

    public String getTypeName() {
        return typeName;
    }

    /**
     * Επικαιροποίηση του ονόματος του τύπου της άσκησης.
     * 
     * @param typeName επικαιροποιημένο όνομα του τύπου της άσκησης.
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
