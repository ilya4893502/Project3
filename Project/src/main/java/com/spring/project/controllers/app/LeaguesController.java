package com.spring.project.controllers.app;

import com.spring.project.dto.app.LeagueDTO;
import com.spring.project.dto.app.MatchDTO;
import com.spring.project.dto.app.TeamDTO;
import com.spring.project.models.app.League;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Team;
import com.spring.project.services.app.LeaguesService;
import com.spring.project.services.app.TeamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/league")
public class LeaguesController {

    private final LeaguesService leaguesService;
    private final TeamsService teamsService;
    private final ModelMapper modelMapper;

    @Autowired
    public LeaguesController(LeaguesService leaguesService, TeamsService teamsService, ModelMapper modelMapper) {
        this.leaguesService = leaguesService;
        this.teamsService = teamsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{league_name}")
    public String league(@PathVariable("league_name") String leagueName, Model model) {
        model.addAttribute("league", convertToLeagueDTO(leaguesService.league(leagueName)));
        model.addAttribute("teams", leaguesService.teamsOfLeague(leagueName).stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "league/league";
    }


    @GetMapping("/{league_name}/calendar")
    public String leagueCalendar(@PathVariable("league_name") String leagueName, Model model) {
        model.addAttribute("matches", leaguesService.matchesOfLeague(leagueName).stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        return "league/calendar";
    }


    @GetMapping("/{league_name}/results")
    public String leagueResults(@PathVariable("league_name") String leagueName, Model model) {
        model.addAttribute("matches", leaguesService.matchesOfLeague(leagueName).stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        return "league/results";
    }



    @GetMapping("/create_league_form")
    public String createLeagueForm(@ModelAttribute("league") LeagueDTO leagueDTO,
                             @ModelAttribute("team") TeamDTO teamDTO, Model model) {
        model.addAttribute("teams", teamsService.allTeams().stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "league/admin/createLeague";
    }


    @PostMapping()
    public String createLeague(@ModelAttribute("league") LeagueDTO leagueDTO,
                               @RequestParam(value = "leagueImage", required = false)
                                       MultipartFile leagueImage,
                               @RequestParam(value = "teamName", required = false)
                                           List<String> teamNameList) throws IOException {
        if (teamNameList == null) {
            leaguesService.createLeagueWhereNotSelectTeams(convertToLeague(leagueDTO), leagueImage);
        } else {
            leaguesService.createLeagueWhereSelectTeams(convertToLeague(leagueDTO), teamNameList, leagueImage);
        }

        return "/account/admin/adminPage";
    }



    @GetMapping("/select_league")
    public String selectLeague(@ModelAttribute("league") LeagueDTO leagueDTO, Model model) {
        model.addAttribute("leagues", leaguesService.allLeagues().stream()
                .map(this::convertToLeagueDTO).collect(Collectors.toList()));
        return "league/admin/selectleague";
    }


    @GetMapping("/edit_league_form")
    public String editLeagueForm(@ModelAttribute("league") LeagueDTO leagueDTO, Model model,
                                 @ModelAttribute("team") TeamDTO teamDTO,
                                 @RequestParam(value = "leagueName", required = false)
                                             String leagueName) {

        model.addAttribute("league", convertToLeagueDTO(leaguesService.league(leagueName)));
        model.addAttribute("leagueImageName", leaguesService.league(leagueName).getLeagueImageName());

        if (!leaguesService.league(leagueName).getTeams().isEmpty()) {
            model.addAttribute("teams1", leaguesService.teamsOfLeague(leagueName).stream()
                    .map(this::convertToTeamDTO).collect(Collectors.toList()));
            model.addAttribute("teams2", leaguesService.allTeamsExceptSelected(leagueName)
                    .stream().map(this::convertToTeamDTO).collect(Collectors.toList()));
        } else {
            model.addAttribute("teams", teamsService.allTeams().stream()
                    .map(this::convertToTeamDTO).collect(Collectors.toList()));
        }

        return "league/admin/editLeague";
    }


    @PatchMapping("/{league_name}")
    public String editLeague(@ModelAttribute("league") LeagueDTO leagueDTO,
                             @PathVariable("league_name") String leagueName,
                             @RequestParam(value = "leagueImage", required = false)
                                         MultipartFile leagueImage,
                             @RequestParam(value = "leagueImageName", required = false) String leagueImageName,
                             @RequestParam(value = "teamName", required = false)
                                         List<String> teamNameList) throws IOException {
        if (teamNameList == null) {
            leaguesService.editLeagueWhereNotEditTeams(convertToLeague(leagueDTO), leagueName,
                    leagueImage, leagueImageName);
        } else {
            leaguesService.editLeagueWhereEditTeams(convertToLeague(leagueDTO), leagueName,
                    teamNameList, leagueImage, leagueImageName);
        }
        return "/account/admin/adminPage";
    }



    @DeleteMapping("/{league_name}")
    public String deleteLeague(@PathVariable("league_name") String leagueName){
        leaguesService.deleteLeague(leagueName);
        return "/account/admin/adminPage";
    }



    private League convertToLeague(LeagueDTO leagueDTO) {
        return modelMapper.map(leagueDTO, League.class);
    }


    private LeagueDTO convertToLeagueDTO(League league) {
        return modelMapper.map(league, LeagueDTO.class);
    }


    private Team convertToTeam(TeamDTO teamDTO) {
        return modelMapper.map(teamDTO, Team.class);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }


    private MatchDTO convertToMatchDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }
}
