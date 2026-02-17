public class Exercise {
    private int id;
    private int sets;
    private int reps;
    private ExerciseType type;

    public Exercise(int id, ExerciseType type, int sets, int reps) {
        this.id = id;
        this.type = type;
        this.sets = sets;
        this.reps = reps;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSets() {
        return sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    // Getter και Setter για reps
    public int getReps() {
        return reps;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public ExerciseType getType() {
        return type;
    }

}
