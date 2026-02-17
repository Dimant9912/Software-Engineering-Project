import java.util.ArrayList;
import java.util.List;

public class Athlete extends  User{

    private Invitation invite;
    private List<TrainingProgram> programs;
    private List<CompletedExercise> completedExercises;


    public Athlete(int id, String email, String password, String firstname, String lastname, Invitation invite) {
        super(id, email, password, firstname, lastname);
        this.invite = invite;
        this.programs = new ArrayList<>();
        this.completedExercises = new ArrayList<>();
    }



    public Invitation getInvite() {
        return invite;
    }

    public List<TrainingProgram> getPrograms() {
        return programs;
    }

    public void addTrainingProgram(TrainingProgram program) {
        this.programs.add(program);
    }

    public List<CompletedExercise> getCompletedExercises() {
        return completedExercises;
    }

    public void addCompletedExercise(CompletedExercise exercise) {
        this.completedExercises.add(exercise);
    }
}
