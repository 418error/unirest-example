package com.channie;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class Service {
	
	static int SPARK_PORT;
	
	public static void main(String[] args) {
		
		if (StringUtils.isNotBlank(System.getenv("SPARK_PORT")))
			SPARK_PORT = Integer.parseInt(System.getenv("SPARK_PORT"));
    	else
    		SPARK_PORT = 4567; //Default Spark port
		port(SPARK_PORT);
		
        get("/health", (rq, rs) -> "Running...");
        
        get("/", (rq, rs) -> {
        	Map<String, Object> attributes = new HashMap<>();
        	rs.status(200);
            rs.type("text/html");
            return new HandlebarsTemplateEngine().render(new ModelAndView(attributes, "index.html"));
        });
        
        post("/user", (rq, rs) -> {
        	//
        	Person person = new Person();
        	person.setFirstName(rq.queryParams("firstName"));
        	person.setLastName(rq.queryParams("lastName"));
        	person.setAge(Integer.valueOf(rq.queryParams("age")));
        	        	
        	HttpResponse<String> insertResult = Unirest.post("http://localhost:"+SPARK_PORT+"/insert")
        			.header("accept", "application/json")
        	        .header("Content-Type", "application/json")
        	        .body(new Gson().toJson(person))
        	        .asString();
        	
        	Result result = new ObjectMapper().readValue(insertResult.getBody(), Result.class);
        	person.setStaffNo(result.getStaffNo());
        	
        	rs.status(200);
            rs.type("text/html");
            return new HandlebarsTemplateEngine().render(new ModelAndView(person, "result.html"));
        });
        
        
        ////////////////////////////////////////////////
        // This would be some sort of data access API //
        ////////////////////////////////////////////////        
        post("/insert", (rq, rs) -> {
        	// Attempt to map the JSON request object to the Event object
        	Person person = new ObjectMapper().readValue(rq.body(), Person.class);
        	//TODO: Do something with Person object... such as write to database?
        	// as a result of insert generate a staff no?
        	Result result = new Result();
        	result.setStaffNo(123456789);
        	rs.status(200);
 			return new Gson().toJson(result);
        });
 
	}

	@Data
	public static class Person {
	    private String firstName;
	    private String lastName;
	    private int age;
	    private int staffNo;
	    	
	    public boolean isValid() {
	        return StringUtils.isNotBlank(firstName) && 
	        		StringUtils.isNotBlank(lastName) && 
	        		age > 0;
	    }	
	}
	
	@Data
	public static class Result {
	    private int staffNo;
	}
}