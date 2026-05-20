//package com.disaster.servicesImpl;
//
//
//import com.disaster.entity.User;
//import com.disaster.services.UserServices;
//import com.disaster.util.DuplicateUserException;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class UserServicesImpl implements UserServices {
//
//    // ─────────────────────────────────────────────────────────────
//    // ADD USER
//    // ─────────────────────────────────────────────────────────────
//    @Override
//    public void addUser(SessionFactory factory, Scanner sc) {
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//
//            tx = session.beginTransaction();
//
//            User user = new User();
//
//            System.out.print("Enter Name        : ");
//            user.setName(sc.nextLine().trim());
//
//            System.out.print("Enter Email       : ");
//            user.setEmail(sc.nextLine().trim());
//
//            System.out.print("Enter Password    : ");
//            user.setPassword(sc.nextLine().trim());
//
//            System.out.print("Enter Phone       : ");
//            user.setPhone(sc.nextLine().trim());
//
//            System.out.print("Enter Address     : ");
//            user.setAddress(sc.nextLine().trim());
//
//            System.out.print("Enter Role (CITIZEN/VOLUNTEER): ");
//            user.setRole(sc.nextLine().trim().toUpperCase());
//
//            session.persist(user);
//
//            tx.commit();
//
//            System.out.println("User added successfully. ID = " + user.getUserId());
//
//        } catch (Exception e) {
//
//            if (tx != null)
//                tx.rollback();
//
//            System.out.println("Error adding user: " + e.getMessage());
//
//        } finally {
//
//            session.close();
//        }
//    }
//
//    // ─────────────────────────────────────────────────────────────
//    // GET USER BY ID
//    // ─────────────────────────────────────────────────────────────
//    @Override
//    public User getUserById(SessionFactory factory, int id)
//            throws DuplicateUserException {
//
//        try (Session session = factory.openSession()) {
//
//            User user = session.find(User.class, id);
//
//            if (user == null) {
//                throw new DuplicateUserException(
//                        "User not found with ID: " + id);
//            }
//
//            return user;
//        }
//    }
//
//    // ─────────────────────────────────────────────────────────────
//    // GET ALL USERS
//    // ─────────────────────────────────────────────────────────────
//    @Override
//    public List<User> getAllUsers(SessionFactory factory) {
//
//        try (Session session = factory.openSession()) {
//
//            List<User> users =
//                    session.createQuery("FROM User", User.class).list();
//
//            if (users.isEmpty()) {
//
//                System.out.println("No users found.");
//
//            } else {
//
//                System.out.println("\n──── ALL USERS ────");
//
//                for (User user : users) {
//
//                    System.out.println("--------------------------------");
//                    System.out.println("ID       : " + user.getUserId());
//                    System.out.println("Name     : " + user.getName());
//                    System.out.println("Email    : " + user.getEmail());
//                    System.out.println("Phone    : " + user.getPhone());
//                    System.out.println("Address  : " + user.getAddress());
//                    System.out.println("Role     : " + user.getRole());
//                }
//            }
//
//            return users;
//        }
//    }
//
//    // ─────────────────────────────────────────────────────────────
//    // UPDATE USER
//    // ─────────────────────────────────────────────────────────────
//    @Override
//    public void updateUser(SessionFactory factory, int id, Scanner sc)
//            throws DuplicateUserException {
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//
//            tx = session.beginTransaction();
//
//            User user = session.find(User.class, id);
//
//            if (user == null) {
//                throw new DuplicateUserException(
//                        "User not found with ID: " + id);
//            }
//
//            System.out.println("\n──── UPDATE USER ────");
//            System.out.println("1. Name");
//            System.out.println("2. Email");
//            System.out.println("3. Password");
//            System.out.println("4. Phone");
//            System.out.println("5. Address");
//            System.out.println("6. Role");
//
//            System.out.print("Enter choice: ");
//
//            int choice =
//                    Integer.parseInt(sc.nextLine().trim());
//
//            switch (choice) {
//
//                case 1:
//                    System.out.print("New Name : ");
//                    user.setName(sc.nextLine().trim());
//                    break;
//
//                case 2:
//                    System.out.print("New Email : ");
//                    user.setEmail(sc.nextLine().trim());
//                    break;
//
//                case 3:
//                    System.out.print("New Password : ");
//                    user.setPassword(sc.nextLine().trim());
//                    break;
//
//                case 4:
//                    System.out.print("New Phone : ");
//                    user.setPhone(sc.nextLine().trim());
//                    break;
//
//                case 5:
//                    System.out.print("New Address : ");
//                    user.setAddress(sc.nextLine().trim());
//                    break;
//
//                case 6:
//                    System.out.print("New Role : ");
//                    user.setRole(sc.nextLine().trim().toUpperCase());
//                    break;
//
//                default:
//                    System.out.println("Invalid choice.");
//                    return;
//            }
//
//            session.merge(user);
//
//            tx.commit();
//
//            System.out.println("User updated successfully.");
//
//        } catch (DuplicateUserException e) {
//
//            if (tx != null)
//                tx.rollback();
//
//            throw e;
//
//        } catch (Exception e) {
//
//            if (tx != null)
//                tx.rollback();
//
//            System.out.println("Error updating user: " + e.getMessage());
//
//        } finally {
//
//            session.close();
//        }
//    }
//
//    // ─────────────────────────────────────────────────────────────
//    // DELETE USER
//    // ─────────────────────────────────────────────────────────────
//    @Override
//    public void deleteUser(SessionFactory factory, int id)
//            throws DuplicateUserException {
//
//        Session session = factory.openSession();
//        Transaction tx = null;
//
//        try {
//
//            tx = session.beginTransaction();
//
//            User user = session.find(User.class, id);
//
//            if (user == null) {
//
//                throw new DuplicateUserException(
//                        "User not found with ID: " + id);
//            }
//
//            session.remove(user);
//
//            tx.commit();
//
//            System.out.println("User deleted successfully.");
//
//        } catch (DuplicateUserException e) {
//
//            if (tx != null)
//                tx.rollback();
//
//            throw e;
//
//        } catch (Exception e) {
//
//            if (tx != null)
//                tx.rollback();
//
//            System.out.println("Error deleting user: " + e.getMessage());
//
//        } finally {
//
//            session.close();
//        }
//    }
//
//	@Override
//	public void addUser(SessionFactory factory) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateUser(SessionFactory factory, int id) throws DuplicateUserException {
//		// TODO Auto-generated method stub
//		
//	}
//}



