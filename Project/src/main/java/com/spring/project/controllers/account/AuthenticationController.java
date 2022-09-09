package com.spring.project.controllers.account;

import com.spring.project.dto.account.PersonDTO;
import com.spring.project.models.account.Person;
import com.spring.project.services.account.RegistrationService;
import com.spring.project.util.validation.PersonValid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final RegistrationService registrationService;
    private final PersonValid personValid;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationController(RegistrationService registrationService, PersonValid personValid, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.personValid = personValid;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/login")
    public String login() {
        return "/account/auth/login";
    }


    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") PersonDTO personDTO) {
        return "/account/auth/registration";
    }


    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid PersonDTO personDTO,
                                      BindingResult bindingResult) {
        personValid.validate(personDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/account/auth/registration";
        }
        registrationService.registrationPerson(convertToPerson(personDTO));
        return "redirect:/auth/login";
    }


    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
