package com.spring.project.services.app;

import com.spring.project.models.app.League;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.LeaguesRepository;
import com.spring.project.repositories.app.MatchesRepository;
import com.spring.project.repositories.app.PlayersRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class MatchesService {

    private final MatchesRepository matchesRepository;
    private final LeaguesRepository leaguesRepository;
    private final PlayersRepository playersRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public MatchesService(MatchesRepository matchesRepository, LeaguesRepository leaguesRepository, PlayersRepository playersRepository, TeamsRepository teamsRepository) {
        this.matchesRepository = matchesRepository;
        this.leaguesRepository = leaguesRepository;
        this.playersRepository = playersRepository;
        this.teamsRepository = teamsRepository;
    }


    public List<Match> allMatches() {
        return matchesRepository.findAll();
    }


    public Match getMatch(int matchId) {
        Optional<Match> match = matchesRepository.findByMatchId(matchId);
        return match.get();
    }


    public List<Match> h2h(int matchId) {
        Match match = getMatch(matchId);
        List<Match> matches =
                matchesRepository.findByNameTeamOneAndNameTeamTwo(match.getNameTeamOne(), match.getNameTeamTwo());
        return matches;
    }


    public List<Team> teamsOfMatch(int matchId) {

        List<Team> teamsOfMatch = new ArrayList<>();
        Match match = getMatch(matchId);

        Team team1 = teamsRepository.findByTeamName(match.getNameTeamOne()).get();
        Team team2 = teamsRepository.findByTeamName(match.getNameTeamTwo()).get();

        teamsOfMatch.add(team1);
        teamsOfMatch.add(team2);

        return teamsOfMatch;
    }


    public List<Player> playersOfMatch(int matchId) {

        List<Team> teamsOfMatch = teamsOfMatch(matchId);

        List<Player> playersTeam1 = teamsOfMatch.get(0).getPlayers();
        List<Player> playersTeam2 = teamsOfMatch.get(1).getPlayers();

        List<Player> playersOfMatch = Stream.concat(playersTeam1.stream(), playersTeam2.stream()).toList();

        return playersOfMatch;
    }


    @Transactional
    public void createMatch(Match match, String leagueName, String teamName,
                            List<String> namesOfPlayers) {

        enrichProperty(match, teamName);

        League league = leaguesRepository.findByLeagueName(leagueName).get();
        league.getMatches().add(match);
        match.setLeague(league);

        String[] teamNames = teamName.split(",");
        List<Team> teamsOfMatch = new ArrayList<>();
        for (String name : teamNames) {
            Team team = teamsRepository.findByTeamName(name).get();
            team.getMatches().add(match);
            teamsOfMatch.add(team);
        }
        match.setTeams(teamsOfMatch);

        if (namesOfPlayers != null) {
            List<Player> playersOfMatch = new ArrayList<>();
            for (String nameOfPlayer : namesOfPlayers) {
                Player player = playersRepository.findByPlayerName(nameOfPlayer).get();
                player.getMatches().add(match);
                playersOfMatch.add(player);
            }
            match.setPlayers(playersOfMatch);
        }

        matchesRepository.save(match);
    }


    private void enrichProperty(Match match, String teamName) {
        String[] teamNames = teamName.split(",");
        match.setNameTeamOne(teamNames[0]);
        match.setNameTeamTwo(teamNames[1]);
    }
}
