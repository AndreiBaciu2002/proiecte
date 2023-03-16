using System.Globalization;
using LaboratorCSharp.domain;
using LaboratorCSharp.service;

namespace LaboratorCSharp.UI;

public class UserInterface
{
    private Service service;

    public UserInterface(Service service1)
    {
        this.service = service1;
    }

    public void showMenu()
    {
        Console.WriteLine("1. Afisati elevi. ");
        Console.WriteLine("2. Afisati echipe. ");
        Console.WriteLine("3. Afisati meciuri. ");
        Console.WriteLine("4. Afisati jucatori. ");
        Console.WriteLine("5. Afisati jucatori activi. ");
        Console.WriteLine("6. Afisati jucatorii unei echipe. ");
        Console.WriteLine("7. Afisati jucatorii activi ai unei echipe de la un anumit meci. ");
        Console.WriteLine("8. Afisati meciurile dintr-o periodata calendaristica. ");
        Console.WriteLine("9. Afisati scorul de la un anumit meci. ");
        Console.WriteLine("0. Exit. ");
    }

    public void runMenu()
    {
        bool stop = false;
        while (!stop)
            try
            {
                {
                    showMenu();
                    Console.WriteLine("introduceti o optiune: ");
                    int optiune = int.Parse(Console.ReadLine());
                    switch (optiune)
                    {
                        case 1:
                        {
                            handle_afiseaza_elevi();
                            break;
                        }
                        case 2:
                        {
                            handle_afiseaza_echipe();
                            break;
                        }
                        case 3:
                        {
                            handle_afiseaza_meciuri();
                            break;
                        }
                        case 4:
                        {
                            handle_afiseaza_jucatori();
                            break;
                        }
                        case 5:
                        {
                            handle_afiseaza_jucatori_activi();
                            break;
                        }
                        case 6:
                        {
                            handle_afiseaza_jucatori_echipa();
                            break;
                        }
                        case 7:
                        {
                            handle_afiseaza_jucatori_activi_de_la_meci();
                            break;
                        }
                        case 8:
                        {
                            handle_afiseaza_meciuri_din_perioada();
                            break;
                        }
                        case 9:
                        {
                            handle_afiseaza_scor_meci_dat();
                            break;
                        }
                        case 0:
                        {
                            stop = true;
                            break;
                        }
                        default:
                        {
                            Console.WriteLine("Optiune invalida.Reincercati!");
                            break;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
    }

    private void handle_afiseaza_scor_meci_dat()
    {
        Console.WriteLine("dati nume echipa1: ");
        Nume nume1 = (Nume)Enum.Parse(typeof(Nume), Console.ReadLine());
        Console.WriteLine("dati nume echipa2: ");
        Nume nume2 = (Nume)Enum.Parse(typeof(Nume), Console.ReadLine());
        Console.WriteLine($"{nume1}:{service.afiseazaScorMeciDat1(nume1, nume2)[0]}");
        Console.WriteLine($"{nume2}:{service.afiseazaScorMeciDat1(nume1, nume2)[1]}");
    }

    private void handle_afiseaza_meciuri_din_perioada()
    {
        Console.WriteLine("Introduceti data in format dd/MM/yyyy: ");
        string Date1String = Console.ReadLine();
        string DateFormat = "dd/MM/yyyy";
        DateTime dt = DateTime.ParseExact(Date1String, DateFormat, CultureInfo.InvariantCulture);
        Console.WriteLine("Introduceti data in format dd/MM/yyyy: ");
        string Date2String = Console.ReadLine();
        DateTime dt2 = DateTime.ParseExact(Date2String, DateFormat, CultureInfo.InvariantCulture);
        foreach (var meci in service.afiseazaMeciuriDate1(dt, dt2))
        {
            Console.WriteLine(meci);
        }
    }

    private void handle_afiseaza_jucatori_activi_de_la_meci()
    {
        Console.WriteLine("dati id meci: ");
        int id = int.Parse(Console.ReadLine());
        Console.WriteLine("dati nume echipa: ");
        Nume nume = (Nume)Enum.Parse(typeof(Nume), Console.ReadLine());
        foreach (var jucatorActiv in service.afiseazaJucatoriActiviDeLaMeci1(id, nume))
        {
            Console.WriteLine(jucatorActiv);
        }
    }

    private void handle_afiseaza_jucatori_echipa()
    {
        Console.WriteLine("dati nume echipa: ");
        Nume nume = (Nume)Enum.Parse(typeof(Nume), Console.ReadLine());
        foreach (var jucator in service.afiseazaJucatoriEchipe1(nume))
        {
            Console.WriteLine(jucator);
        }
    }

    private void handle_afiseaza_jucatori_activi()
    {
        foreach (var jucatorActiv in service.getAllJucatoriActivi())
        {
            Console.WriteLine(jucatorActiv);
        }
    }

    private void handle_afiseaza_jucatori()
    {
        foreach (var jucator in service.getAllJucatori())
        {
            Console.WriteLine(jucator);
        }
    }

    private void handle_afiseaza_meciuri()
    {
        foreach (var meci in service.getAllMeciuri())
        {
            Console.WriteLine(meci);
        }
    }

    private void handle_afiseaza_echipe()
    {
        foreach (var echipa in service.getAllEchipe())
        {
            Console.WriteLine(echipa);
        }
    }

    private void handle_afiseaza_elevi()
    {
        foreach (var elev in service.getAllElevi())
        {
            Console.WriteLine(elev);
        }
    }
}