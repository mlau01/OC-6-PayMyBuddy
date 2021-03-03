package com.paymybuddy.puddy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.puddy.model.Versement;
import com.paymybuddy.puddy.repository.VersementRepository;

@SpringBootTest
public class VersementRepositoryTest {
	
	@Autowired
	private VersementRepository versementRepo;
	
	@Test
	public void testGetFirstEntry_shouldReturnFirstEntry() {
		Optional<Versement> firstVersement = versementRepo.findById(1);
		Versement versement = firstVersement.get();
		
		assertEquals(29.99, versement.getAmount());
	}

}
