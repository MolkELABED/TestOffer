package org.project.TestOffer.Service;

import java.time.LocalDate;
import java.time.Period;

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
	
	//Save a user
	public User register(User user) {
		int age = Period.between(user.getBirthdate(), LocalDate.now()).getYears();
		
		if (age < 18) {
			throw new UserRegistrationException("Invalide input: The user must be an adult");
		} else if (!(user.getCountryOfResidence().toUpperCase().equals(Country.FRANCE.toString()))) {
			throw new UserRegistrationException("Invalide input: The user must be a french resident");
		}
		
	    return userRepository.save(user);
	}
	
	public User findUser(Long userId) {
		return userRepository.findById(userId)
	            .orElseThrow(() -> new UserRegistrationException("User not found for this id ::" + userId));
	}
	
}
