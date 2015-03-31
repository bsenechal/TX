package com.utc.tx.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GenericGenerator(name = "IDROLE", strategy = "INCREMENT")
    private int idRole;

    @Column(name = "LIBELLE")
    private String libelle;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> userSet = new HashSet<User>();

    /**
     * @return the idRole
     */
    public int getIdRole() {
        return idRole;
    }

    /**
     * @param idRole
     *            the idRole to set
     */
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle
     *            the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

	/**
	 * @return the userSet
	 */
	public Set<User> getUserSet() {
		return userSet;
	}

	/**
	 * @param userSet the userSet to set
	 */
	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

}
