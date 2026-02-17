package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Invitation;
import java.util.List;

/**
 * Διεπαφή για την πρόσβαση στα δεδομένα των προσκλήσεων.
 */
public interface InvitationDAO {
    /**
     * Αποθηκεύει μια πρόσκληση.
     * 
     * @param invitation πρόσκληση προς αποθήκευση.
     */
    void save(Invitation invitation);

    /**
     * Αναζητά πρόσκληση με βάση το email του αθλητή και επιστρέφει την πρόσκληση αν
     * βρεθεί ο αθλητής,αλλιώς null.
     * 
     * @param athleteEmail mail αθλητή που στάλθηκε η πρόσκληση.
     * @return πρόσκληση αν βρεθεί ο αθλητής, αλλιώς null.
     */
    Invitation find(String athleteEmail);

    /**
     * Διαγράφει μια πρόσκληση.
     * 
     * @param invitation πρόσκληση προς διαγραφή.
     * 
     */
    void delete(Invitation invitation);

    /**
     * Επιστρέφει λίστα με όλες τις προσκλήσεις.
     * 
     * @return λίστα με όλες τις προσκλήσεις.
     * 
     */
    List<Invitation> findAll();
}