package com.disaster.servicesImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.disaster.entity.User;
import com.disaster.services.UserServices;
import com.disaster.util.DuplicateUserException;

public class UserServicesImpl implements UserServices {

    // =====================================================
    // ADD USER
    // =====================================================

    @Override
    public void addUser(SessionFactory factory, Scanner sc)
            throws DuplicateUserException {

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            User user = new User();

            System.out.print("Enter Name        : ");
            user.setName(sc.nextLine().trim());

            System.out.print("Enter Email       : ");
            String email = sc.nextLine().trim();

            // CHECK EMAIL ALREADY EXISTS

            List<User> existingUsers =
                    session.createQuery(
                            "FROM User WHERE email = :email",
                            User.class)
                            .setParameter("email", email)
                            .list();

            if (!existingUsers.isEmpty()) {

                throw new DuplicateUserException(
                        "Email already exists!");
            }

            user.setEmail(email);

            System.out.print("Enter Password    : ");
            user.setPassword(sc.nextLine().trim());

            System.out.print("Enter Phone       : ");
            user.setPhone(sc.nextLine().trim());

            System.out.print("Enter Address     : ");
            user.setAddress(sc.nextLine().trim());

            System.out.print("Enter Role (CITIZEN/VOLUNTEER): ");
            user.setRole(
                    sc.nextLine().trim().toUpperCase());

            session.persist(user);

            tx.commit();

            System.out.println(
                    "User added successfully. ID = "
                            + user.getUserId());
        }

