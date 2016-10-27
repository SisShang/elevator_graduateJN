package com.database;

import java.io.Serializable;

import android.provider.ContactsContract.Profile;

public class User implements Serializable {
	private  int id;
	private String reportNo;
	private String unit;
	private String address;
	private String contact;
	private String phoneNo;
	private String deviceNo;
	private String governorSpec;
	private String governorNo;
	private String produceTime;
	private String produceUnit;
	private String governorDiameter;
	private String governorPerimeter;
	private String speed;
	private String type;
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getGovernorSpec() {
		return governorSpec;
	}
	public void setGovernorSpec(String governorSpec) {
		this.governorSpec = governorSpec;
	}
	public String getGovernorNo() {
		return governorNo;
	}
	public void setGovernorNo(String governorNo) {
		this.governorNo = governorNo;
	}
	public String getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}
	public String getProduceUnit() {
		return produceUnit;
	}
	public void setProduceUnit(String produceUnit) {
		this.produceUnit = produceUnit;
	}
	public String getGovernorDiameter() {
		return governorDiameter;
	}
	public void setGovernorDiameter(String governorDiameter) {
		this.governorDiameter = governorDiameter;
	}
	public String getGovernorPerimeter() {
		return governorPerimeter;
	}
	public void setGovernorPerimeter(String governorPerimeter) {
		this.governorPerimeter = governorPerimeter;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public User(){}
	public User(String reportNo,String unit,String address,String contact,String phoneNo,String deviceNo,
			String governorSpec,String governorNo,String produceTime,String produceUnit,String governorDiameter,
			String governorPerimeter,String speed,String type){
		this.address=address;
		this.contact=contact;
		this.phoneNo=phoneNo;
		this.reportNo=reportNo;
		this.unit=unit;
		this.deviceNo=deviceNo;
		this.governorNo=governorNo;
		this.governorSpec=governorSpec;
		this.governorNo=governorNo;
		this.produceTime=produceTime;
		this.produceUnit=produceUnit;
		this.governorDiameter=governorDiameter;
		this.governorPerimeter=governorPerimeter;
		this.speed=speed;
		this.type=type;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
