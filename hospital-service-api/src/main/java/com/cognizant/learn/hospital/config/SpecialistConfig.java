package com.cognizant.learn.hospital.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix="specialistdetails")
@PropertySource("classpath:specialist.properties")
public class SpecialistConfig {

	private List<Specialist> specialists;
	
	public List<Specialist> getSpecialists() {
		return specialists;
	}

	public void setSpecialists(List<Specialist> specialists) {
		this.specialists = specialists;
	}

	public static class Specialist {

		private String type;
		private String name;
		private String availableday;
		private String availableTime;
		private String isAvailable;
		private Integer hospitalId;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getHospitalId() {
			return hospitalId;
		}
		public void setHospitalId(Integer hospitalId) {
			this.hospitalId = hospitalId;
		}
		public String getAvailableday() {
			return availableday;
		}
		public void setAvailableday(String availableDays) {
			this.availableday = availableDays;
		}
		public String getAvailableTime() {
			return availableTime;
		}
		public void setAvailableTime(String availableTime) {
			this.availableTime = availableTime;
		}
		public String getIsAvailable() {
			return isAvailable;
		}
		public void setIsAvailable(String isAvailable) {
			this.isAvailable = isAvailable;
		}
		@Override
		public String toString() {
			return "Specialist [type=" + type + ", name=" + name + ", hospitalId=" + hospitalId + ", availableday="
					+ availableday + ", availableTime=" + availableTime + ", isAvailable=" + isAvailable + "]";
		}
		
	}
}
