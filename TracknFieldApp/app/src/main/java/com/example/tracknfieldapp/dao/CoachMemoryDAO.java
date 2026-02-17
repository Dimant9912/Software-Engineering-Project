package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Coach;
import java.util.ArrayList;
import java.util.List;

/**
 * Υλοποίηση του CoachDAO που αποθηκεύει τα δεδομένα στη μνήμη.
 */
public class CoachMemoryDAO implements CoachDAO {
    private static List<Coach> coaches = new ArrayList<>();

    /**
     * Αποθήκευση προπονητή στη λίστα ο οποίος δεν υπάρχει ήδη.
     * 
     * @param coach προπονητής για αποθήκευση.
     */

    @Override
    public void save(Coach coach) {
        if (findByEmail(coach.getEmail()) == null) {
            coaches.add(coach);
        }
    }

    /**
     * Αναζήτηση προπονητή με βάση το email του, επιστρέφει τον προπονητή αν βρεθεί,
     * αλλιώς null.
     * 
     * @param email email προπονητή που αναζητούμε.
     * @return τον προπονητή αν βρεθεί, αλλιώς null
     */

    @Override
    public Coach findByEmail(String email) {
        for (Coach coach : coaches) {
            if (coach.getEmail().equals(email)) {
                return coach;
            }
        }
        return null;
    }

    /**
     * Διαγραφή προπονητή από την λίστα.
     * 
     * @param coach προπονητής προς διαγραφή.
     */

    @Override
    public void delete(Coach coach) {
        coaches.remove(coach);
    }

    /**
     * Επιστρέφει νέα λίσα με όλους τους προπονητές που έχουν αποθηκευτεί.
     * 
     * @return νέα λίστα με προπονητής που έχουν αποθηκευτεί.
     */

    @Override
    public List<Coach> findAll() {
        return new ArrayList<>(coaches);
    }
}
