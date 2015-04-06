package com.utc.tx.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "USER")
public class User implements Serializable{

	private static final long serialVersionUID = -5327553925389840714L;

	@Id
    @GenericGenerator(name = "id_user", strategy = "INCREMENT")
	@Column(name = "id_user")
    private int idUser;

    @Column(name = "password")
    @Size(min = 6, max = 30)
    private String password;

    @Column(name = "first_name")
    @Size(min = 1, max = 40)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 1, max = 40)
    private String lastName;
    
    @Column(name = "login")
    @Size(min = 1, max = 40)
    private String login;

    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    @Column(name = "phone_number")
    @NotNull
    @Min(10)
    private Integer phoneNumber;

    @Column(name = "description")
    private String description;
    
    @Column(name = "creation_date")
    private Date creationDate;
    
    @Column(name = "deal_count")
    private Integer dealCount;
    
    @Column(name = "comment_count")
    private Integer commentCount;
    
    @Column(name = "deal_positive_grade")
    private Integer dealPositiveGrade;
    
    @Column(name = "deal_negative_grade")
    private Integer dealNegativeGrade;
    
    @Column(name = "comment_positive_grade")
    private Integer commentPositiveGrade;
    
    @Column(name = "comment_negative_grade")
    private Integer commentNegativeGrade;
    
    @Column(name = "longitude")
    private Float longitude;
    
    @Column(name = "latitude")
    private Float latitude;
    
    @Column(name = "news_letter")
    private Integer newsLetter;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_avatar")
    private Avatar fkAvatar;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role")
    private Role role;

	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}



	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the dealCount
	 */
	public Integer getDealCount() {
		return dealCount;
	}

	/**
	 * @param dealCount the dealCount to set
	 */
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}

	/**
	 * @return the commentCount
	 */
	public Integer getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the dealPositiveGrade
	 */
	public Integer getDealPositiveGrade() {
		return dealPositiveGrade;
	}

	/**
	 * @param dealPositiveGrade the dealPositiveGrade to set
	 */
	public void setDealPositiveGrade(Integer dealPositiveGrade) {
		this.dealPositiveGrade = dealPositiveGrade;
	}

	/**
	 * @return the dealNegativeGrade
	 */
	public Integer getDealNegativeGrade() {
		return dealNegativeGrade;
	}

	/**
	 * @param dealNegativeGrade the dealNegativeGrade to set
	 */
	public void setDealNegativeGrade(Integer dealNegativeGrade) {
		this.dealNegativeGrade = dealNegativeGrade;
	}

	/**
	 * @return the commentPositiveGrade
	 */
	public Integer getCommentPositiveGrade() {
		return commentPositiveGrade;
	}

	/**
	 * @param commentPositiveGrade the commentPositiveGrade to set
	 */
	public void setCommentPositiveGrade(Integer commentPositiveGrade) {
		this.commentPositiveGrade = commentPositiveGrade;
	}

	/**
	 * @return the commentNegativeGrade
	 */
	public Integer getCommentNegativeGrade() {
		return commentNegativeGrade;
	}

	/**
	 * @param commentNegativeGrade the commentNegativeGrade to set
	 */
	public void setCommentNegativeGrade(Integer commentNegativeGrade) {
		this.commentNegativeGrade = commentNegativeGrade;
	}
	
	/**
	 * @return the longitude
	 */
	public Float getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the latitude
	 */
	public Float getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the newsLetter
	 */
	public Integer getNewsLetter() {
		return newsLetter;
	}

	/**
	 * @param newsLetter the newsLetter to set
	 */
	public void setNewsLetter(Integer newsLetter) {
		this.newsLetter = newsLetter;
	}

	/**
	 * @return the fkAvatar
	 */
	public Avatar getFkAvatar() {
		return fkAvatar;
	}

	/**
	 * @param fkAvatar the fkAvatar to set
	 */
	public void setFkAvatar(Avatar fkAvatar) {
		this.fkAvatar = fkAvatar;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}    
}
