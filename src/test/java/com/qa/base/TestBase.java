package com.qa.base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response responseFromApi;
	public String empId = "2";   //Hard-Coded employee id for POST and PUT request.
	
	public Logger logger;
	
	@BeforeClass
	public void setUp() throws Exception{
		
		logger = Logger.getLogger("EmployeeRestAPI");    //added logger
		PropertyConfigurator.configure("Log4j.properties");    //added logger
		logger.setLevel(Level.DEBUG);
		
		
	}

}