        catch (DuplicateUserException e) {

            if (tx != null)
                tx.rollback();

            throw e;
        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error adding user: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }
    // =====================================================
    // GET USER BY ID
    // =====================================================

    @Override
    public User getUserById(
            SessionFactory factory,
            int id)

            throws DuplicateUserException {

        Session session = factory.openSession();

        User user = session.find(User.class, id);

        session.close();

        if (user == null) {

            throw new DuplicateUserException(
                    "User not found!");
        }

        return user;
    }

    // =====================================================
    // GET ALL USERS
    // =====================================================

    @Override
    public List<User> getAllUsers(
            SessionFactory factory) {

        Session session = factory.openSession();

        List<User> users =
                session.createQuery(
                        "FROM User",
                        User.class)
                        .list();

        session.close();

        return users;
    }

    // =====================================================
    // UPDATE USER
    // =====================================================

    @Override
    public void updateUser(
            SessionFactory factory,
            int id)

            throws DuplicateUserException {

        Scanner sc = new Scanner(System.in);

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            User user =
                    session.find(User.class, id);

            if (user == null) {

                throw new DuplicateUserException(
                        "User not found!");
            }

            System.out.println("\n1. Update Name");
            System.out.println("2. Update Email");
            System.out.println("3. Update Password");
            System.out.println("4. Update Phone");
            System.out.println("5. Update Address");
            System.out.println("6. Update Role");

            System.out.print("Enter Choice : ");

            int choice =
                    Integer.parseInt(
                            sc.nextLine());

            switch (choice) {

            case 1:

                System.out.print(
                        "Enter New Name : ");

                user.setName(
                        sc.nextLine());

                break;

            case 2:

                System.out.print(
                        "Enter New Email : ");

                String email =
                        sc.nextLine();

                List<User> existingUsers =
                        session.createQuery(
                                "FROM User WHERE email=:email",
                                User.class)
                                .setParameter(
                                        "email",
                                        email)
                                .list();

                if (!existingUsers.isEmpty()) {

                    throw new DuplicateUserException(
                            "Email already exists!");
                }

                user.setEmail(email);

                break;

            case 3:

                System.out.print(
                        "Enter New Password : ");

                user.setPassword(
                        sc.nextLine());

                break;

            case 4:

                System.out.print(
                        "Enter New Phone : ");

                user.setPhone(
                        sc.nextLine());

                break;

            case 5:

                System.out.print(
                        "Enter New Address : ");

                user.setAddress(
                        sc.nextLine());

                break;

            case 6:

                System.out.print(
                        "Enter New Role : ");

                user.setRole(
                        sc.nextLine()
                          .toUpperCase());

                break;

            default:

                System.out.println(
                        "Invalid Choice!");
            }

            session.merge(user);

            tx.commit();

            System.out.println(
                    "User updated successfully.");
        }

        catch (DuplicateUserException e) {

            if (tx != null)
                tx.rollback();

            throw e;
        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error updating user: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }

    // =====================================================
    // DELETE USER
    // =====================================================

    @Override
    public void deleteUser(
            SessionFactory factory,
            int id)

            throws DuplicateUserException {

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            User user =
                    session.find(User.class, id);

            if (user == null) {

                throw new DuplicateUserException(
                        "User not found!");
            }

            session.remove(user);

            tx.commit();

            System.out.println(
                    "User deleted successfully.");
        }

        catch (DuplicateUserException e) {

            if (tx != null)
                tx.rollback();

            throw e;
        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error deleting user: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }

    // =====================================================
    // EMPTY METHODS
    // =====================================================

    @Override
    public void addUser(SessionFactory factory) {

    }

    public void updateUser1(
            SessionFactory factory,
            int id)

            throws DuplicateUserException {

    }

	@Override
	public void updateUser(SessionFactory factory, int id, Scanner sc) throws DuplicateUserException {
		// TODO Auto-generated method stub
		
	}
}