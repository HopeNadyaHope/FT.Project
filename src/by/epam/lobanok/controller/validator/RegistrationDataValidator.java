package by.epam.lobanok.controller.validator;

import by.epam.lobanok.entity.RegistrationData;

public class RegistrationDataValidator {
	private static final RegistrationDataValidator instance = new RegistrationDataValidator();
	
	private RegistrationDataValidator() {}
	
	public static RegistrationDataValidator getInstance() {
		return instance;
	}
	
	public boolean validate(RegistrationData regData) {
		return true;
	}
}
