package com.learn.project.common.core.domain;

import java.io.Serializable;

/**
 * Entity interface
 *
 * @author haoxin
 * @date 2021-02-01
 **/
public interface Entity<T extends Entity> extends Serializable {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    default boolean sameIdentityAs(T other){
        return other != null && this.getUniqueLabel().sameValueAs(other.getUniqueLabel());
    }


    EntityId getUniqueLabel();

}
