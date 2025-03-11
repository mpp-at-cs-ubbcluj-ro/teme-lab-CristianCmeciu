namespace ConcursInotCS.domain;

public class Participant
{
    private String nume;
    private int varsta;

    public Participant(string nume, int varsta)
    {
        this.nume = nume;
        this.varsta = varsta;
    }

    public string Nume
    {
        get => nume;
        set => nume = value ?? throw new ArgumentNullException(nameof(value));
    }

    public int Varsta
    {
        get => varsta;
        set => varsta = value;
    }
}