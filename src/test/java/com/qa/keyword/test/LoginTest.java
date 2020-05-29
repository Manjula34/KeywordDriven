package com.qa.keyword.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.qa.keyword.engine.KeywordEngine;

public class LoginTest {
	public KeywordEngine kweng;
	@Test
	public void logintest() throws IOException {
		kweng = new KeywordEngine();
		kweng.startExecution("login");
		
	}

}
