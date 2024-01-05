package com.example.testing.step1;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements TeamInterface{

    private final List<Team> listTeam ;

    public TeamRepository() {
        listTeam = new ArrayList<>();
        listTeam.add(new Team(0,"T1","LCK"));
        listTeam.add(new Team(1,"G2","LEC"));
        listTeam.add(new Team(2,"JDG","LPL"));
    }

    @Override
    public List<Team> getAll() {
        return listTeam;
    }

    @Override
    public Team findById(int id) {
        return listTeam.get(id);
    }

    @Override
    public void create(Team t) {
        listTeam.add(t);
    }

    @Override
    public void edit(Team t, int id) {
        listTeam.set(id, t);
    }

    @Override
    public void delete(int id) {
        listTeam.remove(id);
    }
}
