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
        model.addAttribute("correct", correct);
        model.addAttribute("incorrect", incorrect);
        Math calculation;

        switch (operations.trim()) {
            case ":":
                calculation = mathsCalculations.dzielenie(value);
                break;
            case "-":
                calculation = mathsCalculations.odejmowanie(value);
                break;
            case "*":
                calculation = mathsCalculations.mnozenie(value);
                break;
            case "+":
                calculation = mathsCalculations.dodawanie(value);
                break;
            default:
                return "error"; // Dodaj obsługę błędu dla niezna
        }
        quantity--;
        if (quantity >= 0) {
            model.addAttribute("arg1", calculation.getArg1());
            model.addAttribute("arg2", calculation.getArg2());
            model.addAttribute("quantity", quantity);
            return "start1";
        } else {
            clearAndReset();
            return "end";
        }
    }

    @PostMapping("/sum/{operations}/{value}/{quantity}")
    public String mathSum(@RequestParam int suma, @PathVariable String operations, @PathVariable String value, @PathVariable Integer quantity, Model model) {
        Math calculation = mathsCalculations.getMath();
        if (suma == calculation.getArg3()) {
            String ok = calculation.getArg1() + operations + calculation.getArg2() + " = " + suma;
            goodAnswers.add(ok);
            correct++;
        } else {
            String bad = calculation.getArg1() + operations + calculation.getArg2() + " = " + suma;
            badAnswers.add(bad);
            incorrect++;
        }
        return "redirect:/calc/{operations}/{value}/{quantity}";
    }

    private void clearAndReset() {
        goodAnswers.clear();
        badAnswers.clear();
        LP = 0;
        correct = 0;
        incorrect = 0;
    }
}