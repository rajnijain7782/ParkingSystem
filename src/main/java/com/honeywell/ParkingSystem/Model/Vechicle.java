package com.honeywell.ParkingSystem.Model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit; 


public class Vechicle {

	private String vechicleNumber;
	private String vechicleType;
	private String inTime;
	private String outTime;
	private String parkingTime;
	

	public Vechicle(String vechicleNumber, String vechicleType) {
		super();
		this.vechicleNumber = vechicleNumber;
		this.vechicleType = vechicleType;
	}
	public String getVechicleNumber() {
		return vechicleNumber;
	}
	public void setVechicleNumber(String vechicleNumber) {
		this.vechicleNumber = vechicleNumber;
	}
	public String getVechicleType() {
		return vechicleType;
	}
	public void setVechicleType(String vechicleType) {
		vechicleType = vechicleType;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	public String getParkingTime() {
		return parkingTime;
	}
	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}
	
public long findDifference(String start_date, 
            String end_date,SimpleDateFormat sdf) throws ParseException 
{ 
Date d1 = sdf.parse(start_date); 
Date d2 = sdf.parse(end_date); 

long difference_In_Time = d2.getTime() - d1.getTime(); 
return difference_In_Time;
}

// Print result 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getVechicleNumber()+this.getVechicleType();
	}

	
}
