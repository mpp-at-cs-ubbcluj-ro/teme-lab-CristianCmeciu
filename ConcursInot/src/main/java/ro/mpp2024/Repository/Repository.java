package ro.mpp2024.Repository;

import ro.mpp2024.domain.Entity;

public interface Repository <ID, E extends Entity<ID>> {
    E findById(ID id);
    Iterable<E> findAll();
    E save(E entity);
    E update(E entity);
    E delete(ID id);
}
