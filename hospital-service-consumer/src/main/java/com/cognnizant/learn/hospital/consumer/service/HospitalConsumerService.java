package com.cognnizant.learn.hospital.consumer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognnizant.learn.hospital.consumer.bean.SpecialistRequest;

@Service
public class HospitalConsumerService {
	@Value("${api.endpoint}")
	private String apiURL;
	
	@Value("${api.endpoint.specialist}")
	private String specialistApiPath;

	public ResponseEntity<String> findSpecialist(SpecialistRequest request, HttpHeaders headers) {
		RestTemplate restTemplate = new RestTemplate();
		String url = apiURL + specialistApiPath;
		
		HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
		ResponseEntity<String> response = null;
		
		response = restTemplate.postForEntity(url, requestEntity, String.class);
		
		return response;
	}
}
