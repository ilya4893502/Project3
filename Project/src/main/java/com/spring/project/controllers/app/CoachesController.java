package com.spring.project.controllers.app;

import com.spring.project.dto.app.CoachDTO;
import com.spring.project.dto.app.TeamDTO;
import com.spring.project.models.app.Coach;
import com.spring.project.models.app.Team;
import com.spring.project.services.app.CoachesService;
import com.spring.project.services.app.TeamsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/coach")
public class CoachesController {

    private final CoachesService coachesService;
    private final TeamsService teamsService;
    private final ModelMapper modelMapper;

    @Autowired
    public CoachesController(CoachesService coachesService, TeamsService teamsService, ModelMapper modelMapper) {
        this.coachesService = coachesService;
        this.teamsService = teamsService;
        this.modelMapper = modelMapper;
    }



    @GetMapping("/{coach_name}")
    public String coach(@PathVariable("coach_name") String coachName, Model model) throws IOException {
        model.addAttribute("coach", convertToCoachDTO(coachesService.coach(coachName)));
        model.addAttribute("coachImage", coachesService.convertToImageCoach(coachName));
        return "coach/coach";
    }



    @GetMapping("/create_coach_form")
    public String createCoachForm(@ModelAttribute("coach") CoachDTO coachDTO,
                                  @ModelAttribute("team") TeamDTO teamDTO, Model model) {
        model.addAttribute("teams", teamsService.allTeamsWhereNotCoach().stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
        return "coach/admin/createCoach";
    }




    @PostMapping()
    public String createCoach(@ModelAttribute("coach") CoachDTO coachDTO,
                              @RequestParam(value = "teamName", required = false) String teamName,
                              @RequestParam(value = "coachImage", required = false)
                                          MultipartFile coachImage) {
        try {
            coachesService.createCoach(convertToCoach(coachDTO), teamName, coachImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/account/admin/adminPage";
    }



    @GetMapping("/select_coach")
    public String selectCoach(@ModelAttribute("coach") CoachDTO coachDTO, Model model) {
        model.addAttribute("coaches", coachesService.allCoaches());
        return "coach/admin/selectCoach";
    }


    @GetMapping("/edit_coach_form")
    public String editCoachForm(@ModelAttribute("coach") CoachDTO coachDTO, Model model,
                                @RequestParam(value = "coachName", required = false) String coachName,
                                @ModelAttribute("team") TeamDTO teamDTO) {
        model.addAttribute("coach", convertToCoachDTO(coachesService.coach(coachName)));
        model.addAttribute("coachImageName", coachesService.coach(coachName).getCoachImageName());

    if (coachesService.coach(coachName).getTeam() != null) {
        model.addAttribute("team1", convertToTeamDTO(coachesService.teamOfCoach(coachName)));
        model.addAttribute("teams", teamsService.allTeamsWhereNotCoach()
                .stream().map(this::convertToTeamDTO).collect(Collectors.toList()));
    } else {
        model.addAttribute("teams", teamsService.allTeamsWhereNotCoach().stream()
                .map(this::convertToTeamDTO).collect(Collectors.toList()));
    }
        return "coach/admin/editCoach";
    }


    @PatchMapping("/{coach_name}")
    public String editCoach(@PathVariable("coach_name") String coachName,
                            @ModelAttribute("coach") CoachDTO coachDTO,
                            @RequestParam(value = "teamName", required = false) String teamName,
                            @RequestParam(value = "coachImageName", required = false) String coachImageName,
                            @RequestParam(value = "coachImage", required = false)
                                        MultipartFile coachImage) {
        try {
            coachesService.editCoach(convertToCoach(coachDTO), coachName, teamName, coachImage, coachImageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/account/admin/adminPage";
    }


    @DeleteMapping("/{coach_name}")
    public String deleteCoach(@PathVariable("coach_name") String coachName) {
        coachesService.deleteCoach(coachName);
        return "/account/admin/adminPage";
    }



    private Coach convertToCoach(CoachDTO coachDTO) {
        return modelMapper.map(coachDTO, Coach.class);
    }


    private CoachDTO convertToCoachDTO(Coach coach) {
        return modelMapper.map(coach, CoachDTO.class);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        return modelMapper.map(team, TeamDTO.class);
    }

}
