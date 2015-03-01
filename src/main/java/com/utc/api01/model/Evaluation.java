package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "EVALUATION")
public class Evaluation {

    @Id
    @GenericGenerator(name = "IDEVAL", strategy = "INCREMENT")
    private int idEval;

    @Column(name = "status")
    private int status;

    @ManyToOne
    @JoinColumn(name = "FKBOOK")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "FKUSER")
    private User user;

    /**
     * 
     */
    public Evaluation() {
        super();
    }

    /**
     * @param idEval
     * @param status
     * @param book
     * @param user
     */
    public Evaluation(int idEval, int status, Book book, User user) {
        super();
        this.idEval = idEval;
        this.status = status;
        this.book = book;
        this.user = user;
    }

    /**
     * @return the idEval
     */
    public int getIdEval() {
        return idEval;
    }

    /**
     * @param idEval
     *            the idEval to set
     */
    public void setIdEval(int idEval) {
        this.idEval = idEval;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
