namespace LaboratorCSharp.domain.validatori;

public interface IValidator<E>
{
    void Valideaza(E entitate);
    
}