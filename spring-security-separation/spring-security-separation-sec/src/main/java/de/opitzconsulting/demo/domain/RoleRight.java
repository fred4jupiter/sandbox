package de.opitzconsulting.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class RoleRight {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Right right;

    protected RoleRight() {
       
    }
    
    public RoleRight(Role role, Right right) {
        this.role = role;
        this.right = right;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }
}
