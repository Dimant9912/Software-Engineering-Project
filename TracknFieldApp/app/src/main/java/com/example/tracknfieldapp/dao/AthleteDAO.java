package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Athlete;
import java.util.List;

/**
 * Διεπαφή για την πρόσβαση στα δεδομένα των αθλητών.
 */
public interface AthleteDAO {
    /**
     * Αποθηκεύει έναν αθλητή.
     * 
     * @param athlete αθλητής που θα αποθηκευτεί.
     */
    void save(Athlete athlete);

    /**
     * Αναζητά αθλητή με βάση το email του.
     * 
     * @param email email αθλητή που αναζητάμε
     *              Επιστρέφει τον αθλητή αν βρεθεί, αλλιώς null.
     * @return τον αθλητή αν βρεθεί, αλλιώς null.
     */
    Athlete findByEmail(String email);

    /**
     * Διαγράφει έναν αθλητή.
     * 
     * @param athlete αθλητής για διαγραφή.
     */
    void delete(Athlete athlete);

    /**
     * Επιστρέφει λίστα με όλους τους αθλητες που έχουν εγγραφεί.
     * 
     * @return λίστα με αθλητές που έχουν εγγραφεί.
     */
    List<Athlete> findAll();
}
