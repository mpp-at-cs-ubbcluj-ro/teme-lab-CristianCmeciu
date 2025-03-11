namespace ConcursInotCS.domain;

public class Entity<ID>
{
    private ID id;
    public ID GetID() => id;
    public void SetID(ID id) => this.id = id;
}