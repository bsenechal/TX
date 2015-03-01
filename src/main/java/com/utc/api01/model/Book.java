package com.utc.api01.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GenericGenerator(name = "IDBOOK", strategy = "INCREMENT")
    private int idBook;

    @Column(name = "title")
    @NotEmpty
    @Size(max = 45)
    private String title;

    @Column(name = "autor")
    @NotEmpty
    @Size(max = 45)
    private String autor;

    @Column(name = "type")
    @NotEmpty
    @Size(max = 45)
    private String type;

    @Column(name = "description")
    @NotEmpty
    private String description;

    @Column(name = "image", columnDefinition="blob", length = 16777215)
    @Lob()
    private byte[] image;
    
    

    /**
     * @param idBook
     * @param title
     * @param autor
     * @param type
     * @param description
     * @param image
     */
    public Book(int idBook, String title, String autor, String type,
            String description, byte[] image) {
        super();
        this.idBook = idBook;
        this.title = title;
        this.autor = autor;
        this.type = type;
        this.description = description;
        this.image = image;
    }
    
    public Book(int idBook, String title, String autor, String type,
            String description) {
        super();
        this.idBook = idBook;
        this.title = title;
        this.autor = autor;
        this.type = type;
        this.description = description;
    }    

    /**
     * 
     */
    public Book() {
        super();
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the idBook
     */
    public int getIdBook() {
        return idBook;
    }

    /**
     * @param idBook
     *            the idBook to set
     */
    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor
     *            the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
