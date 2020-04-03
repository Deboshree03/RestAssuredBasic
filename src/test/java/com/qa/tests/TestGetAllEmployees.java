package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TestGetAllEmployees extends TestBase {
	
	@BeforeClass
	public void setUp() throws Exception{
		
		logger.info("***************Started TestGetAllEmployees*******************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		responseFromApi = httpRequest.request(Method.GET,"/employees");
		Thread.sleep(3);
		
	}
	
	@Test
	public void checkResponseBody(){
		
		logger.info("*********************Checking the Response Body**********************");
		String responseBody = responseFromApi.getBody().asString();
		logger.info("The response from API is :---> " + responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	@Test
	public void checkStatusCode(){
		
		logger.info("**********************Checking the Response Status Code************************");
		int statusCode = responseFromApi.getStatusCode();
		logger.info("The Status Code returned from the API is :---> " + statusCode);
		Assert.assertEquals("statusCode", 200);
		
	}
	
	@Test
	public void checkResponseTime(){
		
		logger.info("*****************Checking the Response Time*********************");
		long responseTime = responseFromApi.getTime();
		logger.info("The Response Time of the request is :---> " + responseTime);
		
		if(responseTime > 2000){
			logger.warn("Response Time is greater than 2000");
		}
		
		Assert.assertTrue(responseTime<2000);
		
	}
	
	@Test
	public void checkStatusLine(){
		
		logger.info("**************************Check Status Line*************************");
		String statusLine = responseFromApi.getStatusLine();
		logger.info("The Status Line is :---> " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	public void checkContentType(){
		
		logger.info("*************************Check Content Type**********************");
		String contentType = responseFromApi.header("Content-Type");
		logger.info("Content-Type is :----> " + contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
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
		
		logger.info("*********************Finished Executing TestGetAllEmployees***************************");
		
	}
	
	

}
