package com.spring.project.controllers;

import com.spring.project.dto.app.LeagueDTO;
import com.spring.project.models.app.League;
import com.spring.project.services.app.LeaguesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class MainController {

    private final LeaguesService leaguesService;
    private final ModelMapper modelMapper;

    @Autowired
    public MainController(LeaguesService leaguesService, ModelMapper modelMapper) {
        this.leaguesService = leaguesService;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    public String mainPage(Model model){
        model.addAttribute("leagues", leaguesService.allLeagues().stream()
                .map(this::convertToLeagueDTO).collect(Collectors.toList()));
        return "mainPage";
    }


    private LeagueDTO convertToLeagueDTO(League league) {
        return modelMapper.map(league, LeagueDTO.class);
    }
}
