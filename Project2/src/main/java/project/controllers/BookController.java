package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.models.Book;
import project.models.Person;
import project.services.BookService;
import project.services.PeopleService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String all(Model model,@RequestParam(value = "page", required = false) Integer page,
                      @RequestParam(value = "books_per_page", required = false) Integer books_per_page){
        if (page == null){
            model.addAttribute("books", bookService.all());
        }
        else  model.addAttribute("books", bookService.all2(page,books_per_page));
        return "books/all";
    }
    @GetMapping("/new")
    public String new_book(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping("/new_one")
    public String new_one(@ModelAttribute("book") Book book){
        bookService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        boolean check = bookService.show(id).getPerson() != null;
        model.addAttribute("book", bookService.show(id));
        model.addAttribute("people", peopleService.all());
        model.addAttribute("person", bookService.show(id).getPerson());
        model.addAttribute("check",check);
        model.addAttribute("date2", new Date());
        System.out.println((Date)bookService.show(id).getDate());
        System.out.println(new Date());
        return "books/show";
    }
    @GetMapping("/{id}/setPerson")
    public String setPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", new Person());
        model.addAttribute("people", peopleService.all());
        model.addAttribute("book", bookService.show(id));
        return "books/setPerson";
    }
    @PatchMapping("/{id}/settedPerson")
    public String settedPerson(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        bookService.update(id, person);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}/edit_one")
    public String edit_one(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        bookService.update(book,id);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/search")
    public String search(){

        return "books/search";
    }
    @PatchMapping("/search")
    public String searched(@RequestParam("name") String name, Model model){
        System.out.println(name);
        model.addAttribute("books", bookService.findAllByName(name));
        return "books/search";
    }

}
