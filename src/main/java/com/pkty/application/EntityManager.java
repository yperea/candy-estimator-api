package com.pkty.application;

import com.pkty.persistance.AppDbContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Entity manager.
 *
 * @param <T> the type parameter
 */
public class EntityManager<T> {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private AppDbContext<T> appDbContext;

    /**
     * Instantiates a new Entities manager.
     *
     * @param entityType the entity type
     */
    public EntityManager(Class<T> entityType) {
        appDbContext = new AppDbContext<>(entityType);
        logger.info("EntityManager(Class<T>): Instantiating EntityManager class.");
    }


    //public EntityManager(EntityManager entityType) {
    //}

    /**
     * Gets the list of all of the Entities.
     *
     * @return the list of entities
     */
    public List<T> getList(){
        List<T> entities = appDbContext.getAll();

        logger.debug("getList(): Returning list of Entities.");
        logger.trace("getList(): Returning " + entities);

        return entities;
    }

    /**
     * Gets the list of all of the Entities which property is equals to the value.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the list
     */
    public List<T> getListEquals(String propertyName, String value){
        List<T> entities = appDbContext.getByPropertyEqual(propertyName, value);

        logger.debug("getListEquals(): Returning list of Entities.");
        logger.trace("getListEquals(): Returning " + entities);

        return entities;
    }

    /**
     * Gets the list of all of the Entities which property is equals to the value.
     *
     * @param userId       the user id
     * @return the list
     */
    public List<T> getRolesByUserId(int userId){

        String user = String.valueOf(userId);
        List<T> entities = appDbContext.getByPropertyEqual("user.id", user);

        logger.debug("getRolesByUserId(): Returning list of Entities.");
        logger.trace("getRolesByUserId(): Returning " + entities);

        return entities;
    }

    /**
     * Gets the list of all of the Entities which property contains the value.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the list
     */
    public List<T> getListContains(String propertyName, String value){
        List<T> entities = appDbContext.getByPropertyLike(propertyName, value);

        logger.debug("getListContains(): Returning list of Entities.");
        logger.trace("getListContains(): Returning " + entities);

        return entities;
    }

    /**
     * Gets a specific entity by its id.
     *
     * @param id the id
     * @return the entity
     */
    public T get(int id) {
        T entity = appDbContext.get(id);

        logger.debug("get(int): Returning Entity.");
        logger.trace("get(int): Returning " + entity);

        return entity;
    }

    /**
     * Creates a new Entity.
     *
     * @param entity the entity to be created
     * @return the entity just created
     */
    public T create (T entity) {

        logger.debug("create(T): Creating Entity.");
        logger.trace("create(T): Creating " + entity);

        return appDbContext.create(entity);
    }

    /**
     * Creates a new Entity.
     *
     * @param entity the entity to be created
     * @return the entity just created
     */
    public T createSerializable (T entity) {

        logger.debug("create2(T): Creating Entity.");
        logger.trace("create2(T): Creating " + entity);

        return appDbContext.createSerializable(entity);
    }


    /**
     * Updates a specific Entity.
     *
     * @param entity the entity to be updated
     */
    public void update (T entity) {

        logger.debug("update(T): Updating Entity.");
        logger.trace("update(T): Updating " + entity);

        appDbContext.update(entity);
    }

    /**
     * Deletes a specific Entity.
     *
     * @param entity the entity to be deleted
     */
    public void delete (T entity) {

        logger.debug("delete(T): Deleting Entity.");
        logger.trace("delete(T): Deleting " + entity);

        appDbContext.delete(entity);
    }

}
