package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Coach;
import java.util.List;

/**
 * Διεπαφή για την πρόσβαση στα δεδομένα των προπονητών.
 */
public interface CoachDAO {
    /**
     * Αποθηκεύει έναν προπονητή.
     * 
     * @param coach προπονητής για αποθήκευση.
     */
    void save(Coach coach);

    /**
     * Αναζητά προπονητή με βάση το email του. και επιστρέφει τον προπονητή αν
     * βρέθηκε αλλιώς null
     * 
     * @param email email αθλητή.
     * @return τον προπονητή αν βρεθεί, αλλιώς null.
     * 
     */
    Coach findByEmail(String email);

    /**
     * Διαγράφει έναν προπονητή.
     * 
     * @param coach προπονητής που θα διαγραφεί.
     */
    void delete(Coach coach);

    /**
     * Επιστρέφει λίστα με όλους τους προπονητές.
     * 
     * @return λίστα με όλους το προπονητές.
     */
    List<Coach> findAll();
}
