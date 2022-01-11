package com.github.springredis.model;
/**
 * @author Mohamed Anouar BENCHEIKH
 * @project springredis
 */
public class Employee {
    private String name;
    private String function;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}
