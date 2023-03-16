namespace LaboratorCSharp.domain;

public class Elev : Entity<string>
{
    public String Nume { get; set; }
    public Scoala Scoala { get; set; }

    public override string ToString()
    {
        return Id + " " + Nume + " " + Scoala;
    }

    public Elev(string id, string nume, Scoala scoala) : base(id)
    {
        this.Nume = nume;
        this.Scoala = scoala;
    }

    public Elev() : base()
    {
    }
}