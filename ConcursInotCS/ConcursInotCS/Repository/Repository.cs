using ConcursInotCS.domain;

namespace ConcursInotCS.Repository;

public interface Repository<ID,E> where E : Entity<ID>
{
    E findById(int id);
    IEnumerable<E> findAll();
    E save(E entity);
    E update(E entity);
    E delete(E entity);
}