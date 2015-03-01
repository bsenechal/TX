package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "QUESTIONS")
public class Question {

    @Id
    @GenericGenerator(name = "IDQUESTIONS", strategy = "INCREMENT")
    private int idQuestions;

    @Column(name = "libelle")
    @NotEmpty
    @Size(max = 45)
    private String libelle;

    @Column(name = "valMax")
    @Min(0)
    @Max(10)
    private int valMax;

    @Column(name = "ponderation")
    @Min(0)
    @Max(10)
    private int ponderation;
    
    @Transient
    @Min(0)
    @Max(5)
    private int note;

    /**
     * @param idQuestion
     * @param libelle
     * @param valMax
     * @param ponderation
     */
    public Question(int idQuestions, String libelle, Integer valMax,
            Integer ponderation) {
        super();
        this.idQuestions = idQuestions;
        this.libelle = libelle;
        this.valMax = valMax;
        this.ponderation = ponderation;
    }

    /**
     * 
     */
    public Question() {
        super();
    }

    /**
     * @return the idQuestions
     */
    public int getIdQuestions() {
        return idQuestions;
    }

    /**
     * @param idQuestions
     *            the idQuestion to set
     */
    public void setIdQuestions(int idQuestions) {
        this.idQuestions = idQuestions;
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
     * @return the valMax
     */
    public Integer getValMax() {
        return valMax;
    }

    /**
     * @param valMax
     *            the valMax to set
     */
    public void setValMax(Integer valMax) {
        this.valMax = valMax;
    }

    /**
     * @return the ponderation
     */
    public Integer getPonderation() {
        return ponderation;
    }

    /**
     * @param ponderation
     *            the ponderation to set
     */
    public void setPonderation(Integer ponderation) {
        this.ponderation = ponderation;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * @param valMax the valMax to set
     */
    public void setValMax(int valMax) {
        this.valMax = valMax;
    }

    /**
     * @param ponderation the ponderation to set
     */
    public void setPonderation(int ponderation) {
        this.ponderation = ponderation;
    }

   
    
}
