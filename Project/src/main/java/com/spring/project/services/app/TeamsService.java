package com.spring.project.services.app;

import com.spring.project.models.app.Coach;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.CoachesRepository;
import com.spring.project.repositories.app.PlayersRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TeamsService {

    private final TeamsRepository teamsRepository;
    private final CoachesRepository coachesRepository;
    private final PlayersRepository playersRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository, CoachesRepository coachesRepository, PlayersRepository playersRepository) {
        this.teamsRepository = teamsRepository;
        this.coachesRepository = coachesRepository;
        this.playersRepository = playersRepository;
    }


    public Team team(String teamName){
        Team team = teamsRepository.findByTeamName(teamName).get();
        return team;
    }


    public List<Player> playersOfTeam(String teamName){
        Team team = team(teamName);
        List<Player> players = team.getPlayers();
        return players;
    }


    public Coach coachOfTeam(String teamName) {
        Team team = team(teamName);
        Coach coach = team.getCoach();
        return coach;
    }


    public List<Match> matchesOfTeam(String teamName) {
        Team team = team(teamName);
        List<Match> matchesOfTeam = team.getMatches();
        return matchesOfTeam;
    }


    public List<Team> allTeams() {
        return teamsRepository.findAll();
    }


    public List<Team> allTeamsWhereNotCoach() {
        List<Team> allTeams = allTeams();
        List<Team> allTeamsWhereNotCoach = allTeams.stream().filter(team -> team.getCoach() == null)
                .collect(Collectors.toList());
        return allTeamsWhereNotCoach;
    }


    public List<Team> findTeamOnStartingLetter(String startLetter) {
        return teamsRepository.findByTeamNameStartingWith(startLetter);
    }



    @Transactional
    public void createTeamWithoutCoachAndPlayers(Team team, MultipartFile teamImage) throws IOException {
        if (teamImage != null) {
            team.setTeamImageName(teamImage.getOriginalFilename());
            team.setTeamImage(teamImage.getBytes());
        }
        teamsRepository.save(team);
    }

    @Transactional
    public void createTeamWithCoachAndOrPlayers(Team team, String coachName, List<String> playerNameList,
                                                MultipartFile teamImage) throws IOException {

        if (playerNameList != null) {
            List<Player> playersOfTeam = new ArrayList<>();
            for (String playerName : playerNameList) {
                Player player = playersRepository.findByPlayerName(playerName).get();
                player.getTeams().add(team);
                playersOfTeam.add(player);
            }
            team.setPlayers(playersOfTeam);
        }

        if (teamImage != null) {
            team.setTeamImageName(teamImage.getOriginalFilename());
            team.setTeamImage(teamImage.getBytes());
        }

        teamsRepository.save(team);

        if (!coachName.equals("null")) {
            Coach coach = coachesRepository.findByCoachName(coachName).get();
            coach.setTeam(team);
            team.setCoach(coach);
        }
    }



    @Transactional
    public void editTeamWhereNotEditCoachAndPlayers(Team team, String teamName, MultipartFile teamImage,
                                                    String teamImageName) throws IOException {
        Team editTeam = teamsRepository.findByTeamName(teamName).get();
        team.setTeamId(editTeam.getTeamId());

        if (editTeam.getPlayers() != null) {
            team.setPlayers(editTeam.getPlayers());
        }

        if (editTeam.getCoach() != null) {
            team.setCoach(editTeam.getCoach());
            editTeam.getCoach().setTeam(team);
        }

        if (teamImage != null) {
            team.setTeamImage(teamImage.getBytes());
            if (teamImage.getOriginalFilename().equals("")) {
                team.setTeamImageName(teamImageName);
            } else {
                team.setTeamImageName(teamImage.getOriginalFilename());
            }
        }

        teamsRepository.save(team);
    }


    @Transactional
    public void editTeamWhereEditCoachAndOrPlayers(Team team, String teamName,
                                                   List<String> playerNameList, String coachName,
                                                   MultipartFile teamImage, String teamImageName) throws IOException {
        Team editTeam = teamsRepository.findByTeamName(teamName).get();
        team.setTeamId(editTeam.getTeamId());

        if (playerNameList != null) {
            List<Player> playersOfTeam = new ArrayList<>(editTeam.getPlayers());
            for (String playerName : playerNameList) {
                Player player = playersRepository.findByPlayerName(playerName).get();
                if (!editTeam.getPlayers().contains(player)) {
                    player.getTeams().add(team);
                    playersOfTeam.add(player);
                } else if (editTeam.getPlayers().contains(player)) {
                    player.getTeams().remove(team);
                    playersOfTeam.remove(player);
                }

            }
            team.setPlayers(playersOfTeam);
        }

        if (!coachName.equals("null")) {
            Coach coach = coachesRepository.findByCoachName(coachName).get();
            coach.setTeam(team);
            team.setCoach(coach);
        } else if(coachName.equals("null") & editTeam.getCoach() != null) {
            Coach coach = editTeam.getCoach();
            team.setCoach(coach);
        }

        if (teamImage != null) {
            team.setTeamImage(teamImage.getBytes());
            if (teamImage.getOriginalFilename().equals("")) {
                team.setTeamImageName(teamImageName);
            } else {
                team.setTeamImageName(teamImage.getOriginalFilename());
            }
        }

        teamsRepository.save(team);
    }


    @Transactional
    public void deleteTeam(String teamName) {
        Team team = teamsRepository.findByTeamName(teamName).get();
        List<Player> players = team.getPlayers();
        for (Player player : players) {
            player.getTeams().remove(team);
        }
        teamsRepository.delete(team);
    }
}
