namespace LaboratorCSharp.domain;

public class JucatorActiv : Entity<int>
{
    public String idJucator { get; set; }
    public int idMeci { get; set; }
    public int nrPuncteInscrise { get; set; }
    public Tip tip { get; set; }
    public override string ToString()
    {
        return Id + " " + idJucator + " " + idMeci + " " + nrPuncteInscrise + " " + tip;
    }

    public JucatorActiv(int idJucatorActiv, String idJucator, int idMeci, int nrPuncteInscrise, Tip tip) : base(
        idJucatorActiv)
    {
        this.idJucator = idJucator;
        this.idMeci = idMeci;
        this.nrPuncteInscrise = nrPuncteInscrise;
        this.tip = tip;
    }
}