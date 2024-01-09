package com.example.testing.step1.crud;

public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
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
