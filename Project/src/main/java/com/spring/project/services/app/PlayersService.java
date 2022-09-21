package com.spring.project.services.app;

import com.spring.project.dto.app.PlayerDTO;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.repositories.app.PlayersRepository;
import com.spring.project.repositories.app.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PlayersService {

    private final PlayersRepository playersRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public PlayersService(PlayersRepository playersRepository, TeamsRepository teamsRepository) {
        this.playersRepository = playersRepository;
        this.teamsRepository = teamsRepository;
    }


    public List<Player> allPlayers() {
        return playersRepository.findAll();
    }


    public Player player(String playerName) {
        Player player = playersRepository.findByPlayerName(playerName).get();
        return player;
    }


    public List<Player> playersOfTeam(String teamName) {
        Team team = teamsRepository.findByTeamName(teamName).get();
        List<Player> playersOfTeam = team.getPlayers();
        return playersOfTeam;
    }


    public List<Player> allPlayersExceptSelected(String teamName) {
        List<Player> allPlayers = allPlayers();
        List<Player> playersOfTeam = playersOfTeam(teamName);

        List<Player> allPlayersExceptSelected = new ArrayList<>(allPlayers);
        allPlayersExceptSelected.removeAll(playersOfTeam);

        return allPlayersExceptSelected;
    }


    public List<Team> teamsOfPlayer(String playerName) {
        Player player = playersRepository.findByPlayerName(playerName).get();
        List<Team> teamsOfPlayer = player.getTeams();
        return teamsOfPlayer;
    }


    public List<Team> allTeamsExceptTeamsOfPlayer(String playerName) {
        List<Team> teamsOfPlayer = teamsOfPlayer(playerName);
        List <Team> allTeamsExceptTeamsOfPlayer = new ArrayList<>(teamsRepository.findAll());
        allTeamsExceptTeamsOfPlayer.removeAll(teamsOfPlayer);
        return allTeamsExceptTeamsOfPlayer;
    }



    @Transactional
    public void createPlayer(Player player, String teamName, MultipartFile playerImage) throws IOException {
        if (!teamName.equals("null")) {
            Team team = teamsRepository.findByTeamName(teamName).get();
            player.setTeams(new ArrayList<>(List.of(team)));
            team.getPlayers().add(player);
        }
        if (playerImage != null) {
            player.setPlayerImageName(playerImage.getOriginalFilename());
            player.setPlayerImage(playerImage.getBytes());
        }
        playersRepository.save(player);
    }


    @Transactional
    public void editPlayer(Player player, String playerName, String teamName, MultipartFile playerImage,
                           String playerImageName) throws IOException {
        Player editPlayer = playersRepository.findByPlayerName(playerName).get();
        player.setPlayerId(editPlayer.getPlayerId());

        Optional<Team> getTeam = teamsRepository.findByTeamName(teamName);

        if (editPlayer.getTeams() != null) {
            if (teamName.equals("null")) {
                editPlayer.getTeams().clear();
            } else if (!editPlayer.getTeams().contains(getTeam)) {
                Team team = teamsRepository.findByTeamName(teamName).get();
                editPlayer.getTeams().clear();
                player.setTeams(new ArrayList<>(List.of(team)));
                team.getPlayers().add(player);
            } else {
                Team team = teamsRepository.findByTeamName(teamName).get();
                player.getTeams().add(team);
                team.getPlayers().add(player);
            }
        } else if (editPlayer.getTeams() == null) {
            if (teamName.equals("null")) {
                return;
            } else if (!teamName.equals("null")) {
                Team team = teamsRepository.findByTeamName(teamName).get();
                player.setTeams(new ArrayList<>(List.of(team)));
                team.getPlayers().add(player);
            }
        }

        if (playerImage != null) {
            player.setPlayerImage(playerImage.getBytes());
            if (playerImage.getOriginalFilename().equals("")) {
                player.setPlayerImageName(playerImageName);
            } else {
                player.setPlayerImageName(playerImage.getOriginalFilename());
            }
        }

        playersRepository.save(player);
    }


    @Transactional
    public void deletePlayer(String playerName) {
        Player deletePlayer = playersRepository.findByPlayerName(playerName).get();
        playersRepository.delete(deletePlayer);
    }
}
