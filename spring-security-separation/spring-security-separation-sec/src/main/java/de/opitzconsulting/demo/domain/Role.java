package de.opitzconsulting.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * represents a role for a given user
 * 
 * @author staehler_m1
 */
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Version
    private Long version;


    protected Role() {

    }

    /**
     * creates a role by given name
     * 
     * @param name
     *            role name
     */
    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

}
