namespace LaboratorCSharp.domain.validatori;

public class ValidatorElev : IValidator<Elev>
{
    public void Valideaza(Elev elev)
    {
        if (elev.Nume == null || elev.Nume == "")
        {
            throw new ValidationException("Numele este vid");
        }
    }
}