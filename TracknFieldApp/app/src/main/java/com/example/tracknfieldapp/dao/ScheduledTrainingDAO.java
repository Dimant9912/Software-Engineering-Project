package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.ScheduledTraining;
import java.util.List;

/**
 * Διεπαφή για την πρόσβαση στα δεδομένα των προγραμματισμένων προπονήσεων.
 */
public interface ScheduledTrainingDAO {
    /**
     * Αποθηκεύει μια προγραμματισμένη προπόνηση.
     * 
     * @param scheduledTraining προγραμματισμένη προπόνηση προς αποθήκευση.
     */
    void save(ScheduledTraining scheduledTraining);

    /**
     * Διαγράφει μια προγραμματισμένη προπόνηση.
     * 
     * @param scheduledTraining προγραμματισμένη προπόνηση προς διαγραφή.
     */
    void delete(ScheduledTraining scheduledTraining);

    /**
     * Επιστρέφει λίστα με όλες τις προγραμματισμένες προπονήσεις.
     * 
     * @return λίστα με όλες τις προγραμματισμένες προπονήσεις.
     */
    List<ScheduledTraining> findAll();

    /**
     * Αναζητά προπονήσεις με βάση το email του αθλητή και επιστρέφει λίστα με τις
     * προπονήσεις του αθλητη.
     * 
     * @param email email αθλητή για τον οποίο ψάχνουμε τις προπονήσεις.
     * @return λίστα με τις προπονήσεις του αθλητή.
     * 
     */
    List<ScheduledTraining> findByAthlete(String email);
}
