package com.example.testclickimage;

public class State {

    private String name;
    private int color;

    public State(String name,int color) {
        this.name = name;
        this.color = color;
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
