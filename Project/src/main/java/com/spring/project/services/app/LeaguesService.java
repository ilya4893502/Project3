package com.spring.project.services.app;

import com.spring.project.models.app.League;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.LeaguesRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


    public String convertToImageLeague(String leagueName) throws IOException {
        byte[] encodeBase64 = Base64.encode(leaguesRepository.findByLeagueName(leagueName).get().getLeagueImage());
        String base64Encoded = new String(encodeBase64, "UTF-8");
        return base64Encoded;
    }


    public List<Team> teamsOfLeague(String leagueName) {
        League league = league(leagueName);
        List<Team> teams = league.getTeams().stream().sorted(new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                if (o1.getNumberOfPoints() < o2.getNumberOfPoints()) {
                    return 1;
                } else if(o1.getNumberOfPoints() > o2.getNumberOfPoints()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }).collect(Collectors.toList());

        int position = 1;
        for (Team team : teams) {
            team.setPositionOnTable(position);
            position++;
        }
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
    public void createLeagueWhereSelectTeams(League league, List<String> teamNameList,
                                             MultipartFile leagueImage) throws IOException {
        List<Team> teamList = new ArrayList<>();
        for (String teamName : teamNameList) {
            Team team = teamsRepository.findByTeamName(teamName).get();
            team.getLeagues().add(league);
            teamList.add(team);
        }
        league.setTeams(teamList);
        if (leagueImage != null) {
            league.setLeagueImageName(leagueImage.getOriginalFilename());
            league.setLeagueImage(leagueImage.getBytes());
        }
        leaguesRepository.save(league);
    }


    @Transactional
    public void createLeagueWhereNotSelectTeams(League league, MultipartFile leagueImage) throws IOException {
        if (leagueImage != null) {
            league.setLeagueImageName(leagueImage.getOriginalFilename());
            league.setLeagueImage(leagueImage.getBytes());
        }
        leaguesRepository.save(league);
    }



    @Transactional
    public void editLeagueWhereEditTeams (League league, String leagueName, List<String> teamNameList,
                                          MultipartFile leagueImage, String leagueImageName) throws IOException {
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

        if (leagueImage != null) {
            league.setLeagueImage(leagueImage.getBytes());
            if (leagueImage.getOriginalFilename().equals("")) {
                league.setLeagueImageName(leagueImageName);
            } else {
                league.setLeagueImageName(leagueImage.getOriginalFilename());
            }
        }

        leaguesRepository.save(league);
    }


    @Transactional
    public void editLeagueWhereNotEditTeams(League league, String leagueName, MultipartFile leagueImage,
                                            String leagueImageName) throws IOException {
        League editLeague = leaguesRepository.findByLeagueName(leagueName).get();
        league.setLeagueId(editLeague.getLeagueId());
        league.setTeams(editLeague.getTeams());
        editLeague.getTeams().forEach(team -> team.getLeagues().add(league));
        if (leagueImage != null) {
            league.setLeagueImage(leagueImage.getBytes());
            if (leagueImage.getOriginalFilename().equals("")) {
                league.setLeagueImageName(leagueImageName);
            } else {
                league.setLeagueImageName(leagueImage.getOriginalFilename());
            }
        }
        leaguesRepository.save(league);
    }



    @Transactional
    public void deleteLeague(String leagueName) {
        League league = leaguesRepository.findByLeagueName(leagueName).get();
        leaguesRepository.delete(league);
    }
}
