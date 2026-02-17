

public class CompletedExercise {

    private int exerciseId;
    private int completedReps;
    private int completedSets;
    private String comments;


    public CompletedExercise(int exerciseId, int completedReps,int completedSets, String comments) {
        this.exerciseId = exerciseId;
        this.comments = comments;
        this.completedReps=completedReps;
        this.completedSets=completedSets;

    }


    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getCompletedReps() {
        return completedReps;
    }

    public int getCompletedSets() {
        return completedSets;
    }

    public void setCompletedReps(int completedReps) {
        this.completedReps = completedReps;
    }

    public void setCompletedSets(int completedSets) {
        this.completedSets = completedSets;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getDetails() {

        System.out.println(getComments());
        return "Completed sets :  " + getCompletedSets() + " ,completed reps :  " + completedReps;
    }
}
