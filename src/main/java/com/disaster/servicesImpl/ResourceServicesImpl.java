package com.disaster.servicesImpl;

import com.disaster.entity.Location;
import com.disaster.entity.Resource;
import com.disaster.services.ResourceServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
public class ResourceServicesImpl implements ResourceServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addResource(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Resource r = new Resource();

            System.out.print("Enter Resource Name : ");
            r.setName(sc.nextLine().trim());

            System.out.print("Enter Type (FOOD/MEDICINE/EQUIPMENT): ");
            r.setType(sc.nextLine().trim().toUpperCase());

            System.out.print("Enter Quantity      : ");
            r.setQuantity(Integer.parseInt(sc.nextLine().trim()));

            System.out.print("Enter Status (AVAILABLE/DEPLETED/RESERVED): ");
            r.setStatus(sc.nextLine().trim().toUpperCase());

            System.out.print("Enter Location ID   : ");
            int locationId = Integer.parseInt(sc.nextLine().trim());
            Location location = session.find(Location.class, locationId);
            if (location == null) {
                System.out.println("Location not found. Resource not saved.");
                tx.rollback();
                return;
            }
            r.setLocation(location);

            session.persist(r);
            tx.commit();
            System.out.println("Resource added. ID = " + r.getResourceId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding resource: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Resource getResourceById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Resource r = session.find(Resource.class, id);
            if (r == null) System.out.println("No resource found with ID: " + id);
            return r;
        }
    }

    @Override
    public List<Resource> getAllResources(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Resource> list = session.createQuery("FROM Resource", Resource.class).list();
            if (list.isEmpty()) System.out.println("No resources found.");
            return list;
        }
    }

    @Override
    public void updateResource(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Resource r = session.find(Resource.class, id);
            if (r == null) {
                System.out.println("No resource found with ID: " + id);
                return;
            }

            System.out.println("1. Name  2. Type  3. Quantity  4. Status");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{
                	System.out.print("New Name     : "); r.setName(sc.nextLine().trim()); }
                case 2 :{
                	System.out.print("New Type     : "); r.setType(sc.nextLine().trim().toUpperCase()); }
                case 3:{
                	System.out.print("New Quantity : "); r.setQuantity(Integer.parseInt(sc.nextLine().trim())); }
                case 4:{
                	System.out.print("New Status   : "); r.setStatus(sc.nextLine().trim().toUpperCase()); }
                default:
                	System.out.println("Invalid choice.");
            }
            session.merge(r);
            tx.commit();
            System.out.println("Resource updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating resource: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    @Override
    public void deleteResource(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Resource r = session.find(Resource.class, id);
            if (r == null) {
                System.out.println("No resource found with ID: " + id);
                return;
            }
            session.remove(r);
            tx.commit();
            System.out.println("Resource deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting resource: " + e.getMessage());
        } finally {
            session.close();
        }
    }

	public void addResource(SessionFactory sf, Scanner sc2) {
		// TODO Auto-generated method stub
		
	}
}
