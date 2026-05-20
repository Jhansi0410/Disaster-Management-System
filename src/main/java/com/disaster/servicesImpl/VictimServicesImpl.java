//package com.disaster.servicesImpl;
//
//import com.disaster.entity.Operation;
//import com.disaster.entity.Victim;
//import com.disaster.services.VictimServices;
//import com.disaster.util.VictimNotFoundException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import java.util.List;
//import java.util.Scanner;
//
///** VictimServiceImpl — full CRUD implementation for Victim entity. */
//public class VictimServicesImpl implements VictimServices {
//
//    private final Scanner sc = new Scanner(System.in);
//
//    @Override
//    public void addVictim(SessionFactory factory) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            Victim v = new Victim();
//
//            System.out.print("Enter Name           : ");
//            v.setName(sc.nextLine().trim());
//
//            System.out.print("Enter Age            : ");
//            v.setAge(Integer.parseInt(sc.nextLine().trim()));
//
//            System.out.print("Enter Gender (M/F/O) : ");
//            v.setGender(sc.nextLine().trim().toUpperCase());
//
//            System.out.print("Enter Injury Details : ");
//            v.setInjuryDetails(sc.nextLine().trim());
//
//            System.out.print("Enter Status (RESCUED/HOSPITALIZED/MISSING/DECEASED): ");
//            v.setStatus(sc.nextLine().trim().toUpperCase());
//
//            System.out.print("Enter Operation ID   : ");
//            int opId = Integer.parseInt(sc.nextLine().trim());
//            Operation op = session.find(Operation.class, opId);
//            if (op == null) {
//                System.out.println("Operation not found. Victim not saved.");
//                tx.rollback();
//                return;
//            }
//            v.setOperation(op);
//
//            session.persist(v);
//            tx.commit();
//            System.out.println("Victim added. ID = " + v.getVictimId());
//
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            System.err.println("Error adding victim: " + e.getMessage());
//        } finally {
//            session.close();
//        }
//    }
//
//    /** Retrieves Victim by ID, throws VictimNotFoundException if missing. */
//    @Override
//    public Victim getVictimById(SessionFactory factory, long l) throws VictimNotFoundException {
//        try (Session session = factory.openSession()) {
//            Victim v = session.find(Victim.class, l);
//            if (v == null) {
//                throw new VictimNotFoundException("Victim not found with ID: " + l);
//            }
//            return v;
//        }
//    }
//
//    @Override
//    public List<Victim> getAllVictims(SessionFactory factory) {
//        try (Session session = factory.openSession()) {
//            List<Victim> list = session.createQuery("FROM Victim", Victim.class).list();
//            if (list.isEmpty()) System.out.println("No victims found.");
//            return list;
//        }
//    }
//
//    @Override
//    public void updateVictim(SessionFactory factory, int id) throws VictimNotFoundException {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            Victim v = session.find(Victim.class, id);
//            if (v == null) {
//                throw new VictimNotFoundException("Victim not found with ID: " + id);
//            }
//
//            System.out.println("1. Name  2. Age  3. Gender  4. Injury Details  5. Status");
//            System.out.print("Enter choice: ");
//            int choice = Integer.parseInt(sc.nextLine().trim());
//
//            switch (choice) {
//                case 1:{
//                	System.out.print("New Name           : "); v.setName(sc.nextLine().trim()); }
//                case 2: {
//                	System.out.print("New Age            : "); v.setAge(Integer.parseInt(sc.nextLine().trim())); }
//                case 3 : {
//                	System.out.print("New Gender         : "); v.setGender(sc.nextLine().trim().toUpperCase()); }
//                case 4: {
//                	System.out.print("New Injury Details : "); v.setInjuryDetails(sc.nextLine().trim()); }
//                case 5: { 
//                	System.out.print("New Status         : "); v.setStatus(sc.nextLine().trim().toUpperCase()); }
//                default:
//                	System.out.println("Invalid choice.");
//            }
//
//            session.merge(v);
//            tx.commit();
//            System.out.println("Victim updated.");
//
//        } catch (VictimNotFoundException e) {
//            if (tx != null) tx.rollback();
//            throw e;
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            System.err.println("Error updating victim: " + e.getMessage());
//        } finally {
//            session.close();
//        }
//    }
//
//    @Override
//    public void deleteVictim(SessionFactory factory, long l) throws VictimNotFoundException {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            Victim v = session.find(Victim.class, l);
//            if (v == null) {
//                throw new VictimNotFoundException("Victim not found with ID: " + l);
//            }
//
//            session.remove(v);
//            tx.commit();
//            System.out.println("Victim deleted.");
//
//        } catch (VictimNotFoundException e) {
//            if (tx != null) tx.rollback();
//            throw e;
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            System.err.println("Error deleting victim: " + e.getMessage());
//        } finally {
//            session.close();
//        }
//    }
//
//	public void addVictim(SessionFactory sf, Scanner sc2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteVictim(SessionFactory factory, int id) throws VictimNotFoundException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void updateVictim(SessionFactory factory, long long1, Scanner sc2) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Victim getVictimById(SessionFactory factory, int id) throws VictimNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}




