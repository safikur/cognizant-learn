package com.cognizant.learn.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.learn.hospital.beans.Appointment;
import com.cognizant.learn.hospital.beans.AppointmentRequest;
import com.cognizant.learn.hospital.beans.BedRequest;
import com.cognizant.learn.hospital.beans.SpecialistRequest;
import com.cognizant.learn.hospital.config.SpecialistConfig.Specialist;
import com.cognizant.learn.hospital.exception.ApiException;
import com.cognizant.learn.hospital.service.HospitalService;

@RestController
@RequestMapping("/appointmentsvc")
public class AppointmentController {
	
	@Autowired
	private HospitalService hospitalService;

	@PostMapping(value = "/specialist", 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody List<Specialist> findSpecialists(@RequestBody SpecialistRequest request) throws ApiException {
		List<Specialist> specialists = null;
		try {
			specialists= hospitalService.findSpecialists(request);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Un-known exception occurred");
		}
		return specialists;
	}
	
	@PostMapping(value = "/beds", 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody String findAvailableBeds(@RequestBody BedRequest request) throws ApiException {
		String response = "Number of Beds Available is = ";
		try {
			long count = hospitalService.findNumberOfAvailableBeds(request.getHospitalName());
			response = response + count;
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Un-known exception occurred");
		}
		
		return response;
	}
	
	@PostMapping(value = "/appointment", 
			consumes=MediaType.APPLICATION_JSON_VALUE, 
			produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Appointment newAppointment(@RequestBody AppointmentRequest appointmentRequest) throws ApiException {
		Appointment appointment = null;
		try {
			appointment = hospitalService.newAppointment(appointmentRequest);
		} catch (ApiException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Un-known exception occurred");
		}
		
		return appointment;
	}
}
