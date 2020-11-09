package com.cognizant.learn.hospital.beans;

import java.util.List;

public class Hospital {
	
	private Integer hospitalId;
	private String hospitalName;
	private List<PatientStatus> patientStatusList;
	
	public Hospital(Integer hospitalId, String hospitalName) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
	}
	
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public List<PatientStatus> getPatientStatusList() {
		return patientStatusList;
	}

	public void setPatientStatusList(List<PatientStatus> patientStatus) {
		this.patientStatusList = patientStatus;
	}
	
}
