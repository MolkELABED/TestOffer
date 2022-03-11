package org.project.TestOffer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.project.TestOffer.Exception.UserRegistrationException;
import org.project.TestOffer.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.project.TestOffer.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class TestOfferApplicationTests {
	
	 @Autowired
	private RegistrationService service;
    
	@Test
	public void should_register_user() {
		//arrange
		User user = new User(1L, "admin", LocalDate.of(2002, 5, 13), "france", "034895759", "female");
		service.register(user);
		
		User expectedUser;
		
		//act
		expectedUser = service.findUser(1L);
		
		//assert
		assertThat(user.getId()).isEqualTo(expectedUser.getId());
	}

	@Test
	public void should_throw_exception_when_user_is_not_an_adult() {
		//arrange
		User user = new User(1L, "admin", LocalDate.of(2020, 5, 13), "france", "034895759", "female");

		
		//assert
		Exception exception = assertThrows(UserRegistrationException.class, () -> {
			service.register(user);
		});
	}
	
	@Test
	public void should_throw_exception_when_user_is_not_a_french_resident() {
		//arrange
		User user = new User(1L, "admin", LocalDate.of(2000, 5, 13), "suede", "034895759", "female");

		
		//assert
		Exception exception = assertThrows(UserRegistrationException.class, () -> {
			service.register(user);
		});
	}

	@Test
	void contextLoads() {
	}

}
