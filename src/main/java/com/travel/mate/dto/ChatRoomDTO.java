package com.travel.mate.dto;

public class ChatRoomDTO {
	int roomCode;
	String receive;
	int senderCode;
	int receiverCode;
	String latestdate;
	int unread;
	
	public ChatRoomDTO() {

	}

	public ChatRoomDTO(int roomCode, String send, int senderCode, String receive, int receiverCode, String latestdate, int unread) {
		super();
		this.roomCode = roomCode;
		this.receive = receive;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.latestdate = latestdate;
		this.unread = unread;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}
	
	public int getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(int senderCode) {
		this.senderCode = senderCode;
	}

	public int getreceiverCode() {
		return receiverCode;
	}

	public void setreceiverCode(int receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getLatestDate() {
		return latestdate;
	}

	public void setLatestDate(String latestdate) {
		this.latestdate = latestdate;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}
	
}
