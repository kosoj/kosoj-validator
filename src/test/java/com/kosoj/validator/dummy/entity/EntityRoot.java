package com.kosoj.validator.dummy.entity;

/**
 * User: machain
 * Date: 15.7.13
 * Time: 11:21
 */
public class EntityRoot {

    private Long id;


    // ACCESSORS
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }


    // OVERRIDEN
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final EntityRoot that = (EntityRoot) o;

        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EntityRoot{" +
                "id=" + id +
                '}';
    }

}
