package com.spring.project.services.app;

import com.spring.project.models.app.League;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.LeaguesRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class LeaguesService {

    private final LeaguesRepository leaguesRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public LeaguesService(LeaguesRepository leaguesRepository, TeamsRepository teamsRepository) {
        this.leaguesRepository = leaguesRepository;
        this.teamsRepository = teamsRepository;
    }


    public List<League> allLeagues() {
        return leaguesRepository.findAll();
    }


    public League league(String leagueName) {
        return leaguesRepository.findByLeagueName(leagueName).get();
    }


    public List<Team> teamsOfLeague(String leagueName) {
        League league = league(leagueName);
        List<Team> teams = league.getTeams();
        return teams;
    }


    public List<Team> allTeamsExceptSelected(String leagueName) {

        List<Team> allTeams = teamsRepository.findAll();
        List<Team> teamsOfLeague = teamsOfLeague(leagueName);

        List<Team> allTeamsExceptSelected = new ArrayList<>(allTeams);
        allTeamsExceptSelected.removeAll(teamsOfLeague);

        return allTeamsExceptSelected;
    }


    public List<Match> matchesOfLeague(String leagueName) {
        League league = league(leagueName);
        List<Match> matchesOfLeague = league.getMatches();
        return matchesOfLeague;
    }



    @Transactional
    public void createLeagueWhereSelectTeams(League league, List<String> teamNameList) {
        List<Team> teamList = new ArrayList<>();
        for (String teamName : teamNameList) {
            Team team = teamsRepository.findByTeamName(teamName).get();
            team.getLeagues().add(league);
            teamList.add(team);
        }
        league.setTeams(teamList);
        leaguesRepository.save(league);
    }


    @Transactional
    public void createLeagueWhereNotSelectTeams(League league) {
        leaguesRepository.save(league);
    }



    @Transactional
    public void editLeagueWhereEditTeams (League league, String leagueName, List<String> teamNameList) {
        League editLeague = leaguesRepository.findByLeagueName(leagueName).get();
        league.setLeagueId(editLeague.getLeagueId());

        List<Team> teamList = new ArrayList<>(editLeague.getTeams());
        for (String teamName : teamNameList) {
            Team team = teamsRepository.findByTeamName(teamName).get();
            if (!editLeague.getTeams().contains(team)) {
                team.getLeagues().add(league);
                teamList.add(team);
            } else if(editLeague.getTeams().contains(team)) {
                team.getLeagues().remove(league);
                teamList.remove(team);
            }
        }
        league.setTeams(teamList);
        leaguesRepository.save(league);
    }


    @Transactional
    public void editLeagueWhereNotEditTeams(League league, String leagueName) {
        League editLeague = leaguesRepository.findByLeagueName(leagueName).get();
        league.setLeagueId(editLeague.getLeagueId());
        league.setTeams(editLeague.getTeams());
        editLeague.getTeams().forEach(team -> team.getLeagues().add(league));
        leaguesRepository.save(league);
    }



    @Transactional
    public void deleteLeague(String leagueName) {
        League league = leaguesRepository.findByLeagueName(leagueName).get();
        leaguesRepository.delete(league);
    }
}