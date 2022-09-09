package com.spring.project.controllers.account;

import com.spring.project.dto.app.*;
import com.spring.project.models.app.*;
import com.spring.project.services.account.PeopleService;
import com.spring.project.services.app.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    public String getAdminPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("admin", peopleService.getPerson(id));
        return "/account/admin/adminPage";
    }

}
