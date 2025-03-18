package ro.mpp2024.Repository;

import ro.mpp2024.domain.Entity;

public interface Repository <ID, E extends Entity<ID>> {
    E findById(ID id);
    Iterable<E> findAll();
    void save(E entity);
    void update(E entity);
    void delete(ID id);
}
