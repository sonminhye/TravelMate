package com.travel.mate.dto;

public class ChatRoomDTO {
	int roomCode;
	int senderCode;
	int receiverCode;
	String latestDate;
	
	public ChatRoomDTO(){
		
	}
	
	public ChatRoomDTO(int roomCode, int senderCode, int receiverCode, String latestDate) {
		super();
		this.roomCode = roomCode;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.latestDate = latestDate;
	}
	public int getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}
	public int getSenderCode() {
		return senderCode;
	}
	public void setSenderCode(int senderCode) {
		this.senderCode = senderCode;
	}
	public int getReceiverCode() {
		return receiverCode;
	}
	public void setReceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
	}
	public String getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(String latestDate) {
		this.latestDate = latestDate;
	}
	
}	
