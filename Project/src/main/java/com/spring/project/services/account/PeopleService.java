package com.spring.project.services.account;

import com.spring.project.models.account.Person;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.account.PeopleRepository;
import com.spring.project.repositories.app.TeamsRepository;
import com.spring.project.util.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, TeamsRepository teamsRepository) {
        this.peopleRepository = peopleRepository;
        this.teamsRepository = teamsRepository;
    }


    public Person getPerson(int id) {
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }


//    @Transactional
//    public void addTeamForPerson (int id, Team team) {
//
//        Person person = getPerson(id);
//        Team team1 = teamsRepository.findByTeamNameStartingWith(team.getTeamName());
//
//        person.getTeams().add(team);
//        team.getPeople().add(person);
//    }
}
