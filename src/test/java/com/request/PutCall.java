package com.request;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutCall
{
@Test
public void put()
{
	RestAssured.baseURI="https://reqres.in";
	RequestSpecification Httprequest=RestAssured.given();

	JSONObject jsonObject=new JSONObject();
	
	jsonObject.put(" Name", "xyz");
	jsonObject.put("job", "head coach");

	//aqdd the header
	Httprequest.header("Content-Type","application/json"); 
	
	// add the json to the body of request
	Httprequest.body(jsonObject.toJSONString());
	
	Response response = Httprequest.request(Method.PUT,"/api/users/2");
	//Response response1=Httprequest.put("/api/users/2");  
	Assert.assertEquals(response.getStatusCode(), 200);
	
	String respnseBody=response.getBody().asString();
	System.out.println(respnseBody); 
	
	
}
}
