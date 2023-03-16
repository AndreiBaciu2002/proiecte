namespace LaboratorCSharp.domain.validatori;

public class ValidatorJucator:IValidator<Jucator>
{
    public void Valideaza(Jucator entitate)
    {
        if (entitate.Scoala == null || entitate.Nume == null || entitate.Echipa == null)
        {
            throw new ValidationException("numele/scoala/echipa unui elev nu pot fi nule");
        }
    }
}