

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidAmountException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.TransferRepository;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.TransferServiceImpl;
import com.paymybuddy.puddy.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransferServiceTest {
	
	@Mock
	UserServiceImpl userService;
	
	@Mock
	TransferRepository transferRepo;
	
	ITransferService transferService;
	
	@BeforeEach
	public void setUpPerTest() {
		transferService = new TransferServiceImpl(userService, transferRepo);
	}
	
	@Test
	public void doTransferWithInvalidAmountTest_shouldThrowException() throws NotEnoughCreditException, InvalidAmountException {
		
		assertThrows(InvalidAmountException.class, () -> transferService.doTransfer("matt.lau@gmail.com", "yann.lau@gmail.com", 0, CURRENCY.EUR, "test"));
	}
	
	@Test
	public void doTransferWithGreaterAmountTest_shouldThrowException() throws NotEnoughCreditException, InvalidAmountException {
		User user = new User();
		user.setBalance(0.);
		when(userService.getUserByMail(anyString())).thenReturn(user);
		
		assertThrows(NotEnoughCreditException.class, () -> transferService.doTransfer("matt.lau@gmail.com", "yann.lau@gmail.com", 9999999, CURRENCY.EUR, "test"));
		verify(userService, Mockito.times(1)).getUserByMail(anyString());
	}

}
