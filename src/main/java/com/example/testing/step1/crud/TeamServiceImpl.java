package com.example.testing.step1.crud;

import com.example.testing.step1.crud.Team;
import com.example.testing.step1.crud.TeamRepository;
import com.example.testing.step1.crud.TeamService;

import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private List<Team> listTeam;



    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.listTeam = new ArrayList<>();
    }


    @Override
    public List<Team> getAll() {
        return teamRepository.getAll();
    }

    @Override
    public Team findById(int id) {
        if(teamRepository.findById(id) == null){
            return null;
        }
        return teamRepository.findById(id);
    }

    @Override
    public void create(Team t) {
        teamRepository.create(t);
    }

    @Override
    public void edit(Team t, int id) {
        teamRepository.edit(t, id);
    }

    @Override
    public void delete(int id) {
        teamRepository.delete(id);
    }
}
