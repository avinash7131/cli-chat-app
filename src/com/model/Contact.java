package com.model;

public class Contact {
    private int contactId;
    private int userId;
    private String contactName;
    private String phoneNumber;

    public Contact(int contactId, int userId, String contactName, String phoneNumber) {
        this.contactId = contactId;
        this.userId = userId;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
    }

	// Getters and setters
	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", userId=" + userId + ", contactName=" + contactName
				+ ", phoneNumber=" + phoneNumber + "]";
	}

    
    
}