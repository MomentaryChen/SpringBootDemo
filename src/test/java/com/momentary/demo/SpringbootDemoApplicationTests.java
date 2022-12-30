package com.momentary.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(String.format("\"=================== getProduct Accessed By %s===================\"", "name"));
	}

}
