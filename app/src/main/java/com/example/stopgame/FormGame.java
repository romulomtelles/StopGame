package com.example.stopgame;

public class FormGame {

    private String stopGame, name, cep, color, food, animal, car, object;

    public FormGame(String stopGame, String name, String cep, String color, String food, String animal, String car, String object) {
        this.stopGame = stopGame;
        this.name = name;
        this.cep = cep;
        this.color = color;
        this.food = food;
        this.animal = animal;
        this.car = car;
        this.object = object;
    }

    public String getStopGame() {
        return stopGame;
    }

    public void setStopGame(String stopGame) {
        this.stopGame = stopGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
