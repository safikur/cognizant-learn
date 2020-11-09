package com.cognizant.learn.hospital.beans;

public class PatientStatus {

	private Integer patientId;
	private String status;
	
	public PatientStatus(Integer patientId, String status) {
		super();
		this.patientId = patientId;
		this.status = status;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
