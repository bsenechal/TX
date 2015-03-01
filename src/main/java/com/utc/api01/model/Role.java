
package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

}
