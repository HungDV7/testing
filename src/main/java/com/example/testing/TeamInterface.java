package com.example.testing;

import java.util.List;

public interface TeamInterface {
    List<Team> getAll();

    Team findById(int id);

    void create(Team t);

    void edit(Team t, int id);

    void delete(int id);
}
