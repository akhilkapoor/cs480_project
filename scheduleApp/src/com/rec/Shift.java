package com.rec;

public class Shift {
	
	private Long id;
	private String dayOfWeek;
	private String startTime;
	private String endTime;
	private String location;
	private String person;
	private String shiftState;
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
		this.setStartTime(_startTime);
		this.setEndTime(_endTime);
		this.setLocation(_location);
		this.setPerson(_person);
		this.setShiftState(_shiftState);
		this.setShiftNotes(_shiftNotes);
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
}
