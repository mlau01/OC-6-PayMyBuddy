package com.paymybuddy.puddy.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	@BeforeAll
	public static void setUp(@Autowired UserRepository userRepository) {
		User user1 = new User();
		user1.setFirstName("Tran");
		user1.setLastName("Mitter");
		user1.setPassword("test");
		user1.setCurrency(CURRENCY.EUR);
		user1.setBalance(0.);
		user1.setEmail("user1@mail.com");
		userRepository.save(user1);
	}
	@AfterAll
	public static void clear(@Autowired UserRepository userRepository) {
		userRepository.deleteAll();
	}
	
	@Test
	public void homeSecurityTest_shouldReturnRedirection() throws Exception {
		mockMvc.perform(get("/home"))
		.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	public void transferSecurityTest_shouldReturnRedirection() throws Exception {
		mockMvc.perform(get("/transfer"))
		.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	public void profileSecurityTest_shouldReturnRedirection() throws Exception {
		mockMvc.perform(get("/profile"))
		.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	public void contactSecurityTest_shouldReturnRedirection() throws Exception {
		mockMvc.perform(get("/contact"))
		.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
	}
	
	@Test
	@WithUserDetails("user1@mail.com")
	public void homeSecurityTestAuthenticatedUser_shouldReturnStatusOk() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk());
	}

}
