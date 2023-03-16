namespace LaboratorCSharp.domain;

public class CitireEntitatiDinFisier
{
    private static char Separator = ';';

    public static Elev CitesteElev(string line)
    {
        string[] splitElev = line.Split(Separator);
        Elev elev = new Elev(splitElev[0], splitElev[1], (Scoala)Enum.Parse(typeof(Scoala), splitElev[2]));
        return elev;
    }
    public static Meci CitesteMeci(string line)
    {
        string[] splitMeci = line.Split(Separator);
        Meci meci = new Meci(int.Parse(splitMeci[0]), (Nume)Enum.Parse(typeof(Nume), splitMeci[1]),
            (Nume)Enum.Parse(typeof(Nume), splitMeci[2]), DateTime.Parse(splitMeci[3]));
        return meci;
    }
    public static Echipa CitesteEchipa(string line)
    {
        string[] splitEchipa = line.Split(Separator);
        Echipa echipa = new Echipa(int.Parse(splitEchipa[0]), (Nume)Enum.Parse(typeof(Nume), splitEchipa[1]));
        return echipa;
    }
    
    public static Jucator CitesteJucator(string line)
    {
        string[] splitJucator = line.Split(Separator);
        Jucator jucator = new Jucator(splitJucator[0], (Nume)Enum.Parse(typeof(Nume), splitJucator[1]), splitJucator[2],
            (splitJucator[3]), (Scoala)Enum.Parse(typeof(Scoala), splitJucator[4]));
        return jucator;
    }
    public static JucatorActiv CitesteJucatorActiv(string line)
    {
        string[] splitJucatorActiv = line.Split(Separator);
        JucatorActiv jucatorActiv = new JucatorActiv(int.Parse(splitJucatorActiv[0]), (splitJucatorActiv[1]),
            int.Parse(splitJucatorActiv[2]), int.Parse(splitJucatorActiv[3]), (Tip)Enum
                .Parse(typeof(Tip), splitJucatorActiv[4]));
        return jucatorActiv;
    }
}