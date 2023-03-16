using LaboratorCSharp.domain;
using LaboratorCSharp.repository;

namespace LaboratorCSharp.service;

public class Service
{
    private FileRepository<string, Elev> repoElevi;
    private FileRepository<int, Echipa> repoEchipe;
    private FileRepository<int, Meci> repoMeciuri;
    private FileRepository<string, Jucator> repoJucatori;
    private FileRepository<int, JucatorActiv> repoJucatoriActivi;

    public Service(FileRepository<string, Elev> repoElevi1, FileRepository<int, Echipa> repoEchipe1,
        FileRepository<int, Meci> repoMeciuri1, FileRepository<string, Jucator> repoJucatori1,
        FileRepository<int, JucatorActiv> repoJucatoriActivi1)
    {
        this.repoElevi = repoElevi1;
        this.repoEchipe = repoEchipe1;
        this.repoMeciuri = repoMeciuri1;
        this.repoJucatori = repoJucatori1;
        this.repoJucatoriActivi = repoJucatoriActivi1;
    }

    public List<Jucator> afiseazaJucatoriEchipe(Nume numeEchipa)
    {
        List<Jucator> jucatori = new List<Jucator>();
        foreach (var jucator in repoJucatori.FindAll())
        {
            if (jucator.Echipa == numeEchipa)
            {
                jucatori.Add(jucator);
            }
        }

        return jucatori;
    }

    public List<Meci> afiseazaMeciuriDate(DateTime dateTime1, DateTime dateTime2)
    {
        List<Meci> meciuri = new List<Meci>();
        foreach (var VARIABLE in repoMeciuri.FindAll())
        {
            if ((VARIABLE.Date < dateTime2) && (VARIABLE.Date > dateTime1))
            {
                meciuri.Add(VARIABLE);
            }
        }

        return meciuri;
    }

    public List<JucatorActiv> afiseazaJucatoriActiviDeLaMeci(int idMeci, Nume numeEchipa)
    {
        List<JucatorActiv> lista = new List<JucatorActiv>();
        foreach (var jucatorActiv in repoJucatoriActivi.FindAll())
        {
            if (jucatorActiv.idMeci == idMeci)
            {
                foreach (var jucator in repoJucatori.FindAll())
                {
                    if (jucatorActiv.idJucator == jucator.Id && (jucator.Echipa == numeEchipa))
                    {
                        lista.Add(jucatorActiv);
                    }
                }
            }
        }

        return lista;
    }

    public List<int> afiseazaScorMeciDat(Nume echipa1, Nume echipa2)
    {
        List<int> lista = new List<int>();
        int scor1 = 0;
        int scor2 = 0;
        foreach (var jucatorActiv in repoJucatoriActivi.FindAll())
        {
            Jucator jucator1 = repoJucatori.FindOne(jucatorActiv.idJucator);
            if (jucator1.Echipa == echipa1)
            {
                scor1 += jucatorActiv.nrPuncteInscrise;
            }

            Jucator jucator2 = repoJucatori.FindOne(jucatorActiv.idJucator);
            if (jucator2.Echipa == echipa2)
            {
                scor2 += jucatorActiv.nrPuncteInscrise;
            }
        }

        lista.Add(scor1);
        lista.Add(scor2);
        return lista;
    }

    public List<Jucator> afiseazaJucatoriEchipe1(Nume numeEchipa)
    {
        List<Jucator> listaJucatori = repoJucatori.FindAll().ToList();
        IEnumerable<Jucator> jucatorii = listaJucatori.Where(jucator1 => jucator1.Echipa == numeEchipa);
        return jucatorii.ToList();
    }

    public List<JucatorActiv> afiseazaJucatoriActiviDeLaMeci1(int idMeci, Nume numeEchipa)
    {
        List<JucatorActiv> jucatoriActivi = repoJucatoriActivi.FindAll().ToList();
        var jucatori = from k in jucatoriActivi
            where k.idMeci == idMeci
            from j1 in repoJucatori.FindAll().ToList()
            where (j1.Id == k.idJucator && j1.Echipa == numeEchipa)
            select k;
        return jucatori.ToList();
    }

    public List<Meci> afiseazaMeciuriDate1(DateTime dateTime1, DateTime dateTime2)
    {
        List<Meci> listaMeciuri = repoMeciuri.FindAll().ToList();
        var meci = listaMeciuri.Where(meci1 => meci1.Date < dateTime2 && meci1.Date > dateTime1);
        return meci.ToList();
    }

    public List<int> afiseazaScorMeciDat1(Nume echipa1, Nume echipa2)
    {
        List<int> lista = new List<int>();
        var scor1 = from k in repoJucatori.FindAll().ToList()
            where k.Echipa == echipa1
            from j in repoJucatoriActivi.FindAll().ToList()
            where j.idJucator == k.Id
            select j.nrPuncteInscrise;
        var scor2 = from k1 in repoJucatori.FindAll().ToList()
            where k1.Echipa == echipa2
            from j1 in repoJucatoriActivi.FindAll().ToList()
            where j1.idJucator == k1.Id
            select j1.nrPuncteInscrise;
        lista.Add(scor1.Sum());
        lista.Add(scor2.Sum());
        return lista;
    }

    public List<Elev> getAllElevi()
    {
        return repoElevi.FindAll().ToList();
    }

    public List<Echipa> getAllEchipe()
    {
        return repoEchipe.FindAll().ToList();
    }

    public List<Meci> getAllMeciuri()
    {
        return repoMeciuri.FindAll().ToList();
    }

    public List<Jucator> getAllJucatori()
    {
        return repoJucatori.FindAll().ToList();
    }

    public List<JucatorActiv> getAllJucatoriActivi()
    {
        return repoJucatoriActivi.FindAll().ToList();
    }
}