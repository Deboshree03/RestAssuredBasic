package com.qa.tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.utils.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestPutEmployeeRecord extends TestBase {
	
	String employeeName = RestUtils.empName();
	String employeeSalary = RestUtils.empSalary();
	String employeeAge = RestUtils.empAge();
	
	@BeforeClass
	public void putRecord() throws InterruptedException{
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		JSONObject requestParameters = new JSONObject();
		requestParameters.put("name", employeeName);
		requestParameters.put("salary", employeeSalary);
		requestParameters.put("age", employeeAge);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestParameters.toJSONString());
		
		responseFromApi = httpRequest.request(Method.PUT,"/update/"+empId);
		
		Thread.sleep(5000);
		
	}
	
	@Test
	public void checkResponseBody(){
		
		String responseBody = responseFromApi.getBody().asString();
		logger.info("The response from API is :---> " + responseBody);
		Assert.assertEquals(responseBody!=null, true);
		Assert.assertEquals(responseBody.contains(employeeName), true);
		Assert.assertEquals(responseBody.contains(employeeSalary), true);
		Assert.assertEquals(responseBody.contains(employeeAge), true);
		
	}
	
	@Test
	public void checkStatusCode(){
		
		logger.info("**********************Checking the Response Status Code************************");
		int statusCode = responseFromApi.getStatusCode();
		logger.info("The Status Code returned from the API is :---> " + statusCode);
		Assert.assertEquals("statusCode", 200);
		
	}
	
	@AfterClass
	public void tearDown(){
		
		logger.info("********************Coming out of TestPutEmployeeRecord********************");
		
	}
	

}
