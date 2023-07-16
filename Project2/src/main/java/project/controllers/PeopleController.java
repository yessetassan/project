package project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.models.Person;
import project.services.BookService;
import project.services.PeopleService;

import javax.persistence.EntityManager;
import java.util.Date;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final BookService bookService;

    @Autowired
    public PeopleController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String all(Model model){
        model.addAttribute("people", peopleService.all());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String personal(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.show(id));
        model.addAttribute("books",bookService.findBookByPersonId(id));
        model.addAttribute("date2", new Date());
        return "people/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("person", peopleService.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}/edit_one")
    public String update(@PathVariable("id") int id, @ModelAttribute("person")Person person){
        peopleService.update(id,person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String new_student(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping("/new_one")
    public String new_one(@ModelAttribute("person") Person person){
        peopleService.add(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
