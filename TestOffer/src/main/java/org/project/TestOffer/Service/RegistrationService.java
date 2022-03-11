package org.project.TestOffer.Service;

import java.time.LocalDate;
import java.time.Period;

import static org.project.TestOffer.Exception.UserRegistrationException.USER_NOT_FOUND;
import static org.project.TestOffer.Exception.UserRegistrationException.INVALIDE_COUNTRY_INPUT;
import static org.project.TestOffer.Exception.UserRegistrationException.INVALIDE_AGE_INPUT;
import static org.project.TestOffer.Exception.UserRegistrationException.INVALIDE_INPUT;

import org.project.TestOffer.Exception.UserRegistrationException;
import org.project.TestOffer.Repository.UserRepository;
import org.project.TestOffer.entity.Country;
import org.project.TestOffer.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {
	
	@Autowired
	UserRepository userRepository;
	
	//Register a user
	public User register(User user) {
		int age = Period.between(user.getBirthdate(), LocalDate.now()).getYears();
		
		if (user.getName() == null || user.getCountryOfResidence() == null || user.getBirthdate() == null) {
			throw new UserRegistrationException(INVALIDE_INPUT);
		}
		if (age < 18) {
			throw new UserRegistrationException(INVALIDE_AGE_INPUT);
		} else if (!(user.getCountryOfResidence().toUpperCase().equals(Country.FRANCE.toString()))) {
			throw new UserRegistrationException(INVALIDE_COUNTRY_INPUT);
		}
		
	    return userRepository.save(user);
	}

	//Find the details of registered user
	public User userDetails(Long userId) {
	    return userRepository.findById(userId)
	            .orElseThrow(() -> new UserRegistrationException(USER_NOT_FOUND));
	}
	
}
