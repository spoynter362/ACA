package com.aca.rest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {
	
			private int id;
			private String lastName;
			private String firstName;
			private LocalDate birthdate;
			private LocalDate hiredate;
			private String address;
			private String city;
			private String StateId;
			private BigDecimal salary;
			private int titleId;
			private int titleOfCourtesyId;
			private String zipCode;
			private String personalPhone;
			private String extension;
			private String picture;
			private String notes;
			private int reportsToId;
			
			
			public LocalDate getHiredate() {
				return hiredate;
			}
			public void setHiredate(LocalDate hiredate) {
				this.hiredate = hiredate;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getStateId() {
				return StateId;
			}
			public void setStateId(String stateId) {
				StateId = stateId;
			}
			
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getLastName() {
				return lastName;
			}
			public void setLastName(String lastName) {
				this.lastName = lastName;
			}
			public String getFirstName() {
				return firstName;
			}
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			public LocalDate getBirthdate() {
				return birthdate;
			}
			public void setBirthdate(LocalDate birthdate) {
				this.birthdate = birthdate;
			}
			public BigDecimal getSalary() {
				return salary;
			}
			public void setSalary(BigDecimal bigDecimal) {
				this.salary = bigDecimal;
			}
			public int getTitleId() {
				return titleId;
			}
			public void setTitleId(int titleId) {
				this.titleId = titleId;
			}
			public int getTitleOfCourtesyId() {
				return titleOfCourtesyId;
			}
			public void setTitleOfCourtesyId(int titleOfCourtesyId) {
				this.titleOfCourtesyId = titleOfCourtesyId;
			}
			public String getZipCode() {
				return zipCode;
			}
			public void setZipCode(String zipCode) {
				this.zipCode = zipCode;
			}
			public String getPersonalPhone() {
				return personalPhone;
			}
			public void setPersonalPhone(String personalPhone) {
				this.personalPhone = personalPhone;
			}
			public String getExtension() {
				return extension;
			}
			public void setExtension(String extension) {
				this.extension = extension;
			}
			public String getPicture() {
				return picture;
			}
			public void setPicture(String picture) {
				this.picture = picture;
			}
			public String getNotes() {
				return notes;
			}
			public void setNotes(String notes) {
				this.notes = notes;
			}
			public int getReportsToId() {
				return reportsToId;
			}
			public void setReportsToId(int reportsToId) {
				this.reportsToId = reportsToId;
			}
			
	

}
