package com.example.tracknfieldapp.domain;

import java.time.LocalDate;

/**
 * Η abstract κλάση User αποτελεί την βασική κλάση για όλους τους χρήστες του
 * συστήματος (Αθλητές και Προπονητές).
 * Παρέχει τα κοινά στοιχεία όπως email, κωδικό πρόσβασης και προσωπικά
 * στοιχεία.
 */
public abstract class User {
    private int id;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private LocalDate birthDate;

    /**
     * Κατασκευαστήςωτης κλάσης.
     * 
     * @param id        "ταυτότητα" του χρήστη.
     * @param email     email του χρήστη.
     * @param password  κωδικός πρόσβασης.
     * @param firstname όνομα.
     * @param lastname  επώνυμο.
     * @param birthDate ημερομηνία γέννησης.
     */
    public User(int id, String email, String password, String firstname, String lastname, LocalDate birthDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.first_name = firstname;
        this.last_name = lastname;
        this.birthDate = birthDate;
    }

    /**
     * Επιστρέφει το ID του χρήστη.
     * 
     * @return το ID του χρήστη.
     */
    public int getId() {
        return id;
    }

    /** @param id καινούρια "ταυτότητα" προς ανάθεση */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Επιστρέφει το email.
     * 
     * @return to email.
     */
    public String getEmail() {
        return email;
    }

    /** @param email email χρήστη */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Επιστρέφει τον κωδικό πρόσβασης.
     * 
     * @return τον κωδικό πρόσβασης.
     */
    public String getPassword() {
        return password;
    }

    /** @param password κωδικός πρόσβασης */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Επιστρέφει το όνομα χρήστη.
     * 
     * @return όνομα χρήστη.
     */
    public String getFirst_name() {
        return first_name;
    }

    /** @param first_name όνομα χρήστη */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Επιστρέφει το επώνυμο του χρήστη.
     * 
     * @return επώνυμο χρήστη.
     */
    public String getLast_name() {
        return last_name;
    }

    /** @param last_name επώνυμο χρήστη. */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Επιστρέφει την ημερομηνία γέννησης.
     * 
     * @return την ημερομηνία γέννησης
     */

    public LocalDate getBirthDate() {
        return birthDate;
    }

    /** @param birthDate ημερομηνία γέννησης χρήστη */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Ελέγχει αν ο κωδικός πρόσβασης είναι σωστός.και επιστρέφει true,αλλιώς false.
     * 
     * @param passwordCandidate κωδικός προς έλεγχος.
     * @return true αν ο κωδικός είναι σωστός, αλλιώς false.
     */
    public boolean login(String passwordCandidate) {
        return this.password != null && this.password.equals(passwordCandidate);
    }
}
