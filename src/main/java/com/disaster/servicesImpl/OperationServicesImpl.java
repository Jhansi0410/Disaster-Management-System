package com.disaster.servicesImpl;

import com.disaster.entity.Disaster;
import com.disaster.entity.Location;
import com.disaster.entity.Operation;
import com.disaster.entity.RescueTeam;
import com.disaster.services.OperationServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/** OperationServiceImpl — full CRUD implementation for Operation entity. */
public class OperationServicesImpl implements OperationServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addOperation(SessionFactory factory) {

        Session session = factory.openSession();

        Transaction tx = null;

        Scanner sc = new Scanner(System.in);

        try {

            tx = session.beginTransaction();

            Operation op = new Operation();

            // ================= DISASTER =================

            System.out.print("Enter Disaster ID   : ");

            int disasterId =
                    Integer.parseInt(sc.nextLine());

            Disaster disaster =
                    session.find(
                            Disaster.class,
                            disasterId);

            if (disaster == null) {

                System.out.println(
                        "Disaster not found!");

                return;
            }

            op.setDisaster(disaster);

            // ================= LOCATION =================

            System.out.print("Enter Location ID   : ");

            int locationId =
                    Integer.parseInt(sc.nextLine());

            Location location =
                    session.find(
                            Location.class,
                            locationId);

            if (location == null) {

                System.out.println(
                        "Location not found!");

                return;
            }

            op.setLocation(location);

            // ================= TEAM =================

            System.out.print("Enter Team ID       : ");

            int teamId =
                    Integer.parseInt(sc.nextLine());

            RescueTeam team =
                    session.find(
                            RescueTeam.class,
                            teamId);

            if (team == null) {

                System.out.println(
                        "Team not found!");

                return;
            }

            op.setTeam(team);

            // ================= STATUS =================

            System.out.print(
                    "Enter Status (ONGOING/COMPLETED): ");

            op.setStatus(
                    sc.nextLine()
                      .trim()
                      .toUpperCase());

            // ================= DATES =================

            op.setStartDate(new java.util.Date());

            

            op.setNotes("Operation Started");

            // ================= SAVE =================

            session.persist(op);

            // IMPORTANT
            session.flush();

            tx.commit();

            System.out.println(
                    "Operation added. ID = "
                            + op.getOperationId());

        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error adding operation: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }
    @Override
    public Operation getOperationById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Operation op = session.find(Operation.class, id);
            if (op == null) System.out.println("No operation found with ID: " + id);
            return op;
        }
    }

    @Override
    public List<Operation> getAllOperations(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Operation> list = session.createQuery("FROM Operation", Operation.class).list();
            if (list.isEmpty()) System.out.println("No operations found.");
            return list;
        }
    }

    @Override
    public void updateOperation(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Operation op = session.find(Operation.class, id);
            if (op == null) {
                System.out.println("No operation found with ID: " + id);
                return;
            }

            System.out.println("1. Status  ");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{
                	System.out.print("New Status : "); op.setStatus(sc.nextLine().trim().toUpperCase()); }
//                case 2:{
//                	System.out.print("New Notes  : "); op.setNotes(sc.nextLine().trim()); }
                
                default:
                	System.out.println("Invalid choice.");
            }

            session.merge(op);
            tx.commit();
            System.out.println(" Operation updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating operation: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteOperation(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Operation op = session.find(Operation.class, id);
            if (op == null) {
                System.out.println("No operation found with ID: " + id);
                return;
            }

            session.remove(op);
            tx.commit();
            System.out.println("Operation deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting operation: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}