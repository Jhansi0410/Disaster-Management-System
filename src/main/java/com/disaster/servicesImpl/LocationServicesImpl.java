package com.disaster.servicesImpl;

import com.disaster.entity.Disaster;
import com.disaster.entity.Location;
import com.disaster.services.LocationServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
public class LocationServicesImpl implements LocationServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addLocation(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Location l = new Location();

            System.out.print("Enter Location Name : ");
            l.setName(sc.nextLine().trim());

            System.out.print("Enter State         : ");
            l.setState(sc.nextLine().trim());

            System.out.print("Enter Country       : ");
            l.setCountry(sc.nextLine().trim());

            System.out.print("Enter Pincode      : ");
            l.setPincode(sc.nextLine().trim());

            System.out.print("Enter Disaster ID   : ");
            int disasterId = Integer.parseInt(sc.nextLine().trim());
            Disaster disaster = session.find(Disaster.class, disasterId);
            if (disaster == null) {
                System.out.println("Disaster ID not found. Location not saved.");
                tx.rollback();
                return;
            }
            l.setDisaster(disaster);

            session.persist(l);
            tx.commit();
            System.out.println("Location added. ID = " + l.getLocationId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding location: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Location getLocationById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Location l = session.find(Location.class, id);
            if (l == null) System.out.println("No location found with ID: " + id);
            return l;
        }
    }

    @Override
    public List<Location> getAllLocations(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Location> list = session.createQuery("FROM Location", Location.class).list();
            if (list.isEmpty()) System.out.println("No locations found.");
            return list;
        }
    }

    @Override
    public void updateLocation(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Location l = session.find(Location.class, id);
            if (l == null) {
                System.out.println("No location found with ID: " + id);
                return;
            }

            System.out.println("1. Name  2. State  3. Country  4. Latitude  5. Longitude");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{
                	System.out.print("New Name      : "); l.setName(sc.nextLine().trim()); }
                case 2:{
                	System.out.print("New State     : "); l.setState(sc.nextLine().trim()); }
                case 3:{
                	System.out.print("New Country   : "); l.setCountry(sc.nextLine().trim()); }
                case4:{
                	System.out.print("Enter Pincode      : ");
                	l.setPincode(sc.nextLine().trim());
                }
                default:
                	System.out.println("Invalid choice.");
            }

            session.merge(l);
            tx.commit();
            System.out.println("Location updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating location: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteLocation(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Location l = session.find(Location.class, id);
            if (l == null) {
                System.out.println("No location found with ID: " + id);
                return;
            }

            session.remove(l);
            tx.commit();
            System.out.println("Location deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting location: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
