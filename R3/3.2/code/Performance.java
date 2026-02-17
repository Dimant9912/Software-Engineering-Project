import java.time.LocalDate;

public class Performance {
    private int exerciseId;
    private LocalDate executionDate;

    private  String competition;
    private String result;

    public Performance(int exerciseId,LocalDate executionDate,String competition,String result)
    {
        this.exerciseId=exerciseId;
        this.executionDate=executionDate;
        this.competition=competition;
        this.result=result;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    public String getCompetition() {
        return competition;
    }

    public String getResult() {
        return result;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public void setExecutionDate(LocalDate executionDate) {
        this.executionDate = executionDate;
    }

    public void setResult(String result) {
        this.result = result;
    }

}



