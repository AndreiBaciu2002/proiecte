using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class FileRepoMeci : FileRepository<int, Meci>
{
    public FileRepoMeci(IValidator<Meci> validator, string numeFisier) : base(validator, numeFisier, CitireEntitatiDinFisier.CitesteMeci)
    {
    }
}