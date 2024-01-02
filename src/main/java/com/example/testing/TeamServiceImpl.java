package com.example.testing;

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


    // todo CRUD Javalin
//    TeamRepository teamRepository = new TeamRepository();
//    TeamServiceImpl teamService = new TeamServiceImpl(teamRepository) ;
//
//        app.get("/getAll", ctx -> {
//        ctx.json(teamService.getAll());
//    });
//
//        app.get("/getById/{id}", ctx ->{
//        String id = ctx.pathParam("id");
//        Team team = teamService.findById(Integer.valueOf(id));
//        ctx.json(team);
//    });
//
//
//        app.post("create", ctx ->{
//        Team newTeam = ctx.bodyAsClass(Team.class);
//        teamService.create(newTeam);
//        ctx.status(201);
//    });
//
//        app.patch("edit/{id}", ctx ->{
//        String id = ctx.pathParam("id");
//        Team newTeam = ctx.bodyAsClass(Team.class);
//        teamService.edit(newTeam, Integer.valueOf(id));
//        ctx.status(204);
//    });
//
//        app.delete("delete/{id}",ctx -> {
//        String id = ctx.pathParam("id");
//        teamService.delete(Integer.valueOf(id));
//        ctx.status(204);
//    });
}
