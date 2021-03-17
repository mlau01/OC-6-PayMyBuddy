import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.thymeleaf.engine.ModelBuilderTemplateHandler;

import com.paymybuddy.puddy.controller.MainController;
import com.paymybuddy.puddy.enums.CURRENCY;
import com.paymybuddy.puddy.exceptions.InvalidArgumentException;
import com.paymybuddy.puddy.exceptions.NotEnoughCreditException;
import com.paymybuddy.puddy.form.UserForm;
import com.paymybuddy.puddy.model.User;
import com.paymybuddy.puddy.repository.BillingRepository;
import com.paymybuddy.puddy.repository.TransferRepository;
import com.paymybuddy.puddy.service.ITransferService;
import com.paymybuddy.puddy.service.IUserService;
import com.paymybuddy.puddy.service.TransferServiceImpl;
import com.paymybuddy.puddy.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
	
	private MainController controller;
	
	@Mock
	IUserService userService;
	
	@Mock
	ITransferService transferService;
	
	@Mock
	Model model;
	
	@Mock
	Principal principal;
	
	@BeforeEach
	public void setUpPerTest() {
		controller = new MainController(userService, transferService);
	}
	
	
	@Test
	@Disabled
	public void postProfileTest_shouldReturn() {
		UserForm userForm = new UserForm();
		BindingResult bindingResult = BindingResultUtils.getBindingResult(Maps.newHashMap("error", "error"), "model");
		controller.postProfile(userForm, bindingResult, null, model);
	}

}
