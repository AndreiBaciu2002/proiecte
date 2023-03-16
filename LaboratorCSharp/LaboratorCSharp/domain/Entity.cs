namespace LaboratorCSharp.domain;

public class Entity<TId>
{
    public TId Id { get; set; }
    
    public Entity(TId id)
    {
        Id = id;
    }
    public Entity(){}
}