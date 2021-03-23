package com.honeywell.ParkingSystem.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.honeywell.ParkingSystem.Model.Vechicle;

@Mapper
public interface VechicleMapper {

	@Select("Select * from Vechicle where outTime is null")
	List<Vechicle> findAll();
	
	@Select("SELECT * FROM Vechicle WHERE vechicleNumber = #{vechicleNumber} and outTime is null")
    public Vechicle findById(String vechicleNumber);
	
	@Delete("DELETE FROM Vechicle WHERE vechicleNumber = #{vechicleNumber}")
    public int deleteById(String vechicleNumber);

	@Update("Update Vechicle set outTime=#{outTime},parkingTime=#{parkingTime} WHERE vechicleNumber = #{vechicleNumber} and outTime is null")
	public void update(String outTime,String parkingTime,String vechicleNumber) ;
	
    @Insert("INSERT INTO Vechicle(vechicleNumber, VechicleType, inTime) " +
        " VALUES (#{vechicleNumber}, #{vechicleType},#{inTime} )")
    public int insert(String vechicleNumber, String vechicleType, String inTime);


}

