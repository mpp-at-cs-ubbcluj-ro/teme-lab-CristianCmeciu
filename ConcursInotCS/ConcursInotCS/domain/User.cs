namespace ConcursInotCS.domain;

public class User : Entity<long>
{
    private string username;
    private string password;

    public User(string username, string password)
    {
        this.username = username;
        this.password = password;
    }

    public string Username1
    {
        get => username;
        set => username = value ?? throw new ArgumentNullException(nameof(value));
    }

    public string Password1
    {
        get => password;
        set => password = value ?? throw new ArgumentNullException(nameof(value));
    }
}