package com.example.applearning.service;

import com.example.applearning.model.Math;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MathsCalculations {

    Math math;
    Random random;

    public MathsCalculations(Math math, Random random) {
        this.math = math;
        this.random = random;
    }

    public Math getMath() {
        return math;
    }

    public Math dzielenie(String s) {
        int range = Integer.parseInt(s);
        int i;
        int j;
        int z;
        boolean flaga = true;
        while (flaga) {
            i = random.nextInt(range);
            j = random.nextInt(range);
            if (i >= j && i != j && j > 0 && j != 1 && i % j == 0) {
                math.setArg1(i);
                math.setArg2(j);
                math.setArg3(z = i / j);
                flaga = false;
            }
        }
        return math;
    }

    public Math dodawanie(String s) {
        int range = Integer.parseInt(s);
        int i;
        int j;
        int z;
        i = random.nextInt(range);
        j = random.nextInt(range);
        math.setArg1(i);
        math.setArg2(j);
        math.setArg3(z = i + j);
        return math;
    }

    public Math odejmowanie(String s) {
        int range = Integer.parseInt(s);
        int i;
        int j;
        int z;
        boolean flaga = true;
        while (flaga) {
            i = random.nextInt(range);
            j = random.nextInt(range);
            if (i >= j && i != j) {
                math.setArg1(i);
                math.setArg2(j);
                math.setArg3(z = i - j);
                flaga = false;
            }
        }
        return math;
    }

    public Math mnozenie(String s) {
        int range = Integer.parseInt(s);
        int i;
        int j;
        int z;
        i = random.nextInt(range);
        j = random.nextInt(range);
        math.setArg1(i);
        math.setArg2(j);
        math.setArg3(z = i * j);
        return math;
    }
}
