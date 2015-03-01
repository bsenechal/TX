package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "USER")
public class User {
    @Id
    @GenericGenerator(name = "IDUSER", strategy = "INCREMENT")
    private int idUser;

    @Column(name = "PASSWORD")
    @Size(min = 6, max = 15)
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private boolean enabled;

    @Column(name = "FIRSTNAME")
    @NotEmpty
    private String firstname;

    @Column(name = "LASTNAME")
    @NotEmpty
    private String lastname;

    @Column(name = "EMAIL")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "TELEPHONE")
    @NotNull
    @Min(10)
    private Integer telephone;

    @Column(name = "CREATION_DATE")
    private String creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FKROLE")
    private Role role;
    
    @Column(name = "avatar", columnDefinition="blob", length = 16777215)
    @Lob()
    private byte[] avatar;

    @Transient
    @NotNull
    private int roleUser;

    @Transient
    @NotEmpty
    @Size(min = 6, max = 15)
    private String confirmation;
  
    /**
     * @return the roleUser
     */
    public int getRoleUser() {
        return roleUser;
    }

    /**
     * @param roleUser
     *            the roleUser to set
     */
    public void setRoleUser(int roleUser) {
        this.roleUser = roleUser;
    }

    /**
     * @return the avatar
     */
    public byte[] getAvatar() {
        return avatar;
    }

    /**
     * @param avatar the avatar to set
     */
    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the confirmation
     */
    public String getConfirmation() {
        return confirmation;
    }

    /**
     * @param confirmation
     *            the confirmation to set
     */
    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    /**
     * @return the idUser
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     *            the idUser to set
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname
     *            the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname
     *            the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telephone
     */
    public Integer getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     *            the telephone to set
     */
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
