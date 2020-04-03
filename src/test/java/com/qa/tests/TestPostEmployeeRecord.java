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

public class TestPostEmployeeRecord extends TestBase{
	
	String employeeName = RestUtils.empName();
	String employeeSalary = RestUtils.empSalary();
	String employeeAge = RestUtils.empAge();
	
	@BeforeClass
	public void createEmployee() throws InterruptedException{
		
		logger.info("*******************Started TestPostEmployeeRecord**********************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		//JSONObject is a class that represents simple JSON. 
		//We can add key-value pairs using the put method.
		JSONObject requestParameters = new JSONObject();
		requestParameters.put("name", employeeName);
		requestParameters.put("salary", employeeSalary);
		requestParameters.put("age", employeeAge);
		
		//Add a header stating the request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		//Add the JSON to the body of the request.
		httpRequest.body(requestParameters.toJSONString());
		
		responseFromApi = httpRequest.request(Method.POST,"/create");
		
		Thread.sleep(5000);
		
	}
	
	@Test
	public void checkResponseBody(){
		
		String responseBody = responseFromApi.getBody().asString();
		logger.info("The Response from API is :---> " + responseBody);
		Assert.assertEquals(responseBody!=null, true);
		Assert.assertEquals(responseBody.contains(employeeName), true);
		Assert.assertEquals(responseBody.contains(employeeSalary), true);
		Assert.assertEquals(responseBody.contains(employeeAge), true);
		
	}
	
	@Test
	public void checkContentEncoding(){
		
		logger.info("***********************Check Content Encoding************************");
		String serverType = responseFromApi.header("Content-Encoding");
		logger.info("The Content-Encoding is :---> " + serverType);
		Assert.assertEquals(serverType, "gzip");
	}
	
	@AfterClass
	public void tearDown(){
		
		logger.info("********************Coming out of TestPostEmployeeRecord********************");
		
	}
	
	

}
