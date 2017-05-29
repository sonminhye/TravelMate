package com.travel.mate.dto;

public class ChatRoomDTO {
	int roomCode;
	String send;
	int sCode;
	String receive;
	int rCode;
	String latestDate;
	int myCode;
	
	public ChatRoomDTO(){
		
	}
	
	public ChatRoomDTO(int roomCode, String send, int sCode, String receive, int rCode, String latestDate, int myCode) {
		super();
		this.roomCode = roomCode;
		this.send = send;
		this.sCode = sCode;
		this.receive = receive;
		this.rCode = rCode;
		this.latestDate = latestDate;
		this.myCode = myCode;
	}
	
	public int getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	public int getsCode() {
		return sCode;
	}
	public void setsCode(int sCode) {
		this.sCode = sCode;
	}
	public String getReceive() {
		return receive;
	}
	public void setReceive(String receive) {
		this.receive = receive;
	}
	public int getrCode() {
		return rCode;
	}
	public void setrCode(int rCode) {
		this.rCode = rCode;
	}
	public String getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(String latestDate) {
		this.latestDate = latestDate;
	}
	public int getMyCode() {
		return myCode;
	}
	public void setMyCode(int myCode) {
		this.myCode = myCode;
	}
	
}	
