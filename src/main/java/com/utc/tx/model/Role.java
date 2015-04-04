package com.utc.tx.model;

import java.io.Serializable;
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
public class Role implements Serializable{

	private static final long serialVersionUID = -2891517194796346552L;

	@Id
    @GenericGenerator(name = "id_role", strategy = "INCREMENT")
    private int idRole;

    @Column(name = "label")
    private String label;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<User> userSet = new HashSet<User>();

	/**
	 * @return the idRole
	 */
	public int getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
