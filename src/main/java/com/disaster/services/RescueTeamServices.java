package com.disaster.services;

import java.util.List;

import org.hibernate.SessionFactory;

import com.disaster.entity.RescueTeam;

/** Service contract for RescueTeam CRUD operations. */
public interface RescueTeamServices {

    void addTeam(SessionFactory factory);

    RescueTeam getTeamById(SessionFactory factory, int id);

    List<RescueTeam> getAllTeams(SessionFactory factory);

    void updateTeam(SessionFactory factory, int id);

    void deleteTeam(SessionFactory factory, int id);

}
