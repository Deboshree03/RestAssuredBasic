package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TestDeleteEmployeeRecord extends TestBase{
	
	@BeforeClass
	public void deleteRecord() throws InterruptedException{
		
		logger.info("*****************Starting execution of class TestDeleteEmployeeRecord*****************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		responseFromApi = httpRequest.request(Method.GET,"/employees");
		
		//First get the JsonPath object instance from the Response interface.
		JsonPath jsonPathEvaluator = responseFromApi.jsonPath();
		
		//Capture id
		String empID = jsonPathEvaluator.get("[0].id");
		responseFromApi = httpRequest.request(Method.DELETE,"/delete/" + empID); //Pass ID to delete record.
		
		Thread.sleep(3);
	}
	
	@Test
	public void checkResponseBody(){
		
		String responseBody = responseFromApi.body().asString();
		logger.info("The response received from API is :---> " + responseBody);
		Assert.assertEquals(responseBody!=null, true);
	}
	
	@AfterClass
	public void tearDown(){
		
		logger.info("********************Coming out of TestDeleteEmployeeRecord********************");
		
	}
	

}
