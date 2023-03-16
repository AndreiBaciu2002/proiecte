namespace LaboratorCSharp.domain;

public class Meci : Entity<int>
{
    public Nume Echipa1 { get; set; }
    public Nume Echipa2 { get; set; }
    public DateTime Date { get; set; }

    public override string ToString()
    {
        return Id + " " + Echipa1 + " " + Echipa2 + " " + Date;
    }

    public Meci(int id, Nume echipa1, Nume echipa2, DateTime dateTime) : base(id)
    {
        this.Echipa1 = echipa1;
        this.Echipa2 = echipa2;
        this.Date = dateTime;
    }
}