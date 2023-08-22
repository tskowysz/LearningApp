package com.example.applearning.controller;

import com.example.applearning.model.Math;
import com.example.applearning.service.MathsCalculations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MathController {
    public static int LP;
    List<String> goodAnswers;
    List<String> badAnswers;
    int correct;
    int incorrect;

    MathsCalculations mathsCalculations;

    @Autowired
    public MathController(List<String> goodAnswers, List<String> badAnswers, MathsCalculations mathsCalculations) {
        this.goodAnswers = goodAnswers;
        this.badAnswers = badAnswers;
        this.mathsCalculations = mathsCalculations;
    }

    public MathController() {
    }

    @GetMapping("/math")
    public String home(Model model) throws InterruptedException {
        return "sumRange";
    }

    @GetMapping("/save")
    public String show(@RequestParam String operations, @RequestParam String value, @RequestParam String quantity, Model model) {
        model.addAttribute("operations", operations);
        model.addAttribute("value", value);
        model.addAttribute("quantity", quantity);
        return "start";
    }

    @GetMapping("/calc/{operations}/{value}/{quantity}")
    public String calculate(@PathVariable String operations, @PathVariable String value, @PathVariable Integer quantity, Model model) {
        LP++;
        model.addAttribute("lp", LP);
        model.addAttribute("goodAnswers", goodAnswers);
        model.addAttribute("badAnswers", badAnswers);
        model.addAttribute("correct",correct);
        model.addAttribute("incorrect",incorrect);
        if (operations.equals(" : ")) {
            quantity--;
            if (quantity >= 0) {
                Math dzielenie = mathsCalculations.dzielenie(value);
                model.addAttribute("arg1", dzielenie.getArg1());
                model.addAttribute("arg2", dzielenie.getArg2());
                model.addAttribute("quantity", quantity);
                return "start1";
            } else {
                goodAnswers.clear();
                badAnswers.clear();
                LP = 0;
                correct=0;
                incorrect=0;
                return "end";
            }
        }
        if (operations.equals(" - ")) {
            quantity--;
            if (quantity >= 0) {
                Math dzielenie = mathsCalculations.odejmowanie(value);
                model.addAttribute("arg1", dzielenie.getArg1());
                model.addAttribute("arg2", dzielenie.getArg2());
                model.addAttribute("quantity", quantity);
                return "start1";
            } else {
                goodAnswers.clear();
                badAnswers.clear();
                LP = 0;
                correct=0;
                incorrect=0;
                return "end";
            }
        }
        if (operations.equals(" * ")) {
            quantity--;
            if (quantity >= 0) {
                Math dzielenie = mathsCalculations.mnozenie(value);
                model.addAttribute("arg1", dzielenie.getArg1());
                model.addAttribute("arg2", dzielenie.getArg2());
                model.addAttribute("quantity", quantity);
                return "start1";
            } else {
                goodAnswers.clear();
                badAnswers.clear();
                LP = 0;
                correct=0;
                incorrect=0;
                return "end";
            }
        }
        if (operations.equals(" + ")) {
            quantity--;
            if (quantity >= 0) {
                Math dzielenie = mathsCalculations.dodawanie(value);
                model.addAttribute("arg1", dzielenie.getArg1());
                model.addAttribute("arg2", dzielenie.getArg2());
                model.addAttribute("quantity", quantity);
                return "start1";
            } else {
                goodAnswers.clear();
                badAnswers.clear();
                LP = 0;
                correct=0;
                incorrect=0;
                return "end";
            }
        }
        return "";
    }

    @PostMapping("/sum/{operations}/{value}/{quantity}")
    public String mathSum(@RequestParam int suma, @PathVariable String operations, @PathVariable String value, @PathVariable Integer quantity, Model model) {

        if (suma == mathsCalculations.getMath().getArg3()) {
            String ok = mathsCalculations.getMath().getArg1() + operations + mathsCalculations.getMath().getArg2() + " = " + suma;
            goodAnswers.add(ok);
            correct++;
        } else {
            String bad = mathsCalculations.getMath().getArg1() + operations + mathsCalculations.getMath().getArg2() + " = " + suma;
            badAnswers.add(bad);
            incorrect++;
        }
        return "redirect:/calc/{operations}/{value}/{quantity}";
    }

}