package com.example.testing.step1.crud;

import com.example.testing.step1.crud.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAll();

    Team findById(int id);

    void create(Team t);

    void edit(Team t, int id);

    void delete(int id);
}
