
using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;
using LaboratorCSharp.repository;
using LaboratorCSharp.service;
using LaboratorCSharp.UI;

public class Program
{
    public static void Main()
    {
        FileRepository<string, Elev> fileRepoElev = new FileRepoElev(new ValidatorElev(),
            "C:/Users/Andrei Baciu/RiderProjects/LaboratorCSharp/LaboratorCSharp/Data/elevi.txt");
        FileRepository<int, Echipa> fileRepoEchipa = new FileRepoEchipe(new ValidatorEchipa(),
            "C:/Users/Andrei Baciu/RiderProjects/LaboratorCSharp/LaboratorCSharp/Data/echipe.txt");
        FileRepository<int, Meci> fileRepoMeci = new FileRepoMeci(new ValidatorMeci(),
            "C:/Users/Andrei Baciu/RiderProjects/LaboratorCSharp/LaboratorCSharp/Data/meciuri.txt");
        FileRepository<String, Jucator> fileRepoJucator = new FileRepoJucator(new ValidatorJucator(),
            "C:/Users/Andrei Baciu/RiderProjects/LaboratorCSharp/LaboratorCSharp/Data/jucatori.txt");
        FileRepository<int, JucatorActiv> fileRepoJucatorActiv = new FileRepoJucatoriActivi(new ValidatorJucatorActiv(),
            "C:/Users/Andrei Baciu/RiderProjects/LaboratorCSharp/LaboratorCSharp/Data/jucatoriactivi.txt");
        Service service = new Service(fileRepoElev, fileRepoEchipa, fileRepoMeci, fileRepoJucator,
            fileRepoJucatorActiv);
        UserInterface userInterface = new UserInterface(service);
        userInterface.runMenu();
    }
}