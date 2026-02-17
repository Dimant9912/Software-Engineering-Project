package com.example.tracknfieldapp;

import static org.junit.Assert.*;
import com.example.tracknfieldapp.domain.Invitation;
import com.example.tracknfieldapp.domain.InvitationStatus;
import org.junit.Test;

/**
 * Κλάση δοκιμών για την Invitation.
 * Ελέγχει την παραγωγή κωδικών και τη  κατάσταση των προσκλήσεων.
 */
public class InvitationTest {

    /**
     * Έλεγχος της ορθής αρχικοποίησης μιας πρόσκλησης και της παραγωγής κωδικού 6 χαρακτήρων.
     */
    @Test
    public void testInvitationCreation() {
        Invitation invite = new Invitation("test@athlete.com");
        assertEquals("test@athlete.com", invite.getAthlete_email());
        assertEquals(InvitationStatus.PENDING, invite.getStatus());
        assertNotNull(invite.getInvitationCode());
        assertEquals(6, invite.getInvitationCode().length());
    }

    /**
     *  Έλεγχος της αλλαγής κατάστασης της πρόσκλησης σε ACCEPTED και REJECTED.
     */
    @Test
    public void testStatusChanges() {
        Invitation invite = new Invitation("test@athlete.com");
        
        invite.accept();
        assertEquals(InvitationStatus.ACCEPTED, invite.getStatus());
        
        invite = new Invitation("test2@athlete.com");
        invite.reject();
        assertEquals(InvitationStatus.REJECTED, invite.getStatus());
    }
}
