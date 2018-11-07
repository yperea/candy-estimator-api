package com.pkty.persistance;

import java.util.List;

public class AppDbContext<T> {
    private EntityDAO<T> entityDAO;

    /**
     * Instantiates a new App repository.
     *
     * @param entityType the entity type
     */
    public AppDbContext(Class<T> entityType) {

        entityDAO = new EntityDAO<>(entityType);
    }

    public List<T> getAll() {
        return entityDAO.getAll();
    }

    public List<T> getByPropertyEqual(String propertyName, String value) {
        return entityDAO.getByPropertyEqual(propertyName, value);
    }

    public List<T> getByPropertyLike(String propertyName, String value) {
        return entityDAO.getByPropertyLike(propertyName, value);
    }

    public T get(int id) {
        return entityDAO.getById(id);
    }

    /**
     * Creates an BusinessEntity in the database
     *
     * @param entity entity to be inserted or updated
     * @return the inserted record
     */
    /*@Override
    public int create(T entity) {

        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();

        return i;
    }*/
    public T create(T entity) {
        int i = entityDAO.insert(entity);
        entityDAO.saveChanges();
        return get(i);
    }

    public T createSerializable(T entity) {
        T newEntity = entityDAO.insertSerializable(entity);
        entityDAO.saveChanges();
        return newEntity;
    }

    public void update(T entity) {
        entityDAO.update(entity);
        entityDAO.saveChanges();
    }

    public void delete(T entity) {
        entityDAO.delete(entity);
        entityDAO.saveChanges();
    }
}
