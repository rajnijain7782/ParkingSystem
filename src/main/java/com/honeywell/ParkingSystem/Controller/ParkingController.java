package com.honeywell.ParkingSystem.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.honeywell.ParkingSystem.Mapper.VechicleMapper;
import com.honeywell.ParkingSystem.Model.Vechicle;

@RestController
public class ParkingController {
	static AtomicInteger slotsAvailable = new AtomicInteger(100);
	VechicleMapper mapper;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//SimpleDateFormat timeFormat=new SimpleDateFormat("HH:mm:ss");

	Logger logger = LoggerFactory.getLogger(ParkingController.class);

	ParkingController(VechicleMapper mapper) {
		this.mapper = mapper;
	}

	@PostMapping("/park")
	public String park(@RequestBody Vechicle vechicle) {
		logger.info(vechicle.getVechicleNumber() + ":" + vechicle.getVechicleType());
		if (vechicle.getVechicleNumber()==null)
			return "Please provide Vechicle Number";
		vechicle.setInTime(formatter.format(new Date()));
		if (slotsAvailable.get() == 0)
			return "Parking is Full";
		mapper.insert(vechicle.getVechicleNumber(), vechicle.getVechicleType(), vechicle.getInTime());
		slotsAvailable.getAndDecrement();
		return "Vechicle is Parked Successfully";
	}

	@PostMapping("/unpark")
	public String unpark(@RequestBody Vechicle vechicle) {
		if (vechicle.getVechicleNumber()==null)
			return "Please provide Vechicle Number";
		Vechicle result = mapper.findById(vechicle.getVechicleNumber());
		if (null == result)
			return "No Vechicle for this number available";
		String outTime = formatter.format(new Date());
		String parkingTime=calculateTime(result);
		mapper.update(outTime,parkingTime,result.getVechicleNumber());
		slotsAvailable.getAndIncrement();
		return parkingTime;
	}

	@GetMapping("/all")
	public List<Vechicle> listofvechicles() {
		return mapper.findAll();

	}
	
	@GetMapping("/availableslots")
	public int availableslots() {
		return slotsAvailable.get();

	}
	
	@PostMapping("/parkingTime")
	public String calculateTime(@RequestBody Vechicle vechicle) {
		Vechicle v =mapper.findById(vechicle.getVechicleNumber());
		if(null==v)
			return "No Vechicle is parked with " + vechicle.getVechicleNumber() + " number";
		String outTime = formatter.format(new Date());
		long difference_In_Time = 0;
		try {
			difference_In_Time = v.findDifference(v.getInTime(),outTime,formatter);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.error("Issue While parsing the dates"+e);				
		}
		long difference_In_Minutes = TimeUnit.MILLISECONDS .toMinutes(difference_In_Time) % 60; 

		long difference_In_Hours = TimeUnit .MILLISECONDS .toHours(difference_In_Time) % 24; 

		long difference_In_Days = TimeUnit .MILLISECONDS .toDays(difference_In_Time) % 365; 

		return difference_In_Days +" Days" + difference_In_Hours +" Hours "
				+ difference_In_Minutes +" Mins "; 
		
	}
	
}
