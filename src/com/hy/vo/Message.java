package com.hy.vo;

public class Message {
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	private boolean state;
	private String message ;
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Message(boolean state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
}
