package com.spring.project.services.account;

import com.spring.project.dto.account.PersonDTO;
import com.spring.project.models.account.Person;
import com.spring.project.repositories.account.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public void registrationPerson(Person person) {

        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);

        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }


    public Optional<Person> checkUnique(PersonDTO personDTO) {

        Optional<Person> checkPerson = peopleRepository.findByUsername(personDTO.getUsername());
        return checkPerson;
    }
}
