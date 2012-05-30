package com.rec;

public class ShiftStruct {
	private String shiftTime;
	private String location;
	private String person;
	private String shiftState;
	private String shiftNotes;
	
	public ShiftStruct(){}
	public ShiftStruct(
			String _shiftTime,
			String _location,
			String _person,
			String _shiftState,
			String _shiftNotes
			){
		this.setShiftTime(_shiftTime);
		this.setLocation(_location);
		this.setPerson(_person);
		this.setShiftState(_shiftState);
		this.setShiftNotes(_shiftNotes);
	}
	
	public String getShiftTime() {
		return shiftTime;
	}
	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
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
