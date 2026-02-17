package com.example.tracknfieldapp.dao;

/**
 * Factory κλάση (Singleton) για τη δημιουργία και χρήση των DAO αντικειμένων.
 */
public class DAOFactory {
    private static DAOFactory instance;
    private AthleteDAO athleteDAO;
    private CoachDAO coachDAO;
    private ExerciseTypeDAO exerciseTypeDAO;
    private InvitationDAO invitationDAO;
    private ScheduledTrainingDAO scheduledTrainingDAO;

    /**
     * Ιδιωτικός constructor
     * Αρχικοποιεί τις υλοποιήσεις των DAO στη μνήμη.
     */
    private DAOFactory() {
        athleteDAO = new AthleteMemoryDAO();
        coachDAO = new CoachMemoryDAO();
        invitationDAO = new InvitationMemoryDAO();
        scheduledTrainingDAO = new ScheduledTrainingMemoryDAO();
        exerciseTypeDAO = new ExerciseTypeMemoryDAO();
    }

    /**
     * Επιστρέφει το μοναδικό στιγμιότυπο του DAOFactory.
     * 
     * @return το μοναδικό στιγμυότυπο του DAOFactory.
     */
    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * Επιστρέφει το DAO για τους αθλητές.
     * 
     * @return το DAO για τους αθλητές.
     */
    public AthleteDAO getAthleteDAO() {
        return athleteDAO;
    }

    /**
     * ΕπιστρέφειΤο DAO για τους προπονητές.
     * 
     * @return το DAO για τους προπονητές.
     */
    public CoachDAO getCoachDAO() {
        return coachDAO;
    }

    /**
     * Επιστρέφει Το DAO για τις προσκλήσεις.
     * 
     * @return το DAO για τις προσκλήσεις.
     */
    public InvitationDAO getInvitationDAO() {
        return invitationDAO;
    }

    /**
     * Επιστρέφει Το DAO για τις προγραμματισμένες προπονήσεις.
     * 
     * @return το DAO για τις προγραμματισμένες προπονήσεις.
     */
    public ScheduledTrainingDAO getScheduledTrainingDAO() {
        return scheduledTrainingDAO;
    }

    /**
     * Επιστρέφει Το DAO για τους τύπους ασκήσεων.
     * 
     * @return το DAO για τους τύπους ασκήσεων.
     */
    public ExerciseTypeDAO getExerciseTypeDAO() {
        return exerciseTypeDAO;
    }
}
