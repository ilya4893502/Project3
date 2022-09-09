package com.spring.project.services.app;

import com.spring.project.dto.app.CoachDTO;
import com.spring.project.dto.app.PlayerDTO;
import com.spring.project.models.app.Coach;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.CoachesRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CoachesService {

    private final CoachesRepository coachesRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public CoachesService(CoachesRepository coachesRepository, TeamsRepository teamsRepository) {
        this.coachesRepository = coachesRepository;
        this.teamsRepository = teamsRepository;
    }


    public List<Coach> allCoaches() {
        return coachesRepository.findAll();
    }


    public List<Coach> allFreeCoaches() {
        return coachesRepository.findAll().stream()
                .filter(coach -> coach.getTeam() == null).collect(Collectors.toList());
    }


    public Coach coach(String coachName) {
        Coach coach = coachesRepository.findByCoachName(coachName).get();
        return coach;
    }


    public Coach coachOfTeam(String teamName) {
        Team team = teamsRepository.findByTeamName(teamName).get();
        Coach coachOfTeam = team.getCoach();
        return coachOfTeam;
    }


    public List<Team> allTeamsExceptTeamsOfCoach(String teamName) {
        Coach coachOfTeam = coachOfTeam(teamName);
        List <Team> allTeamsExceptTeamsOfCoach = new ArrayList<>(teamsRepository.findAll());
        allTeamsExceptTeamsOfCoach.remove(coachOfTeam);
        return allTeamsExceptTeamsOfCoach;
    }


    public Team teamOfCoach(String coachName) {
        Coach coach = coachesRepository.findByCoachName(coachName).get();
        Team team = coach.getTeam();
        return team;
    }



    @Transactional
    public void createCoach(Coach coach, String teamName) {
        if (!teamName.equals("null")) {
            Team team = teamsRepository.findByTeamName(teamName).get();
            coach.setTeam(team);
            team.setCoach(coach);
        }
        coachesRepository.save(coach);
    }


    @Transactional
    public void editCoach(Coach coach, String coachName, String teamName) {
        Coach editCoach = coachesRepository.findByCoachName(coachName).get();
        coach.setCoachId(editCoach.getCoachId());

        if (editCoach.getTeam() != null) {
            if (teamName.equals("null")) {
                coach.setTeam(null);
                editCoach.getTeam().setCoach(null);
            } else if (!teamName.equals(editCoach.getTeam().getTeamName())) {
                Team team = teamsRepository.findByTeamName(teamName).get();
                editCoach.getTeam().setCoach(null);
                editCoach.setTeam(null);
                coach.setTeam(team);
                team.setCoach(coach);
            } else {
                Team team = teamsRepository.findByTeamName(teamName).get();
                coach.setTeam(team);
                team.setCoach(coach);
            }
        } else if (editCoach.getTeam() == null) {
            if (teamName.equals("null")) {
                return;
            } else if (!teamName.equals("null")) {
                Team team = teamsRepository.findByTeamName(teamName).get();
                coach.setTeam(team);
                team.setCoach(coach);
            }
        }

        coachesRepository.save(coach);
    }


    @Transactional
    public void deleteCoach(String coachName) {
        Coach deleteCoach = coachesRepository.findByCoachName(coachName).get();
        coachesRepository.delete(deleteCoach);
    }
}
