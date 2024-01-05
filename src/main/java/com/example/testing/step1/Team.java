package com.example.testing.step1;

public class Team {
    private int id;
    private String name;
    private String area;


    public Team() {
    }

    public Team(int id, String name, String area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