package com.disaster.servicesImpl;

import com.disaster.entity.Operation;
import com.disaster.entity.Victim;
import com.disaster.services.VictimServices;
import com.disaster.util.VictimNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class VictimServicesImpl implements VictimServices {

    Scanner sc = new Scanner(System.in);

    // =====================================================
    // ADD VICTIM
    // =====================================================

    @Override
    public void addVictim(SessionFactory factory, Scanner sc) {

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim v = new Victim();

            System.out.print("Enter Victim Name : ");
            v.setName(sc.nextLine());

            System.out.print("Enter Age : ");
            v.setAge(Integer.parseInt(sc.nextLine()));

            System.out.print("Enter Gender : ");
            v.setGender(sc.nextLine());

            System.out.print("Enter Injury Details : ");
            v.setInjuryDetails(sc.nextLine());

            System.out.print("Enter Status : ");
            v.setStatus(sc.nextLine());

            System.out.print("Enter Operation ID : ");
            long opId = Long.parseLong(sc.nextLine());

            Operation op =
                    session.find(Operation.class, opId);

            if (op == null) {

                System.out.println(
                        "Operation not found!");

                return;
            }

            v.setOperation(op);

            session.persist(v);

            tx.commit();

            System.out.println(
                    "Victim added successfully.");

        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error : " + e.getMessage());
        }

        finally {

            session.close();
        }
    }

    // =====================================================
    // GET VICTIM BY ID
    // =====================================================

    @Override
    public Victim getVictimById(
            SessionFactory factory,
            long id)

            throws VictimNotFoundException {

        Session session = factory.openSession();

        Victim victim =
                session.find(Victim.class, id);

        session.close();

        if (victim == null) {

            throw new VictimNotFoundException(
                    "Victim not found with ID: " + id);
        }

        return victim;
    }

    // =====================================================
    // GET ALL VICTIMS
    // =====================================================

    @Override
    public List<Victim> getAllVictims(
            SessionFactory factory) {

        Session session = factory.openSession();

        List<Victim> victims =
                session.createQuery(
                        "FROM Victim",
                        Victim.class)
                        .list();

        session.close();

        return victims;
    }

    // =====================================================
    // UPDATE VICTIM
    // =====================================================

    @Override
    public void updateVictim(
            SessionFactory factory,
            int id)

            throws VictimNotFoundException {

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim v =
                    session.find(Victim.class, id);

            if (v == null) {

                throw new VictimNotFoundException(
                        "Victim not found with ID: "
                                + id);
            }

            System.out.println("\n1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Gender");
            System.out.println("4. Update Injury");
            System.out.println("5. Update Status");

            System.out.print("Enter Choice : ");

            int choice =
                    Integer.parseInt(
                            sc.nextLine());

            switch (choice) {

            case 1:

                System.out.print(
                        "Enter New Name : ");

                v.setName(
                        sc.nextLine().trim());

                break;

            case 2:

                System.out.print(
                        "Enter New Age : ");

                v.setAge(
                        Integer.parseInt(
                                sc.nextLine()));

                break;

            case 3:

                System.out.print(
                        "Enter New Gender : ");

                v.setGender(
                        sc.nextLine()
                          .trim()
                          .toUpperCase());

                break;

            case 4:

                System.out.print(
                        "Enter New Injury : ");

                v.setInjuryDetails(
                        sc.nextLine().trim());

                break;

            case 5:

                System.out.print(
                        "Enter New Status : ");

                v.setStatus(
                        sc.nextLine()
                          .trim()
                          .toUpperCase());

                break;

            default:

                System.out.println(
                        "Invalid Choice!");
            }

            session.merge(v);

            tx.commit();

            System.out.println(
                    "Victim updated successfully.");
        }

        catch (VictimNotFoundException e) {

            if (tx != null)
                tx.rollback();

            throw e;
        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error updating victim: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }

    // =====================================================
    // DELETE VICTIM
    // =====================================================

    @Override
    public void deleteVictim(
            SessionFactory factory,
            long id)

            throws VictimNotFoundException {

        Session session = factory.openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim v =
                    session.find(Victim.class, id);

            if (v == null) {

                throw new VictimNotFoundException(
                        "Victim not found with ID: "
                                + id);
            }

            session.remove(v);

            tx.commit();

            System.out.println(
                    "Victim deleted successfully.");
        }

        catch (VictimNotFoundException e) {

            if (tx != null)
                tx.rollback();

            throw e;
        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println(
                    "Error deleting victim: "
                            + e.getMessage());
        }

        finally {

            session.close();
        }
    }

	@Override
	public Victim getVictimById(SessionFactory factory, int id) throws VictimNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVictim(SessionFactory factory, int id) throws VictimNotFoundException {
		// TODO Auto-generated method stub
		
	}

	

	public void updateVictim(SessionFactory factory, long long1, Scanner sc2) {
		// TODO Auto-generated method stub
		
	}
}
