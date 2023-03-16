namespace LaboratorCSharp.domain;

public class Jucator : Elev
{
    public Nume Echipa { get; set; }

    public Jucator(String id, Nume echipa, String idElev, String nume, Scoala scoala) : base(idElev, nume, scoala)
    {
        this.Echipa = echipa;
    }

    public override string ToString()
    {
        return Id + " " + Echipa + " " + Id + " " + Nume + " " + Scoala;
    }
}