package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestGetSingleEmployee extends TestBase{
	
	
	@BeforeClass
	public void setUp(){
		
		logger.info("*************************Entered TestGetSingleEmployee TestClass****************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		responseFromApi = httpRequest.request(Method.GET,"/employee/" + empId);
		
	}
	
	@Test
	public void checkResponseBody(){
		
		logger.info("*****************Checking the Response Body****************");
		String responseBody = responseFromApi.getBody().asString();
		logger.info("The response from the API is :---> " + responseBody);
		Assert.assertTrue(responseBody!=null);
		Assert.assertEquals(responseBody.contains(empId), true);
		
	}
	
	@AfterClass
	public void tearDown(){
		
		logger.info("******************Coming out of the execution of TestGetSingleData**************");
		
		
	}

}
