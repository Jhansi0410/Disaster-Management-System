package com.disaster.servicesImpl;

import com.disaster.entity.Admin;
import com.disaster.services.AdminServices;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

/** AdminServiceImpl — full CRUD implementation for Admin entity. */
public class AdminServicesImpl implements AdminServices {

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void addAdmin(SessionFactory factory) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Admin admin = new Admin();

            System.out.print("Enter Name     : ");
            admin.setName(sc.nextLine().trim());

            System.out.print("Enter Email    : ");
            admin.setEmail(sc.nextLine().trim());

            System.out.print("Enter Password : ");
            admin.setPassword(sc.nextLine().trim());

            System.out.print("Enter Phone    : ");
            admin.setPhone(sc.nextLine().trim());

            session.persist(admin);
            tx.commit();
            System.out.println("Admin added. ID = " + admin.getAdminId());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error adding admin: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public Admin getAdminById(SessionFactory factory, int id) {
        try (Session session = factory.openSession()) {
            Admin admin = session.find(Admin.class, id);
            if (admin == null) System.out.println("No admin found with ID: " + id);
            return admin;
        }
    }

    @Override
    public List<Admin> getAllAdmins(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            List<Admin> list = session.createQuery("FROM Admin", Admin.class).list();
            if (list.isEmpty()) System.out.println("No admins found.");
            return list;
        }
    }

    @Override
    public void updateAdmin(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Admin admin = session.find(Admin.class, id);
            if (admin == null) {
                System.out.println("ℹ️  No admin found with ID: " + id);
                return;
            }

            System.out.println("1. Name  2. Email  3. Password  4. Phone");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 :{
                	System.out.print("New Name     : "); admin.setName(sc.nextLine().trim()); }
                case 2 : {
                	System.out.print("New Email    : "); admin.setEmail(sc.nextLine().trim()); }
                case 3 : {
                	System.out.print("New Password : "); admin.setPassword(sc.nextLine().trim()); }
                case 4: {
                	System.out.print("New Phone    : "); admin.setPhone(sc.nextLine().trim()); }
                default :
                	System.out.println("Invalid choice.");
            }

            session.merge(admin);
            tx.commit();
            System.out.println("Admin updated.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error updating admin: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteAdmin(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Admin admin = session.find(Admin.class, id);
            if (admin == null) {
                System.out.println("No admin found with ID: " + id);
                return;
            }

            session.remove(admin);
            tx.commit();
            System.out.println("Admin deleted.");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error deleting admin: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
