package com.pkty.shared;

import com.pkty.application.EntityManager;

/**
 * Factory for the Entity Managers of Application
 *
 * @param <T> the type parameter
 */
public class ManagerFactory<T> {

    /**
     * Gets manager.
     *
     * @param entityType the entity type
     * @return the manager
     */
    public static EntityManager getManager(Class entityType) {
        return new EntityManager(entityType);
    }
}
