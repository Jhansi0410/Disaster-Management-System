package com.disaster.servicesImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.disaster.entity.User;
import com.disaster.services.UserServiceExp;
import com.disaster.services.UserServices;
import com.disaster.util.DuplicateUserException;
import com.disaster.util.DuplicateUserException;

public class UserServiceImplExp implements UserServiceExp {

    // ================= ADD USER =================

	@Override
	public void addUser(SessionFactory factory, Scanner sc)
	        throws DuplicateUserException {

	    Session session = factory.openSession();
	    Transaction tx = null;

	    try {

	        tx = session.beginTransaction();

	        User user = new User();

	        System.out.print("Enter Name : ");
	        user.setName(sc.nextLine());

	        System.out.print("Enter Email : ");
	        user.setEmail(sc.nextLine());

	        System.out.print("Enter Password : ");
	        user.setPassword(sc.nextLine());

	        System.out.print("Enter Phone : ");
	        user.setPhone(sc.nextLine());

	        System.out.print("Enter Address : ");
	        user.setAddress(sc.nextLine());

	        System.out.print("Enter Role : ");
	        user.setRole(sc.nextLine().toUpperCase());

	        session.persist(user);

	        tx.commit();

	        System.out.println("User Added Successfully");

	    }

	    catch (Exception e) {

	        if (tx != null)
	            tx.rollback();

	        // CHECK DUPLICATE EMAIL
	        if (e.getCause() != null &&
	            e.getCause().getMessage().contains("Duplicate entry")) {

	            throw new DuplicateUserException(
	                    "Email already exists!");
	        }

	        else {

	            System.out.println(
	                    "Error adding user: "
	                    + e.getMessage());
	        }
	    }

	    finally {

	        session.close();
	    }
	}
    // ================= GET USER =================

    @Override
    public User getUserById(SessionFactory factory, int id)
            throws DuplicateUserException {

        Session session = factory.openSession();

        User user = session.find(User.class, id);

        session.close();

        if (user == null) {

            throw new DuplicateUserException(
                    "User Not Found!");
        }

        return user;
    }

    // ================= GET ALL USERS =================

    @Override
    public List<User> getAllUsers(SessionFactory factory) {

        Session session = factory.openSession();

        List<User> users =
                session.createQuery(
                        "from User",
                        User.class).list();

        session.close();

        return users;
    }

    // ================= UPDATE USER =================

    @Override
    public void updateUser(SessionFactory factory, int id)
            throws DuplicateUserException {

        Scanner sc = new Scanner(System.in);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            User user = session.find(User.class, id);

            if (user == null) {

                throw new DuplicateUserException(
                        "User Not Found!");
            }

            System.out.println("1. Name");
            System.out.println("2. Email");
            System.out.println("3. Phone");
            System.out.println("4. Address");
            System.out.println("5. Role");

            System.out.print("Enter Choice : ");

            int choice =
                    Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    System.out.print("Enter New Name : ");
                    user.setName(sc.nextLine());
                    break;

                case 2:
                    System.out.print("Enter New Email : ");
                    user.setEmail(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Enter New Phone : ");
                    user.setPhone(sc.nextLine());
                    break;

                case 4:
                    System.out.print("Enter New Address : ");
                    user.setAddress(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter New Role : ");
                    user.setRole(
                            sc.nextLine().toUpperCase());
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

            session.merge(user);

            tx.commit();

            System.out.println("User Updated Successfully");

        } catch (Exception e) {

            tx.rollback();

            System.out.println(e.getMessage());

        } finally {

            session.close();
        }
    }

    // ================= DELETE USER =================

    @Override
    public void deleteUser(SessionFactory factory, int id)
            throws DuplicateUserException {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            User user = session.find(User.class, id);

            if (user == null) {

                throw new DuplicateUserException(
                        "User Not Found!");
            }

            session.remove(user);

            tx.commit();

            System.out.println("User Deleted Successfully");

        } catch (Exception e) {

            tx.rollback();

            System.out.println(e.getMessage());

        } finally {

            session.close();
        }
    }
}