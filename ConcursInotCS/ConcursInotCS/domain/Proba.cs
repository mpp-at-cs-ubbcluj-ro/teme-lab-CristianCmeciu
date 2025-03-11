namespace ConcursInotCS.domain;

public class Proba
{
    private int distanta;
    private string stil;

    public Proba(int distanta, string stil)
    {
        this.distanta = distanta;
        this.stil = stil;
    }

    public int Distanta
    {
        get => distanta;
        set => distanta = value;
    }

    public string Stil
    {
        get => stil;
        set => stil = value ?? throw new ArgumentNullException(nameof(value));
    }
}