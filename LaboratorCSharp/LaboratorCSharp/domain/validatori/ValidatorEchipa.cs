namespace LaboratorCSharp.domain.validatori;

public class ValidatorEchipa : IValidator<Echipa>
{
    public void Valideaza(Echipa echipa)
    {
        if (echipa.Nume == null)
        {
            throw new ValidationException("numele echipei nu poate fi nul");
        }
    }
}