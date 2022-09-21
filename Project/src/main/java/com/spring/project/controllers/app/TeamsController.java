package com.spring.project.controllers.app;

import com.spring.project.dto.app.CoachDTO;
import com.spring.project.dto.app.MatchDTO;
import com.spring.project.dto.app.PlayerDTO;
import com.spring.project.dto.app.TeamDTO;
import com.spring.project.models.app.Coach;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.services.app.CoachesService;
import com.spring.project.services.app.PlayersService;
import com.spring.project.services.app.TeamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/team")
public class TeamsController {

    private final TeamsService teamsService;
    private final CoachesService coachesService;
    private final PlayersService playersService;
    private final ModelMapper modelMapper;

    @Autowired
    public TeamsController(TeamsService teamsService, CoachesService coachesService, PlayersService playersService, ModelMapper modelMapper) {
        this.teamsService = teamsService;
        this.coachesService = coachesService;
        this.playersService = playersService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{team_name}")
    public String team(@PathVariable("team_name") String teamName, Model model) {
        model.addAttribute("team", convertToTeamDTO(teamsService.team(teamName)));
        model.addAttribute("players", teamsService.playersOfTeam(teamName).stream()
                .map(this::convertToPlayerDTO).collect(Collectors.toList()));
        model.addAttribute("coach", convertToCoachDTO(teamsService.coachOfTeam(teamName)));
        return "team/team";
    }


    @GetMapping("/{team_name}/results")
    public String resultsTeam(@PathVariable("team_name") String teamName, Model model) {
        model.addAttribute("matches", teamsService.matchesOfTeam(teamName).stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        return "team/results";
    }


    @GetMapping("/{team_name}/calendar")
    public String calendarTeam(@PathVariable("team_name") String teamName, Model model) {
        model.addAttribute("matches", teamsService.matchesOfTeam(teamName).stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        return "team/calendar";
    }



    @GetMapping("/create_team_form")
    public String createTeamForm(@ModelAttribute("team") TeamDTO teamDTO,
                           @ModelAttribute("coach") CoachDTO coachDTO,
                           @ModelAttribute("player") PlayerDTO playerDTO, Model model) {
        model.addAttribute("coaches", coachesService.allFreeCoaches().stream()
                .map(this::convertToCoachDTO).collect(Collectors.toList()));
        model.addAttribute("players", playersService.allPlayers().stream()
                .map(this::convertToPlayerDTO).collect(Collectors.toList()));
        return "team/admin/createTeam";
    }


    @PostMapping()
    public String createTeam(@ModelAttribute("team") TeamDTO teamDTO,
                             @RequestParam(value = "coachName", required = false) String coachName,
                             @RequestParam(value = "teamImage", required = false)
                                         MultipartFile teamImage,
                             @RequestParam(value = "playerName", required = false)
                                         List<String> playerNameList) throws IOException {
        if (coachName == null & playerNameList == null) {
            teamsService.createTeamWithoutCoachAndPlayers(convertToTeam(teamDTO), teamImage);
        } else {
            teamsService.createTeamWithCoachAndOrPlayers(convertToTeam(teamDTO), coachName,
                    playerNameList, teamImage);
        }
        return "/account/admin/adminPage";
    }



    @GetMapping("/select_team")
    public String selectTeam(@ModelAttribute("team") TeamDTO teamDTO, Model model) {
        model.addAttribute("teams", teamsService.allTeams().stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "team/admin/selectTeam";
    }


    @GetMapping("/edit_team_form")
    public String editTeamForm(@ModelAttribute("team") TeamDTO teamDTO,
                               @ModelAttribute("player") PlayerDTO playerDTO,
                               @RequestParam(value = "teamName", required = false) String teamName,
                               @ModelAttribute("coach") CoachDTO coachDTO, Model model) {
        model.addAttribute("team", convertToTeamDTO(teamsService.team(teamName)));
        model.addAttribute("teamImageName", teamsService.team(teamName).getTeamImageName());

        if (teamsService.team(teamName).getPlayers() != null) {
            model.addAttribute("players1", playersService.playersOfTeam(teamName).stream()
                    .map(this::convertToPlayerDTO).collect(Collectors.toList()));
            model.addAttribute("players2", playersService.allPlayersExceptSelected(teamName)
                    .stream().map(this::convertToPlayerDTO).collect(Collectors.toList()));
        } else {
            model.addAttribute("players", playersService.allPlayers().stream()
                    .map(this::convertToPlayerDTO).collect(Collectors.toList()));
        }

        if (teamsService.team(teamName).getCoach() != null) {
            model.addAttribute("coach1", convertToCoachDTO(coachesService.coachOfTeam(teamName)));
            model.addAttribute("coaches", coachesService.allFreeCoaches().stream()
                    .map(this::convertToCoachDTO).collect(Collectors.toList()));
        } else {
            model.addAttribute("coaches", coachesService.allFreeCoaches().stream()
                    .map(this::convertToCoachDTO).collect(Collectors.toList()));
        }
        return "team/admin/editTeam";
    }



    @PatchMapping("/{team_name}")
    public String editTeam(@ModelAttribute("team") TeamDTO teamDTO,
                           @PathVariable("team_name") String teamName,
                           @RequestParam(value = "coachName", required = false) String coachName,
                           @RequestParam(value = "teamImage", required = false)
                                       MultipartFile teamImage,
                           @RequestParam(value = "teamImageName", required = false) String teamImageName,
                           @RequestParam(value = "playerName", required = false)
                                       List<String> playerNameList) throws IOException {
        if (playerNameList == null & coachName == null) {
            teamsService.editTeamWhereNotEditCoachAndPlayers(convertToTeam(teamDTO), teamName, teamImage,
                    teamImageName);
        } else {
            teamsService.editTeamWhereEditCoachAndOrPlayers(convertToTeam(teamDTO), teamName,
                    playerNameList, coachName, teamImage, teamImageName);
        }
        return "/account/admin/adminPage";
    }


    @DeleteMapping("/{team_name}")
    public String deleteTeam(@PathVariable("team_name") String teamName) {
        teamsService.deleteTeam(teamName);
        return "/account/admin/adminPage";
    }


    private Team convertToTeam(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, Team.class);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }


    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }


    private CoachDTO convertToCoachDTO(Coach coach) {
        return modelMapper.map(coach, CoachDTO.class);
    }


    private MatchDTO convertToMatchDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }
}
