package com.example.shoppinglistapp;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ShoppingListAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void notesListTest() throws Exception {
		this.mockMvc.perform(get("/index").cookie(new Cookie("JSESSIONID", "A3967D16FCEAD5E938C318FBEE9BAD54")))
				.andDo(print())
				.andExpect(xpath("//div[@id='container']/div").nodeCount(4));
	}

	@Test
	void notesItemsTest() throws Exception {
		this.mockMvc.perform(get("/index").cookie(new Cookie("JSESSIONID", "A3967D16FCEAD5E938C318FBEE9BAD54")))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.content().string(containsString("milk")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("sweets")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("coffee")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("cookies")));
	}

	@Test
	void contextLoads() throws Exception {
	}

}
