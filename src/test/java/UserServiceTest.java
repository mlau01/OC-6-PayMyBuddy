import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymybuddy.puddy.exceptions.EmailAlreadyExistsException;
import com.paymybuddy.puddy.exceptions.PasswordNotMatchException;
import com.paymybuddy.puddy.model.UserForm;
import com.paymybuddy.puddy.repository.ContactRepository;
import com.paymybuddy.puddy.repository.UserRepository;
import com.paymybuddy.puddy.service.IUserService;
import com.paymybuddy.puddy.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	private IUserService userService;
	
	@Mock
	UserRepository userRepo;
	
	@Mock
	ContactRepository contactRepo;
	
	@BeforeEach
	public void setUpPerTest() {
		userService = new UserServiceImpl(userRepo, contactRepo);
	}

	@Test
	public void addUserTest_shouldThrowPasswordException() throws PasswordNotMatchException, EmailAlreadyExistsException {
		UserForm userForm = new UserForm();
		userForm.setFirstName("Eric");
		userForm.setLastName("Yoto");
		userForm.setEmail("eric.yoto@gmail.com");
		userForm.setPassword("test");
		userForm.setPassword_confirm("nono");
		
		assertThrows(PasswordNotMatchException.class, () -> userService.addNewUser(userForm));
	}
}
