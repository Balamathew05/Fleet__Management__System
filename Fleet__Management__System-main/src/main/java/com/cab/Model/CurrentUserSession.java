package com.cab.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrentUserSession {

	@Id
	private Integer currUserId;
	private String uuid;
	private String currRole;
	private String currStatus;
	private String otp;
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Integer getCurrUserId() {
		return currUserId;
	}
	public void setCurrUserId(Integer currUserId) {
		this.currUserId = currUserId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCurrRole() {
		return currRole;
	}
	public void setCurrRole(String currRole) {
		this.currRole = currRole;
	}
	public String getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(String currStatus) {
		this.currStatus = currStatus;
	}
}
