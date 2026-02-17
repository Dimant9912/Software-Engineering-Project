package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.ScheduledTraining;
import java.util.ArrayList;
import java.util.List;

/**
 * Υλοποίηση του ScheduledTrainingDAO που αποθηκεύει τα δεδομένα στη μνήμη.
 */
public class ScheduledTrainingMemoryDAO implements ScheduledTrainingDAO {

    private static List<ScheduledTraining> trainings = new ArrayList<>();

    /**
     * Προσθήκη νέας προγραμματισμένης προπόνησης.
     * 
     * @param training προπόνηση προς αποθήκευση.
     */

    @Override
    public void save(ScheduledTraining training) {
        trainings.add(training);
    }

    /**
     * Διαγραφή μίας προγραμματισμένης προπόνησης.
     * 
     * @param training προπόνηση προς διαγραφή.
     */

    @Override
    public void delete(ScheduledTraining training) {
        trainings.remove(training);
    }

    /**
     * Επιστρέφει το σύνολο των προπονήσεων.
     * 
     * @return σύνολο των προπονήσεων.
     */

    @Override
    public List<ScheduledTraining> findAll() {
        return new ArrayList<>(trainings);
    }

    /**
     * Αναζητά μέσω email όλες τις προπονήσεις ενός αθλητή και τις επιστρέφει.
     * 
     * @param email email αθλητή με βάση το οποίο γίνεται η αναζήτηση
     * @return όλες οι προπονήσεις του αθλητή.
     */

    @Override
    public List<ScheduledTraining> findByAthlete(String email) {
        List<ScheduledTraining> result = new ArrayList<>();
        for (ScheduledTraining t : trainings) {
            if (t.getAthlete().getEmail().equalsIgnoreCase(email)) {
                result.add(t);
            }
        }
        return result;
    }
}
