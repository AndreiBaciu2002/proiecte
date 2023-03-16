using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class FileRepoJucatoriActivi : FileRepository<int, JucatorActiv>
{
    public FileRepoJucatoriActivi(IValidator<JucatorActiv> validator, string numeFisier) : base(validator, numeFisier,
        CitireEntitatiDinFisier.CitesteJucatorActiv)
    {
        
    }
}