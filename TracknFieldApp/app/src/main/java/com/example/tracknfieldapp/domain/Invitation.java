package com.example.tracknfieldapp.domain;

import java.util.UUID;

/**
 * Πρόσκληση που στέλνει ένας προπονητής σε έναν αθλητή.
 * Κάθε πρόσκληση έχει μοναδικό κωδικό και ανάλογα με την κατάστασή της
 * καθορίζεται η ροή.
 */
public class Invitation {
    private final String InvitationCode;
    private final String athlete_email;
    private InvitationStatus status;

    /**
     * Δημιουργεί μια νέα πρόσκληση με τυχαίο κωδικό.
     * 
     * @paramathlete_email email του αθλητή στον οποίο στέλνεται η πρόσκληση.
     */
    public Invitation(String athlete_email) {
        this.InvitationCode = UUID.randomUUID().toString().substring(0, 6);
        this.athlete_email = athlete_email;
        this.status = InvitationStatus.PENDING;
    }

    /** Αποδοχή της πρόσκλησης. */
    public void accept() {
        this.status = InvitationStatus.ACCEPTED;
    }

    /** Απόρριψη της πρόσκλησης. */
    public void reject() {
        this.status = InvitationStatus.REJECTED;
    }

    /**
     * Επιστρέφει την κατάσταση της πρόσκλησης.
     * 
     * @return κατάσταση πρόσκλησης.
     */

    public InvitationStatus getStatus() {
        return status;
    }

    /**
     * Επιστρέφει τον κωδικό της πρόσκλησης.
     * 
     * @return κωδικς πρόσκλησης.
     */
    public String getInvitationCode() {
        return InvitationCode;
    }

    /**
     * Επιστρέφει το mail του αθλητή.
     * 
     * @return mail αθλητή.
     */
    public String getAthlete_email() {
        return athlete_email;
    }
}
