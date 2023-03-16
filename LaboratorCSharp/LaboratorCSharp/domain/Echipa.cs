namespace LaboratorCSharp.domain;

public class Echipa : Entity<int>
{
    public Nume Nume { get; set; }

    public Echipa(int id, Nume numeEchipa) : base(id)
    {
        this.Nume = numeEchipa;
    }

    public override string ToString()
    {
        return Id + " " + Nume;
    }
}