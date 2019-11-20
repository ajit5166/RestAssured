 package com.request;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.qa.data.UserData;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostCall 
{
@Test
void PostRequest() throws URISyntaxException, JsonGenerationException, JsonMappingException, IOException
{
	RestAssured.baseURI="https://reqres.in";
	RequestSpecification Httprequest=RestAssured.given();
	
//     JSONObject jsonObject=new JSONObject();
//     jsonObject.put("anil","24");
     
     
	Httprequest.header("Content-Type","application/json"); 
	
	org.codehaus.jackson.map.ObjectMapper mapper =new org.codehaus.jackson.map.ObjectMapper();
	UserData user=new UserData("Abhi","24");

	ArrayList array=new ArrayList ();
	
	array.add(new UserData("amit","24"));
	array.add(new UserData("anil","26"));
	array.add(new UserData("Rahul","28"));
	
	
	//mapper.writeValue(new File("C://Users//Interview//eclipse-workspace//RestAssured//src//test//java//com//qa//data//user.json"),array);
	
	
	// add the json to the body of request
	//Httprequest.body(jsonObject.toJSONString());
	
	
	//object to json

	String jsonString=mapper.writeValueAsString(array);
	String jsonString1=mapper.writeValueAsString(user);
	
	System.out.println(jsonString);
	System.out.println(jsonString1);
	
	Response response = Httprequest.post("/api/users");
	
	
	String responseBody=response.getBody().asString();
	System.out.println(responseBody);
	

	int statusCode=response.getStatusCode();
	Assert.assertEquals(201,statusCode);
	System.out.println(response.statusLine());
	  
	System.out.println(response.jsonPath().get("SuccessCode"));
}
}
