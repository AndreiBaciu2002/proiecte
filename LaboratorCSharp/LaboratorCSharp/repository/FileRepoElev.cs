using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class FileRepoElev : FileRepository<string, Elev>
{
    public FileRepoElev(IValidator<Elev> validator, string numeFisier) : base(validator, numeFisier, CitireEntitatiDinFisier.CitesteElev)
    {
    }
}