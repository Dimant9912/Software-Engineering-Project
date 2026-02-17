package com.example.tracknfieldapp.dao;

import com.example.tracknfieldapp.domain.Invitation;
import java.util.ArrayList;
import java.util.List;

/**
 * Υλοποίηση του InvitationDAO που αποθηκεύει τα δεδομένα στη μνήμη.
 */
public class InvitationMemoryDAO implements InvitationDAO {
    private static List<Invitation> invitations = new ArrayList<>();

    /**
     * Προσθήκη νέας πρόσκλησης στη λίστα.
     * 
     * @param invitation πρόσκληση που θα αποθηκευτεί.
     */

    @Override
    public void save(Invitation invitation) {
        invitations.add(invitation);
    }

    /**
     * Αναζήτηση πρόσκλησης βάση email αθλητή, αν βρεθεί την επιστρέφει αλλιώς null.
     * 
     * @param athleteEmail
     * @return πρόσκληση αν βρεθεί, αλλιώς null.
     */

    @Override
    public Invitation find(String athleteEmail) {
        for (Invitation invite : invitations) {
            if (invite.getAthlete_email().equalsIgnoreCase(athleteEmail))
                return invite;
        }
        return null;
    }

    /**
     * Διαγραφή πρόσκλησης.
     * 
     * @param invitation πρόσκληση προς διαγραφή.
     */

    @Override
    public void delete(Invitation invitation) {
        invitations.remove(invitation);
    }

    /**
     * Επιστροφή όλων των προσκλήσεων που είναι αποθηκευμένες.
     * 
     * @return όλες τις αποθηκευμένες προσκλήσεις.
     */

    @Override
    public List<Invitation> findAll() {
        return new ArrayList<>(invitations);
    }
}
