package com.travel.mate.dto;

import org.springframework.data.annotation.Id;

public class ChatDTO {
	
	@Id
	private String id;
	
	int roomCode;
	int senderCode;
	int receiverCode;
	String content;
	String sendDate;
	boolean readFlag;
	String senderName;

	
	public ChatDTO(){
		
	}
	
	public ChatDTO( int roomCode, int senderCode, int receiverCode, String content, String sendDate,
			boolean readFlag, String senderName) {
		super();
	
		this.roomCode = roomCode;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.content = content;
		this.sendDate = sendDate;
		this.readFlag = readFlag;
		this.senderName = senderName;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public boolean isReadFlag() {
		return readFlag;
	}

	public void setReadFlag(boolean readFlag) {
		this.readFlag = readFlag;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

}
