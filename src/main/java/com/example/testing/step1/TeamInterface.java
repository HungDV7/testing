package com.example.testing.step1;

import com.example.testing.step1.Team;

import java.util.List;

public interface TeamInterface {
    List<Team> getAll();

    Team findById(int id);

    void create(Team t);

    void edit(Team t, int id);

    void delete(int id);
}
