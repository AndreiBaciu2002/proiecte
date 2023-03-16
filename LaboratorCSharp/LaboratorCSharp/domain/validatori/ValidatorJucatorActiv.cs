namespace LaboratorCSharp.domain.validatori;

public class ValidatorJucatorActiv : IValidator<JucatorActiv>
{
    public void Valideaza(JucatorActiv jucatorActiv)
    {
        if (jucatorActiv.tip == null)
        {
            throw new ValidationException("Tipul este vid");
        }
    }
}