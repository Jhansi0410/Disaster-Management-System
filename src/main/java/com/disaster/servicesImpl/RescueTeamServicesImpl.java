package com.disaster.servicesImpl;

import com.disaster.entity.Admin;
import com.disaster.entity.RescueTeam;
import com.disaster.services.RescueTeamServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

/** RescueTeamServiceImpl — full CRUD + assignTeam for RescueTeam entity. */
public class RescueTeamServicesImpl implements RescueTeamServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addTeam(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            RescueTeam team = new RescueTeam();

            System.out.print("Enter Team Name     : ");
            team.setTeamName(sc.nextLine().trim());

            System.out.print("Enter Description   : ");
            team.setDescription(sc.nextLine().trim());

            System.out.print("Enter Contact No    : ");
            team.setContactNo(sc.nextLine().trim());

            System.out.print("Enter Status (AVAILABLE/BUSY/STANDBY): ");
            team.setStatus(sc.nextLine().trim().toUpperCase());

            System.out.print("Enter Admin ID      : ");
            int adminId = Integer.parseInt(sc.nextLine().trim());
            Admin admin = session.find(Admin.class, adminId);
            if (admin == null) {
                System.out.println("Admin ID not found. Team not saved.");
                tx.rollback();
                return;
            }
            team.setAdmin(admin);

            session.persist(team);
            tx.commit();
            System.out.println("Rescue Team added. ID = " + team.getTeamId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding team: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public RescueTeam getTeamById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            RescueTeam team = session.find(RescueTeam.class, id);
            if (team == null) System.out.println("ℹ️  No team found with ID: " + id);
            return team;
        }
    }

    @Override
    public List<RescueTeam> getAllTeams(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<RescueTeam> list = session.createQuery("FROM RescueTeam", RescueTeam.class).list();
            if (list.isEmpty()) System.out.println("ℹ️  No teams found.");
            return list;
        }
    }

    @Override
    public void updateTeam(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            RescueTeam team = session.find(RescueTeam.class, id);
            if (team == null) {
                System.out.println("No team found with ID: " + id);
                return;
            }

            System.out.println("1. Team Name  2. Description  3. Contact No  4. Status");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{
                	System.out.print("New Team Name   : "); team.setTeamName(sc.nextLine().trim()); }
                case 2:{
                	System.out.print("New Description : "); team.setDescription(sc.nextLine().trim()); }
                case 3:{
                	System.out.print("New Contact No  : "); team.setContactNo(sc.nextLine().trim()); }
                case 4:{
                	System.out.print("New Status      : "); team.setStatus(sc.nextLine().trim().toUpperCase()); }
                default:
                	System.out.println("Invalid choice.");
            }

            session.merge(team);
            tx.commit();
            System.out.println("Rescue Team updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating team: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteTeam(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            RescueTeam team = session.find(RescueTeam.class, id);
            if (team == null) {
                System.out.println("No team found with ID: " + id);
                return;
            }

            session.remove(team);
            tx.commit();
            System.out.println("Rescue Team deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting team: " + e.getMessage());
        } finally {
            session.close();
        }
    }

	public void addRescueTeam(SessionFactory sf, Scanner sc2) {
		// TODO Auto-generated method stub
		
	}

	public void getAllRescueTeams(SessionFactory sf) {
		// TODO Auto-generated method stub
		
	}
}
