package com.travel.mate.dto;

public class ChatRoomDTO {
	int roomCode;
	String send;
	int senderCode;
	String receive;
	int receiverCode;
	String latestdate;

	public ChatRoomDTO() {

	}

	public ChatRoomDTO(int roomCode, String send, int sendereceiverCode, String receive, int receiverCode, String latestdate) {
		super();
		this.roomCode = roomCode;
		this.send = send;
		this.senderCode = sendereceiverCode;
		this.receive = receive;
		this.receiverCode = receiverCode;
		this.latestdate = latestdate;
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

	public int getsenderCode() {
		return senderCode;
	}

	public void setsenderCode(int senderCode) {
		this.senderCode = senderCode;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
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
}
