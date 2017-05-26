package com.travel.mate.dto;

public class ChatDTO {
	int messageCode;
	int roomCode;
	int senderCode;
	int receiverCode;
	String content;
	String sendDate;
	boolean readFlag;
	
	public ChatDTO(){
		
	}
	
	public ChatDTO(int messageCode, int roomCode, int senderCode, int receiverCode, String content, String sendDate,
			boolean readFlag) {
		super();
		this.messageCode = messageCode;
		this.roomCode = roomCode;
		this.senderCode = senderCode;
		this.receiverCode = receiverCode;
		this.content = content;
		this.sendDate = sendDate;
		this.readFlag = readFlag;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
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

}
