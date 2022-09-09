package com.spring.project.controllers.app;

import com.spring.project.dto.app.LeagueDTO;
import com.spring.project.dto.app.MatchDTO;
import com.spring.project.dto.app.PlayerDTO;
import com.spring.project.dto.app.TeamDTO;
import com.spring.project.models.app.League;
import com.spring.project.models.app.Match;
import com.spring.project.models.app.Player;
import com.spring.project.models.app.Team;
import com.spring.project.services.app.LeaguesService;
import com.spring.project.services.app.MatchesService;
import com.spring.project.services.app.TeamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/match")
public class MatchesController {

    private final MatchesService matchesService;
    private final LeaguesService leaguesService;
    private final TeamsService teamsService;
    private final ModelMapper modelMapper;

    @Autowired
    public MatchesController(MatchesService matchesService, LeaguesService leaguesService, TeamsService teamsService, ModelMapper modelMapper) {
        this.matchesService = matchesService;
        this.leaguesService = leaguesService;
        this.teamsService = teamsService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{match_id}")
    public String match(@PathVariable("match_id") int matchId, Model model) {
        model.addAttribute("match", convertToMatchDTO(matchesService.getMatch(matchId)));
        model.addAttribute("players", matchesService.playersOfMatch(matchId).stream()
                .map(this::convertToPlayerDTO).collect(Collectors.toList()));
        return "match/futureMatch";
    }


    @GetMapping("/{match_id}/h2h")
    public String h2h(@PathVariable("match_id") int matchId, Model model) {
        model.addAttribute("matches", matchesService.h2h(matchId).stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        model.addAttribute("teams", matchesService.teamsOfMatch(matchId).stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "match/h2h";
    }



    @GetMapping("/create_match_form")
    public String createMatchForm(@ModelAttribute("match") MatchDTO matchDTO, Model model,
                                  @ModelAttribute("team") TeamDTO teamDTO,
                                  @ModelAttribute("player") PlayerDTO playerDTO,
                                  @ModelAttribute("league") LeagueDTO leagueDTO,
                                  @RequestParam(value = "leagueName", required = false)
                                              String leagueName,
                                  @RequestParam(value = "teamName", required = false) String teamName) {

        model.addAttribute("leagues", leaguesService.allLeagues().stream()
                .map(this::convertToLeagueDTO).collect(Collectors.toList()));

        if(leagueName != null) {
            model.addAttribute("teams", leaguesService.teamsOfLeague(leagueName).stream()
                    .map(this::convertToTeamDTO).collect(Collectors.toList()));
            model.addAttribute("league1",
                    convertToLeagueDTO(leaguesService.league(leagueName)));
        }

        if (teamName != null & leagueName != null) {
            String[] teamNames = teamName.split(",");

            model.addAttribute("team1", convertToTeamDTO(teamsService.team(teamNames[0])));
            model.addAttribute("team2", convertToTeamDTO(teamsService.team(teamNames[1])));

            model.addAttribute("players1", teamsService.playersOfTeam(teamNames[0])
                    .stream().map(this::convertToPlayerDTO).collect(Collectors.toList()));
            model.addAttribute("players2", teamsService.playersOfTeam(teamNames[1])
                    .stream().map(this::convertToPlayerDTO).collect(Collectors.toList()));
        }

        return "match/admin/createMatch";
    }


    @PostMapping("/create_match")
    public String createMatch(@ModelAttribute("match") MatchDTO matchDTO,
                              @RequestParam(value = "leagueName", required = false) String leagueName,
                              @RequestParam(value = "teamName", required = false) String teamName,
                              @RequestParam(value = "playerName", required = false)
                              List<String> namesOfPlayers) {
        matchesService.createMatch(convertToMatch(matchDTO), leagueName, teamName, namesOfPlayers);
        return "/account/admin/adminPage";
    }



    @GetMapping("/select_match")
    public String selectMatch(@ModelAttribute("match") MatchDTO matchDTO, Model model) {
        model.addAttribute("matches", matchesService.allMatches().stream()
                .map(this::convertToMatchDTO).collect(Collectors.toList()));
        return "match/admin/selectMatch";
    }


    @GetMapping("/edit_match_form")
    public String editMatchForm(@RequestParam(value = "matchId", required = false) int matchId) {

        return "match/admin/editMatch";
    }


    private Match convertToMatch(MatchDTO matchDTO) {
        return modelMapper.map(matchDTO, Match.class);
    }


    private MatchDTO convertToMatchDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }


    private PlayerDTO convertToPlayerDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }


    private LeagueDTO convertToLeagueDTO(League league) {
        return modelMapper.map(league, LeagueDTO.class);
    }
}
