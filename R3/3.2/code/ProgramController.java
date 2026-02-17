import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProgramController {

    public ProgramController() {
    }

    public TrainingProgram createProgram(int programId, String name, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            return null;
        }
        return new TrainingProgram(programId, name, start, end);
    }

    public void addSessionToProgram(TrainingProgram program, ScheduledTraining session) {
        if (program != null && session != null) {
            program.addSession(session);
        }
    }

    public void addExerciseToSession(ScheduledTraining session, int exerciseId, int targetSets, int targetReps) {
        // Εδώ υποθέτουμε ότι το ScheduledTraining έχει λίστα ασκήσεων.
        // Για την ώρα, απλά ελέγχουμε αν το session είναι έγκυρο.
        if (session == null) {
            System.out.println("Error: Session is null.");
        }
    }
}