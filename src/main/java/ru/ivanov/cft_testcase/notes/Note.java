/**
 * 
 */
package ru.ivanov.cft_testcase.notes;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author a.ivanov
 *
 */
@Entity
public class Note {
	 @Id
	 private int id;
	 private String content;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
