package com.example.tracknfieldapp.domain;

/**
 * Enumeration για την κατάσταση μιας πρόσκλησης.
 */
public enum InvitationStatus {
    /** Η πρόσκληση στάλθηκε, περιμένουμε τον αθλητή. */
    PENDING,
    /** Ο αθλητής την αποδέχτηκε. */
    ACCEPTED,
    /** Ο αθλητής την απέρριψε. */
    REJECTED,
    /** Η πρόσκληση έληξε. (δεν το χρησιμοποιησαμε τλκ) */
    EXPIRED

}
