using LaboratorCSharp.domain;
using LaboratorCSharp.domain.validatori;

namespace LaboratorCSharp.repository;

public class InMemoryRepository<ID, E> : IRepository<ID, E> where E : Entity<ID>
{
    protected IDictionary<ID, E> entities=new Dictionary<ID, E>();
    protected IValidator<E> vali;


    public InMemoryRepository(IValidator<E> validator)
    {
        vali = validator;
    }

    public E FindOne(ID id)
    {
        if (entities.ContainsKey(id))
        {
            return entities[id];
        }

        return null;
    }

    public IEnumerable<E> FindAll()
    {
        return entities.Values.ToList<E>();
    }

    public E Save(E e)
    {
        this.vali.Valideaza(e);
        if (entities.ContainsKey(e.Id))
        {
            return e;
        }

        entities.Add(e.Id, e);
        return e;
    }

    public E Delete(ID id)
    {
        if (entities.ContainsKey(id))
        {
            entities.Remove(id);
        }

        return FindOne(id);
    }

    public E Update(E e)
    {
        throw new NotImplementedException();
    }
}