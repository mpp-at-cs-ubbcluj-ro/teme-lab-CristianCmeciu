package ro.mpp2024.domain;

public class Inscriere extends Entity<Integer>{
    private Participant idParticipant;
    private Proba idProba;

    public Inscriere(Participant idParticipant, Proba idProba) {
        this.idParticipant = idParticipant;
        this.idProba = idProba;
    }

    public Participant getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(Participant idParticipant) {
        this.idParticipant = idParticipant;
    }

    public Proba getIdProba() {
        return idProba;
    }

    public void setIdProba(Proba idProba) {
        this.idProba = idProba;
    }
}
