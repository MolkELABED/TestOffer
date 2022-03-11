package org.project.TestOffer.Controller;

import org.project.TestOffer.Exception.UserRegistrationException;
import org.project.TestOffer.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.project.TestOffer.entity.User;

@RestController
public class UserController {
	
	@Autowired(required=false)
	RegistrationService registrationService;
	
	//register a user
	@PostMapping(value="/user")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			return new ResponseEntity<>(registrationService.register(user), HttpStatus.OK);
		} catch(UserRegistrationException ure) {
			return new ResponseEntity<>(ure.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
