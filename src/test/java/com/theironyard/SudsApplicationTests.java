package com.theironyard;

import com.theironyard.services.BeerRepository;
import com.theironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SudsApplicationTests {

	@Autowired
	UserRepository users;

	@Autowired
	BeerRepository beers;

	@Autowired
	WebApplicationContext wac;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void aTestLogin() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
				.param("name", "Admin")
				.param("password", "hunter2")
		);

		Assert.assertTrue(users.findFirstByName("Admin")  != null);
	}

}
