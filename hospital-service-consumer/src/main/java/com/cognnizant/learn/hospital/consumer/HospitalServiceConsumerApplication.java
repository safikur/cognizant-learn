package com.cognnizant.learn.hospital.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.cognnizant.learn.hospital.consumer.bean.SpecialistRequest;
import com.cognnizant.learn.hospital.consumer.service.HospitalConsumerService;

@SpringBootApplication
public class HospitalServiceConsumerApplication implements CommandLineRunner {

	@Autowired
	private HospitalConsumerService hospitalConsumerService;
	
	public static void main(String[] args) {
		SpringApplication.run(HospitalServiceConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SpecialistRequest request = new SpecialistRequest();
		request.setHospitalName("HealthCare");
		request.setSpecialistType("Family Medicine");
		
		// JSON output
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypes);
		
		ResponseEntity<String> response =  hospitalConsumerService.findSpecialist(request, headers);
		System.out.println("Response JSON : " + response.getBody());
		
		// XML output
		headers = new HttpHeaders();
		mediaTypes = new ArrayList<>();
		mediaTypes.add(MediaType.APPLICATION_XML);
		headers.setAccept(mediaTypes);
		
		response =  hospitalConsumerService.findSpecialist(request, headers);
		System.out.println("Response XML : " + response.getBody());
		
	}

}
