package ru.evgrafova.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.evgrafova.notes.persist.Note;
import ru.evgrafova.notes.persist.NotesRepository;

@Controller
public class NotesController {

    private final NotesRepository repository;

    @Autowired
    public NotesController(NotesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new Note());
        return "index";
    }

    @PostMapping
    public String newNote(Note note) {
        repository.save(note);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
