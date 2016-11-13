package com.theironyard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.entities.User;
import com.theironyard.services.BeerRepository;
import com.theironyard.services.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Table;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void ATestCreateUser() throws Exception {
		User user = new User("Steven", "hunter2");
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
				.content(json)
				.contentType("application/json")
		);

		Assert.assertTrue(users.count() == 4);
	}

	@Test
	public void bTestLogin() throws Exception {
		User user = new User("Steven", "hunter2");
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/login")
				.content(json)
				.contentType("application/json")
		);

		Assert.assertTrue(users.findFirstByName("Steven")  != null);
	}

}
