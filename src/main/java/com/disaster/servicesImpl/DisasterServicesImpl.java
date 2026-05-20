package com.disaster.servicesImpl;

import com.disaster.entity.Disaster;
import com.disaster.services.DisasterServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/** DisasterServiceImpl — full CRUD implementation for Disaster entity. */
public class DisasterServicesImpl implements DisasterServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addDisaster(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Disaster d = new Disaster();

            System.out.print("Enter Type (Earthquake/Flood/Fire/Other) : ");
            d.setType(sc.nextLine().trim());

            System.out.print("Enter Location         : ");
            d.setLocation(sc.nextLine().trim());

            System.out.print("Enter Severity (LOW/MEDIUM/HIGH)     : ");
            d.setSeverity(sc.nextLine().trim().toUpperCase());

//            System.out.print("Enter Description                    : ");
//            d.setDescription(sc.nextLine().trim());

            d.setDateOccurred(new Date()); // today

            System.out.print("Enter Status (ACTIVE/MONITORING)     : ");
            d.setStatus(sc.nextLine().trim().toUpperCase());

            session.persist(d);
            tx.commit();
            System.out.println("Disaster added. ID = " + d.getDisasterId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding disaster: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Disaster getDisasterById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Disaster d = session.find(Disaster.class, id);
            if (d == null) System.out.println("No disaster found with ID: " + id);
            return d;
        }
    }

    @Override
    public List<Disaster> getAllDisasters(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Disaster> list = session.createQuery("FROM Disaster", Disaster.class).list();
            if (list.isEmpty()) System.out.println("No disasters found.");
            return list;
        }
    }

    @Override
    public void updateDisaster(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Disaster d = session.find(Disaster.class, id);
            if (d == null) {
                System.out.println("No disaster found with ID: " + id);
                return;
            }

            System.out.println("1. Type  2. Severity  3. Status");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{ 
                	System.out.print("New Type        : "); d.setType(sc.nextLine().trim()); }
                case 2:{ 
                	System.out.print("New Severity    : "); d.setSeverity(sc.nextLine().trim().toUpperCase()); }
                case 3:{
                	System.out.print("New Status      : "); d.setStatus(sc.nextLine().trim().toUpperCase()); }
//                case 4: {
//                	System.out.print("New Description : "); d.setDescription(sc.nextLine().trim()); }
                default:
                	System.out.println("Invalid choice.");
            }

            session.merge(d);
            tx.commit();
            System.out.println("Disaster updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating disaster: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteDisaster(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Disaster d = session.find(Disaster.class, id);
            if (d == null) {
                System.out.println("No disaster found with ID: " + id);
                return;
            }

            session.remove(d);
            tx.commit();
            System.out.println("Disaster deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting disaster: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
