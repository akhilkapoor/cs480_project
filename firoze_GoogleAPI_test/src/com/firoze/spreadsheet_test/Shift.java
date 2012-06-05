package com.firoze.spreadsheet_test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity(name = "Shifts")
public class Shift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "dayOfWeek", nullable = false)
	private String dayOfWeek;
	
	@Column(name = "startTime", nullable = false)
	private String startTime;
	
	@Column(name = "endTime", nullable = false)
	private String endTime;
	
	@Column(name = "location", nullable = false)
	private String location;
	
	@Column(name = "person", nullable = true)
	private String person;
	
	@Column(name = "shiftState", nullable = false)
	private String shiftState;
	
	@Column(name = "shiftNotes", nullable = false)
	private String shiftNotes;
	
	public Shift(){}
	
	public Shift(
			String _dayOfWeek,
			String _startTime,
			String _endTime,
			String _location,
			String _person,
			String _shiftState,
			String _shiftNotes
			){
		this.setDayOfWeek(_dayOfWeek);
		this.setStartTime(_startTime);
		this.setEndTime(_endTime);
		this.setLocation(_location);
		this.setPerson(_person);
		this.setShiftState(_shiftState);
		this.setShiftNotes(_shiftNotes);
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public String getShiftState() {
		return shiftState;
	}
	public void setShiftState(String shiftState) {
		this.shiftState = shiftState;
	}
	
	public String getShiftNotes() {
		return shiftNotes;
	}
	public void setShiftNotes(String shiftNotes) {
		this.shiftNotes = shiftNotes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
}
