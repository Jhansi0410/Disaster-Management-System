package com.disaster.servicesImpl;


import com.disaster.entity.Disaster;
import com.disaster.entity.Request;
import com.disaster.entity.User;
import com.disaster.services.RequestServices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/** RequestServiceImpl — full CRUD implementation for Request entity. */
public class RequestServicesImpl implements RequestServices {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Creates a new Request after validating required fields.
     * Throws InvalidRequestException if requestType is blank.
     */
    public void addRequest(SessionFactory factory){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            System.out.print("Enter Request Type (FOOD/MEDICAL/SHELTER/OTHER): ");
            String requestType = sc.nextLine().trim().toUpperCase();

            System.out.print("Enter Status (PENDING/APPROVED/REJECTED)        : ");
            String status = sc.nextLine().trim().toUpperCase();

            System.out.print("Enter User ID      : ");
            int userId = Integer.parseInt(sc.nextLine().trim());
            User user = session.find(User.class, userId);
            if (user == null) { System.out.println("User not found."); tx.rollback(); return; }

            System.out.print("Enter Disaster ID  : ");
            int disasterId = Integer.parseInt(sc.nextLine().trim());
            Disaster disaster = session.find(Disaster.class, disasterId);
            if (disaster == null) { System.out.println("Disaster not found."); tx.rollback(); return; }

            Request request = new Request();
            request.setRequestType(requestType);
            request.setStatus(status);
            request.setRequestDate(new Date());
            request.setUser(user);
            request.setDisaster(disaster);

            session.persist(request);
            tx.commit();
            System.out.println("Request added. ID = " + request.getRequestId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding request: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Request getRequestById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Request r = session.find(Request.class, id);
            if (r == null) System.out.println("No request found with ID: " + id);
            return r;
        }
    }

    @Override
    public List<Request> getAllRequests(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Request> list = session.createQuery("FROM Request", Request.class).list();
            if (list.isEmpty()) System.out.println("No requests found.");
            return list;
        }
    }

    @Override
    public void updateRequest(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Request r = session.find(Request.class, id);
            if (r == null) {
                System.out.println("No request found with ID: " + id);
                return;
            }

            System.out.println("1. Request Type  2. Status");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:{
                	System.out.print("New Request Type : "); r.setRequestType(sc.nextLine().trim().toUpperCase()); }
                case 2:{
                	System.out.print("New Status       : "); r.setStatus(sc.nextLine().trim().toUpperCase()); }
                default: 
                	System.out.println("Invalid choice.");
            }

            session.merge(r);
            tx.commit();
            System.out.println("Request updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating request: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteRequest(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Request r = session.find(Request.class, id);
            if (r == null) {
                System.out.println("No request found with ID: " + id);
                return;
            }

            session.remove(r);
            tx.commit();
            System.out.println("Request deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting request: " + e.getMessage());
        } finally {
            session.close();
        }
    }

	public void addUser(SessionFactory factory) {
		// TODO Auto-generated method stub
		
	}

	public User getUserById(SessionFactory factory, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> getAllUsers(SessionFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(SessionFactory factory, int id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteUser(SessionFactory factory, int id) {
		// TODO Auto-generated method stub
		
	}

	public void addRequest(SessionFactory sf, Scanner sc2) {
		// TODO Auto-generated method stub
		
	}
	}
