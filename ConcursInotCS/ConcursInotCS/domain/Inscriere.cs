namespace ConcursInotCS.domain;

public class Inscriere
{
    private Participant idParticipant;
    private Proba idProba;

    public Inscriere(Participant idParticipant, Proba idProba)
    {
        this.idParticipant = idParticipant;
        this.idProba = idProba;
    }

    public Participant IdParticipant
    {
        get => idParticipant;
        set => idParticipant = value ?? throw new ArgumentNullException(nameof(value));
    }

    public Proba IdProba
    {
        get => idProba;
        set => idProba = value ?? throw new ArgumentNullException(nameof(value));
    }
}