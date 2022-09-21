package com.spring.project.controllers.app;

import com.spring.project.dto.app.LeagueDTO;
import com.spring.project.dto.app.PlayerDTO;
import com.spring.project.dto.app.TeamDTO;
import com.spring.project.models.app.League;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.services.app.LeaguesService;
import com.spring.project.services.app.PlayersService;
import com.spring.project.services.app.TeamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/player")
public class PlayersController {

    private final PlayersService playersService;
    private final TeamsService teamsService;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayersController(PlayersService playersService, TeamsService teamsService, ModelMapper modelMapper) {
        this.playersService = playersService;
        this.teamsService = teamsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{player_name}")
    public String player(@PathVariable("player_name") String playerName, Model model) {
        model.addAttribute("player", convertToPlayerDTO(playersService.player(playerName)));
        model.addAttribute("playerImage", playersService.player(playerName).getPlayerImage());
        return "player/player";
    }



    @GetMapping("/create_player_form")
    public String createPlayerForm(@ModelAttribute("player") PlayerDTO playerDTO,
                             @ModelAttribute("team") TeamDTO teamDTO, Model model) {
        model.addAttribute("teams", teamsService.allTeams().stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "player/admin/createPlayer";
    }


    @PostMapping()
    public String createPlayer(@ModelAttribute("player") PlayerDTO playerDTO,
                               @RequestParam(value = "teamName", required = false) String teamName,
                               @RequestParam(value = "playerImage", required = false) MultipartFile playerImage) {
        try {
            playersService.createPlayer(convertToPlayer(playerDTO), teamName, playerImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/account/admin/adminPage";
    }



    @GetMapping("/select_player")
    public String selectPlayer(@ModelAttribute("player") PlayerDTO playerDTO,
                               @ModelAttribute("team") TeamDTO teamDTO, Model model) {
        model.addAttribute("players", playersService.allPlayers().stream()
                .map(this::convertToPlayerDTO).collect(Collectors.toList()));
        return "player/admin/selectPlayer";
    }


    @GetMapping("/edit_player_form")
    public String editPlayerForm(@ModelAttribute("player") PlayerDTO playerDTO, Model model,
                                 @RequestParam(value = "playerName", required = false) String playerName,
                                 @ModelAttribute("team") TeamDTO teamDTO) {

        model.addAttribute("player", convertToPlayerDTO(playersService.player(playerName)));
        model.addAttribute("playerImageName", playersService.player(playerName).getPlayerImageName());

        if (!playersService.player(playerName).getTeams().isEmpty()) {
            model.addAttribute("teams1", (playersService.teamsOfPlayer(playerName).stream()
                    .map(this::convertToTeamDTO).collect(Collectors.toList())));
            model.addAttribute("teams2", playersService.allTeamsExceptTeamsOfPlayer(playerName)
                    .stream().map(this::convertToTeamDTO).collect(Collectors.toList()));
        } else {
            model.addAttribute("teams2", teamsService.allTeams().stream()
                    .map(this::convertToTeamDTO).collect(Collectors.toList()));
        }

        return "player/admin/editPlayer";
    }


    @PatchMapping("/{player_name}")
    public String editPlayer(@PathVariable("player_name") String playerName,
                             @ModelAttribute("player") PlayerDTO playerDTO,
                             @RequestParam(value = "teamName", required = false) String teamName,
                             @RequestParam(value = "playerImageName", required = false) String playerImageName,
                             @RequestParam(value = "playerImage", required = false)
                                         MultipartFile playerImage) {
        try {
            playersService.editPlayer(convertToPlayer(playerDTO), playerName, teamName, playerImage,
                    playerImageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/account/admin/adminPage";
    }


    @DeleteMapping("/{player_name}")
    public String deletePlayer(@PathVariable("player_name") String playerName) {
        playersService.deletePlayer(playerName);
        return "/account/admin/adminPage";
    }



    private Player convertToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }


    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }
}
