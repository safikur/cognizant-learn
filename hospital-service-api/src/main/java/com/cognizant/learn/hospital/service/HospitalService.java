package com.cognizant.learn.hospital.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cognizant.learn.hospital.beans.Appointment;
import com.cognizant.learn.hospital.beans.AppointmentRequest;
import com.cognizant.learn.hospital.beans.Hospital;
import com.cognizant.learn.hospital.beans.PatientStatus;
import com.cognizant.learn.hospital.beans.SpecialistRequest;
import com.cognizant.learn.hospital.config.SpecialistConfig;
import com.cognizant.learn.hospital.config.SpecialistConfig.Specialist;
import com.cognizant.learn.hospital.exception.ApiException;

@Service
public class HospitalService {

	@Autowired
	private SpecialistConfig specialistConfig;
	
	/**
	 * Method to find Specialist details
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	public List<Specialist> findSpecialists(SpecialistRequest request) throws ApiException {
		// validate and find the hospital
		Hospital hospital = validateAndFindHospitalInfo(request.getHospitalName());
		
		List<Specialist> specialists = specialistConfig.getSpecialists();
		List<Specialist> availableSpecialists = specialists.stream()
				.filter(item -> item.getHospitalId().equals(hospital.getHospitalId()))
				.filter(item -> item.getType().equalsIgnoreCase(request.getSpecialistType()))
				.collect(Collectors.toList());
		
		if(CollectionUtils.isEmpty(availableSpecialists)) {
			throw new ApiException("no specialist details found for the specified hospital name.");
		}
		
		return availableSpecialists;
	}
	
	/**
	 * Method to find available beds in a given hospital which counts the number of discharged patients.
	 * @param hospitalName
	 * @return
	 * @throws ApiException
	 */
	public Long findNumberOfAvailableBeds(String hospitalName) throws ApiException {
		// validate and find the hospital
		Hospital hospital = validateAndFindHospitalInfo(hospitalName);
		long dischargedPatients = hospital.getPatientStatusList()
			.stream()
			.filter(item -> item.getStatus().equalsIgnoreCase("DISCHARGE"))
			.count();
		
		return dischargedPatients;
	}
	
	/**
	 * Method to create a new appointment for a patient
	 * @param appointmentRequest
	 * @return
	 * @throws ApiException
	 */
	public Appointment newAppointment(AppointmentRequest appointmentRequest) throws ApiException {
		Specialist specialist = specialistConfig.getSpecialists()
			.stream()
			.filter(item -> item.getName().equalsIgnoreCase(appointmentRequest.getSpecialistName()))
			.filter(item -> item.getAvailableday().contains(appointmentRequest.getAppointmentDay()))
			.filter(item -> item.getIsAvailable().equalsIgnoreCase("Y"))
			.findFirst()
			.orElse(null);
		
		if(null == specialist) {
			throw new ApiException("no specialist details found for the specified request.");
		}
		
		Appointment appointment = new Appointment();
		appointment.setPatientName(appointmentRequest.getPatientName());
		appointment.setSpecialistName(specialist.getName());
		appointment.setAppointmentTime(specialist.getAvailableTime());
		appointment.setAppointmentDay(appointmentRequest.getAppointmentDay());
		
		return appointment;
	}
	
	private Hospital validateAndFindHospitalInfo(String hospitalName) throws ApiException {
		Hospital hospital = getDummyHospitals().stream()
				.filter(item -> item.getHospitalName().equalsIgnoreCase(hospitalName))
				.findFirst()
				.orElse(null);
		
		if(null == hospital) {
			throw new ApiException("invalid input hospital name");
		}
		
		return hospital;
	}
	
	public List<Hospital> getHospital(){
		return getDummyHospitals();
	}
	
	private List<Hospital> getDummyHospitals(){
		List<Hospital> hospitals = new ArrayList<>();
		
		Hospital hospital1 = new Hospital(123, "Hospital 1");
		hospital1.setPatientStatusList(getDummyPatients());
		
		Hospital hospital2 = new Hospital(946, "HealthCare");
		hospital2.setPatientStatusList(getDummyPatients());
		
		
		Hospital hospital3 = new Hospital(123, "NorthShore");
		hospital3.setPatientStatusList(getDummyPatients());
		
		Hospital hospital4 = new Hospital(123, "NCH");
		hospital4.setPatientStatusList(getDummyPatients());
		
		Hospital hospital5 = new Hospital(123, "Hospital5");
		hospital5.setPatientStatusList(getDummyPatients());
		
		hospitals.add(hospital1);
		hospitals.add(hospital2);
		hospitals.add(hospital3);
		hospitals.add(hospital4);
		hospitals.add(hospital5);
		
		return hospitals;
	}
	
	public List<PatientStatus> getDummyPatients(){
		List<PatientStatus> patientStatus = new ArrayList<>();
		
		PatientStatus patient1 = new PatientStatus(1, "ADMITTED");
		PatientStatus patient2 = new PatientStatus(1, "DISCHARGE");
		PatientStatus patient3 = new PatientStatus(1, "ADMITTED");
		PatientStatus patient4 = new PatientStatus(1, "DISCHARGE");
		PatientStatus patient5 = new PatientStatus(1, "DISCHARGE");
		PatientStatus patient6 = new PatientStatus(1, "DISCHARGE");
		PatientStatus patient7 = new PatientStatus(1, "DISCHARGE");
		PatientStatus patient8 = new PatientStatus(1, "ADMITTED");
		
		
		patientStatus.add(patient1);
		patientStatus.add(patient2);
		patientStatus.add(patient3);
		patientStatus.add(patient4);
		patientStatus.add(patient5);
		patientStatus.add(patient6);
		patientStatus.add(patient7);
		patientStatus.add(patient8);
		
		return patientStatus;
	}
}
