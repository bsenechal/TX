package com.utc.tx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AVATAR")
public class Avatar implements Serializable{

	private static final long serialVersionUID = 3582966976151597991L;

	@Id
    @GenericGenerator(name = "id_avatar", strategy = "INCREMENT")
    private int idAvatar;
		
    @Column(name = "content", columnDefinition="blob", length = 16777215)
    @Lob()
    private byte[] content;

	/**
	 * @return the idAvatar
	 */
	public int getIdAvatar() {
		return idAvatar;
	}

	/**
	 * @param idAvatar the idAvatar to set
	 */
	public void setIdAvatar(int idAvatar) {
		this.idAvatar = idAvatar;
	}

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
}
