package org.project.TestOffer.Exception;

public class UserRegistrationException extends RuntimeException {
	
	public static final String USER_NOT_FOUND = "Customer not found.";
	public static final String INVALIDE_COUNTRY_INPUT = "Invalide input: The user must be a french resident.";
	public static final String INVALIDE_AGE_INPUT = "Invalide input: The user must be an adult.";
	public static final String INVALIDE_INPUT = "Invalide input.";
	
    public UserRegistrationException(String message) { 
        super(message);
        }
}
