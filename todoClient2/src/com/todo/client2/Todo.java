package com.todo.client2;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Todo implements Serializable {

	private Long id;
	private String title;
	private String notes;
	private String dueDate;
	private boolean is_deleted;

	public Todo () {
		this.title="";
		this.notes="";
		this.dueDate = "";
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the is_deleted
	 */
	public boolean isIs_deleted() {
		return is_deleted;
	}
	/**
	 * @param is_deleted the is_deleted to set
	 */
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	@Override
	public String toString() {
		String todoString = new String();
		todoString += "Title: " + title + "\n";
		todoString += "Due Date: " + dueDate + "\n";
		if ( ! notes.isEmpty() ) {
			todoString += "Notes: " + notes;
		}
		return todoString;
	}
}
