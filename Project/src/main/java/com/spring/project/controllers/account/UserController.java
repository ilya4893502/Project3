package com.spring.project.controllers.account;

import com.spring.project.models.account.Person;
import com.spring.project.models.app.Team;
import com.spring.project.services.account.PeopleService;
import com.spring.project.services.app.TeamsService;
import com.spring.project.util.exceptions.PersonError;
import com.spring.project.util.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final PeopleService peopleService;
    private final TeamsService teamsService;

    @Autowired
    public UserController(PeopleService peopleService, TeamsService teamsService) {
        this.peopleService = peopleService;
        this.teamsService = teamsService;
    }


    @GetMapping("/{id}")
    public String getUserPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.getPerson(id));
        return "/account/user/userPage";
    }


    @GetMapping("/{id}/search_team")
    public String searchTeam(@RequestParam(value = "teamName", required = false) String teamName,
                             Model model, @PathVariable("id") int id,
                             @ModelAttribute("team") Team team) {
        if (teamName != null) {
            model.addAttribute("teams", teamsService.findTeamOnStartingLetter(teamName));
        }
        model.addAttribute("person", peopleService.getPerson(id));
        return "/account/user/addTeam";
    }


//    @PostMapping("/{id}/add_team")
//    public String addTeamForPerson(@PathVariable("id") int id, @ModelAttribute("team") Team team) {
//        peopleService.addTeamForPerson(id, team);
//        return "redirect:/account/user/userPage";
//    }


    @ExceptionHandler
    private ResponseEntity<PersonError> personNotFoundException(PersonNotFoundException e) {
        PersonError personError = new PersonError(
                "Пользователь не найден!"
        );
        return new ResponseEntity<>(personError, HttpStatus.NOT_FOUND);
    }
}
