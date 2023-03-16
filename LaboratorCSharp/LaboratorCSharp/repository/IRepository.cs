using LaboratorCSharp.domain;

namespace LaboratorCSharp.repository;

public interface IRepository<ID, E> where E : Entity<ID>
{
    E FindOne(ID id);

    IEnumerable<E> FindAll();

    E Save(E e);

    E Delete(ID id);

    E Update(E e);
}