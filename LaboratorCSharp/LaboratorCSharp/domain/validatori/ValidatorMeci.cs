namespace LaboratorCSharp.domain.validatori;

public class ValidatorMeci:IValidator<Meci>
{
    public void Valideaza(Meci meci)
    {
        if (meci.Echipa1 == null)
        {
            throw new ValidationException("numele echipei nu poate fi nul");
        }
    }
}