package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "NOTES")
public class Notes {

    @Id
    @GenericGenerator(name = "IDQUESTIONS", strategy = "INCREMENT")
    private int idNotes;

    @Column(name = "note")
    @Min(0)
    @Max(5)
    private int note;

    @ManyToOne
    @JoinColumn(name = "FKEVAL")
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "FKQUESTION")
    private Question question;

    /**
     * @param idNotes
     * @param note
     * @param evaluation
     * @param question
     */
    public Notes(int idNotes, int note, Evaluation evaluation, Question question) {
        super();
        this.idNotes = idNotes;
        this.note = note;
        this.evaluation = evaluation;
        this.question = question;
    }

    /**
     * 
     */
    public Notes() {
        super();
    }

    /**
     * @return the idNotes
     */
    public int getIdNotes() {
        return idNotes;
    }

    /**
     * @param idNotes
     *            the idNotes to set
     */
    public void setIdNotes(int idNotes) {
        this.idNotes = idNotes;
    }

    /**
     * @return the note
     */
    public int getNote() {
        return note;
    }

    /**
     * @param note
     *            the note to set
     */
    public void setNote(int note) {
        this.note = note;
    }

    /**
     * @return the evaluation
     */
    public Evaluation getEvaluation() {
        return evaluation;
    }

    /**
     * @param evaluation
     *            the evaluation to set
     */
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question
     *            the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

}
