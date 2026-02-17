public class Invitation {
    private String InvitationCode;
    private String trainer_email;
    public Invitation(String InvitationCode,String trainer_email)
    {
        this.InvitationCode=InvitationCode;
        this.trainer_email=trainer_email;
    }

    public String getInvitationCode() {
        return InvitationCode;
    }



    public String getTrainer_email() {
        return trainer_email;
    }
}
