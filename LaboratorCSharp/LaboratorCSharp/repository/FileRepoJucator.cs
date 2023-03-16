using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class FileRepoJucator : FileRepository<string, Jucator>
{
    public FileRepoJucator(IValidator<Jucator> validator, string numeFisier) : base(validator, numeFisier,
        CitireEntitatiDinFisier.CitesteJucator)
    {
    }
}