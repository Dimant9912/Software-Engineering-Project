package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Athlete;
import java.util.ArrayList;
import java.util.List;

/**
 * AthleteDAO , αποθηκεύει τα δεδομένα στη μνήμη.
 */
public class AthleteMemoryDAO implements AthleteDAO {

    /** Στατική λίστα, χρησιμεύει σαν βάση δεδοομένων */
    private static List<Athlete> athletes = new ArrayList<>();

    /**
     * Ελέγχει την ήδη ύπαρξη αθλητή με ίδιο mail.
     * 
     * @param athlete αθλητής που θα αποθηκευτεί.
     */

    @Override
    public void save(Athlete athlete) {
        if (findByEmail(athlete.getEmail()) == null) {
            athletes.add(athlete);
        }
    }

    /**
     * Αναζητά στη λίστα με βάση το email.
     * 
     * @param email email αναζήτησης.
     */

    @Override
    public Athlete findByEmail(String email) {
        for (Athlete athlete : athletes) {
            if (athlete.getEmail().equalsIgnoreCase(email)) {
                return athlete;
            }
        }
        return null;
    }

    /**
     * Αφαιρεί τον αθλητή από την λίστα.
     * 
     * @param athlete αθλητής προς αφαίρεση.
     */

    @Override
    public void delete(Athlete athlete) {
        athletes.remove(athlete);
    }

    /**
     * Επιστρέφει αντίγραφο λίστας αθλητών.
     * 
     * @return αντίγραφο λίστας αθητών.
     */

    @Override
    public List<Athlete> findAll() {
        return new ArrayList<>(athletes);
    }
}
