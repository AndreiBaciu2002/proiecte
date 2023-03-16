namespace LaboratorCSharp.domain.validatori;

public class ValidationException : ApplicationException
{
    public ValidationException(string? message) : base(message)
    {
    }
}