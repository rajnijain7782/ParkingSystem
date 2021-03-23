package com.honeywell.ParkingSystem;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.honeywell.ParkingSystem.Model.Vechicle;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@MappedTypes(Vechicle.class)
@MapperScan("com.honeywell.ParkingSystem.Mapper")
@SpringBootApplication
public class ParkingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSystemApplication.class, args);
	}
	
	  @Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("com.honeywell.ParkingSystem")).build();
	   }

}
