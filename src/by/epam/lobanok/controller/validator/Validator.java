package by.epam.lobanok.controller.validator;

import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.RegistrationData;

public class Validator {
	private static final Validator instance = new Validator();
	private final String EMAIL_REGEX = "^[.a-z0-9_-]+@[a-z0-9-]+\\.[a-zA-Z]{2,6}$";
	
	private Validator() {}
	
	public static Validator getInstance() {
		return instance;
	}
	
	public boolean validateRegistrationData(RegistrationData regData) {
		int age = regData.getAge();
		String email = regData.getEmail();
		
		if((regData.getPassword() == null) || (regData.getLogin() == null)
				||(regData.getName() == null) || (regData.getSurname() == null)
				||(regData.getSex() == null) || (regData.getRole() == null)
				||(age < 14) || (age >  100) || (email == null)) {
			return false;
		}
		return regData.getEmail().matches(EMAIL_REGEX);
	}
	
	public boolean validateEntranceData(EntranceData entrData) {
		if((entrData.getLogin() == null) || (entrData.getPassword() == null)) {
			return false;
		}
		return true;
	}
}
