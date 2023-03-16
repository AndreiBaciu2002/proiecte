using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class FileRepoEchipe : FileRepository<int, Echipa>
{
    public FileRepoEchipe(IValidator<Echipa> validator, string numeFisier) : base(validator, numeFisier, CitireEntitatiDinFisier.CitesteEchipa)
    {
    }
}