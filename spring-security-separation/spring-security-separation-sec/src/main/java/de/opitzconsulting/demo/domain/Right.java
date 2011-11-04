package de.opitzconsulting.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;



/**
 * Domain object holding rights
 * 
 * @author staehler_m1
 */
@Entity
public class Right {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Version
    private Long version;

    protected Right() {

    }

    public Right(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

}
