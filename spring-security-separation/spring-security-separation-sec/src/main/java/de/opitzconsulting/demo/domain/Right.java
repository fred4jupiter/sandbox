package de.opitzconsulting.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;



/**
 * Domain object holding rights
 * 
 * @author staehler_m1
 */
@Entity
@Table(name = "SEC_RIGHT")
public class Right {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Version
    private Long version;

    /**
     * default constructor for hibernate
     */
    @SuppressWarnings("unused")
    private Right() {

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
