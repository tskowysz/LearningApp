package com.example.applearning.model;

import org.springframework.stereotype.Component;

@Component
public class Math {
    Integer arg1;
    Integer arg2;
    Integer arg3;

    public Math() {
    }

    public Math(Integer arg1, Integer arg2, Integer arg3) {
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public Integer getArg1() {
        return arg1;
    }

    public void setArg1(Integer arg1) {
        this.arg1 = arg1;
    }

    public Integer getArg2() {
        return arg2;
    }

    public void setArg2(Integer arg2) {
        this.arg2 = arg2;
    }

    public Integer getArg3() {
        return arg3;
    }

    public void setArg3(Integer arg3) {
        this.arg3 = arg3;
    }

    @Override
    public String toString() {
        return "Math{" +
                "arg1=" + arg1 +
                ", arg2=" + arg2 +
                ", arg3=" + arg3 +
                '}';
    }
}
