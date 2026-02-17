import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ScheduledTraining {
    private int sessionId;
    private LocalDate sessionDate;
    private boolean isCompleted;

    private List<Exercise> scheduledExercises;

    public ScheduledTraining(int sessionId, LocalDate sessionDate) {
        this.sessionId = sessionId;
        this.sessionDate = sessionDate;
        this.scheduledExercises = new ArrayList<>();
        this.isCompleted = false;
    }


    public int getSessionId() {
        return sessionId;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public List<Exercise> getScheduledExercises() {
        return scheduledExercises;
    }


    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public void setScheduledExercises(List<Exercise> scheduledExercises) {
        this.scheduledExercises = scheduledExercises;
    }


    public void addExercise(Exercise exercise) {
        this.scheduledExercises.add(exercise);
    }

    public boolean isCompleted() {
         return isCompleted;
    }

    public void setCompleted(boolean completed) {
         isCompleted = completed;
    }

}