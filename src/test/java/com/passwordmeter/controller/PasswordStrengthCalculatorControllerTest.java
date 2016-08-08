package com.passwordmeter.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.passwordmeter.PasswordMeterApplication;
import com.passwordmeter.model.Complexity;

@SuppressWarnings("deprecation")
@SpringApplicationConfiguration(classes = {PasswordMeterApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest("server.port=0")
public class PasswordStrengthCalculatorControllerTest {
    
    @Value("${local.server.port}")
    private  int port;
    
    @Before
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    public void shouldRequestTheScoreThruWebService() {
        String password = "P@55w0rd";
        
        JsonPath path = RestAssured.given()
            .contentType(ContentType.JSON)
            .get("/calculateScore/{password}", password)
            .andReturn().jsonPath();
        
        int score = path.getObject("strength", int.class);
        String complexity = path.getObject("complexity", String.class);
        
        Assert.assertEquals(Complexity.VERY_STRONG.getAlias(), complexity);
        Assert.assertEquals(86, score);
    }
}