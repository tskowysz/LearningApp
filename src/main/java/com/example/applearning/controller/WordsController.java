package com.example.applearning.controller;
import com.example.applearning.repository.WordRepo;
import com.example.applearning.repository.Words;
import com.example.applearning.service.WordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class WordsController {

    WordRepo wordRepo;
    WordService wordService;

    List<Words> wordsList = new ArrayList<>();


    public WordsController(WordRepo wordRepo, WordService wordService) {
        this.wordRepo = wordRepo;
        this.wordService = wordService;
    }


    @GetMapping("/levels")
    public String getLevel(Model model) {
        wordsList = wordService.findAllFromRepo();
        Set<String> wordsCategory = wordService.findByCategory(wordsList);
        int size = wordsCategory.size();
        model.addAttribute("wordsList", wordsList);
        model.addAttribute("size", size);
        model.addAttribute("categories", wordsCategory);
        return "englishLevel";
    }

    @GetMapping("/learn/{category}")
    public String home1(@PathVariable("category") String category, Model model) throws IOException {
        Stream<Words> wordsStream = wordsList.stream().filter(x -> x.getCategory().equalsIgnoreCase(category));
        List<Words> collect = wordsStream.collect(Collectors.toList());
        int categoryListSize = collect.size();
        if (categoryListSize != 0) {
            Words losuj = wordService.losuj(collect);
            model.addAttribute("categoryListSize", categoryListSize);
            model.addAttribute("category", category);
            model.addAttribute("wylosowane", losuj);
            return "/wordsHome";
        } else {
            return "end";
        }
    }

    @PostMapping("/checkTheWord/{translate}/{category}")
    public String change(@PathVariable String translate, @RequestParam String wpisane, @PathVariable String category) {
        for (int i = 0; i < wordsList.size(); i++) {
            Words w = wordsList.get(i);
            if (w.getTranslate().equalsIgnoreCase(wpisane.trim())) {
                boolean remove = wordsList.remove(w);
                i--;
            }
        }
        return "redirect:/learn/{category}";
    }

    @GetMapping("/library")
    public String addWord(Model model) {
        List<Words> library = wordRepo.findAll();
        int size = library.size();
        model.addAttribute("size", size);
        model.addAttribute("library", library);
        Words words = new Words();
        model.addAttribute("word", words);
        return "library";
    }

    @PostMapping("/addNewWord")
    public String add(Words words) {
        wordRepo.save(words);
        return "redirect:/library";
    }

    @GetMapping("/deleteWord/{id}")
    public String delete(@PathVariable Long id) {
        Words words = wordService.findById(id).get();
        wordRepo.delete(words);
        return "redirect:/library";
    }

    @GetMapping("/reset")
    public String reset() {
        return "redirect:/levels";
    }

}