package com.furman.DigitalAccountingOfBooks.controllers;

import com.furman.DigitalAccountingOfBooks.models.Person;
import com.furman.DigitalAccountingOfBooks.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "people/login";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));

        return "people/show";
    }

    @GetMapping("/registration")
    public String newPerson(@ModelAttribute("person")Person person){
        return "people/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/registration";

        peopleService.save(person);
        return "redirect:/people/login";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id")int id){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult,
                         @PathVariable("id")int id){
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id")int id){
        peopleService.delete(id);
        return "redirect:/people";
    }

}
