package com.request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.testng.annotations.Test;

import com.qa.data.Json;


import groovyjarjarasm.asm.commons.Method;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;


public class GetCall
{
	@Test
	public void get() throws URISyntaxException, JsonParseException, JsonMappingException, IOException
	{
		
		RestAssured.baseURI="https://reqres.in";
		RequestSpecification Httprequest=RestAssured.given();
		
		//get request Authorization
		
		PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
		authScheme.setUserName("ajit");
		authScheme.setPassword("ajit380");
		
		
		RestAssured.authentication=authScheme;
		
		//response object
		Response response=Httprequest.get("/api/users?page");
		Httprequest.request(io.restassured.http.Method.GET, "/api/users?page");   
	   String responseBody = response.getBody().asString();
	 
	   
	   System.out.println(responseBody);
	   int statusCode=response.getStatusCode();
		Assert.assertEquals(200,statusCode);
		
		//headers
		Headers Allheader=response.headers();
		
		 for(Header headers:Allheader)
		 {
			 System.out.println(headers.getName()+"------->    "+headers.getValue());
		 }
		 System.out.println("-----------------------------------------------");
		 
		 
		 
		//json to java
		 ObjectMapper mapper=new ObjectMapper();
		Json user=new Json();
		Json userObject = mapper.readValue(responseBody,Json.class);
		
		System.out.println(userObject.getPer_page());
		System.out.println(userObject.getTotal());
		System.out.println(userObject.getTotal_pages());
		
		
		System.out.println(userObject.getData()[0].getFirst_name());
		System.out.println(userObject.getData()[1].getFirst_name());
		System.out.println(userObject.getData()[2].getFirst_name());
		System.out.println(userObject.getData()[3].getFirst_name());
		System.out.println(userObject.getData()[4].getFirst_name());
		System.out.println(userObject.getData()[5].getFirst_name());
		
		System.out.println(userObject.getData()[0].getEmail());
		System.out.println(userObject.getData()[1].getEmail());
		System.out.println(userObject.getData()[2].getEmail());
		System.out.println(userObject.getData()[3].getEmail());
		System.out.println(userObject.getData()[4].getEmail());
		System.out.println(userObject.getData()[5].getEmail());
	
		
		 // validating headers
		String contesntType= response.header("Content-Type");
		Assert.assertEquals(contesntType,"application/json; charset=utf-8");
		
		String Server= response.header("Server");
		Assert.assertEquals(Server,"cloudflare");
		 
		 // validating response body
		 
		 Assert.assertEquals(responseBody.contains("George"),true);
		 
		 //validation NOde
		 
		 JsonPath jsonpath=response.jsonPath();
		 System.out.println(jsonpath.get("data[1].id"));
		 System.out.println(jsonpath.get("data[0].email"));
		 
		 System.out.println(jsonpath.get("data[1].first_name"));
		 
		 System.out.println(jsonpath.get("total"));
		 System.out.println(jsonpath.get("data[2]. first_name"));	
		 
		 Assert.assertEquals(jsonpath.get("data[1].id"), 2);
		 Assert.assertEquals(jsonpath.get("data[1].first_name"), "Janet");
	
		 
	}
	

}
