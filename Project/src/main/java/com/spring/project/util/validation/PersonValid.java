package com.spring.project.util.validation;

import com.spring.project.dto.account.PersonDTO;
import com.spring.project.models.account.Person;
import com.spring.project.services.account.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValid implements Validator {

    private final RegistrationService registrationService;

    @Autowired
    public PersonValid(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        PersonDTO personDTO = (PersonDTO) target;

        if (registrationService.checkUnique(personDTO).isPresent()) {
            errors.rejectValue("username", "", "Такое имя уже существует!");
        }


        if (!personDTO.getPassword().equals(personDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "", "Пароли не совпадают!");
        }

    }
}
