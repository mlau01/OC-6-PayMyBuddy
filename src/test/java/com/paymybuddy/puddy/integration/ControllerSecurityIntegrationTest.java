package com.paymybuddy.puddy.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.model.Transfer;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.UserRepository;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ControllerSecurityIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	UserRepository userRepository;
	
	@Mock
	IUserService userService;
	
	@Mock
	ITransferService transferService;
	
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
	public void loginAccessTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk());
	}
	
	@Test
	public void registerAccessTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@WithMockUser(username = "user1@mail.com", password = "test")
	public void homeSecurityTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/home")).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@WithMockUser(username = "user1@mail.com", password = "test")
	public void transferSecurityTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/transfer")).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@WithMockUser(username = "user1@mail.com", password = "test")
	public void profileSecurityTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/profile")).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@WithMockUser(username = "user1@mail.com", password = "test")
	public void contactSecurityTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(get("/contact")).andExpect(status().isOk());
	}
	
	@Test
	public void transferPostSecurityTest_shouldGrantAccess() throws Exception {
		mockMvc.perform(post("/transfer")).andExpect(status().isForbidden());

	}
}
