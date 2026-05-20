// =============================
// VictimServicesImpl.java
// =============================

package com.disaster.servicesImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.disaster.entity.Victim;
import com.disaster.entity.VictimExp;
import com.disaster.services.VictimServiceExp;
import com.disaster.services.VictimServices;
import com.disaster.util.VictimNotFoundException;

public class VictimServiceImplExp implements VictimServiceExp {

    // =========================================
    // ADD VICTIM
    // =========================================

    @Override
    public void addVictim(SessionFactory factory, Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim victim = new Victim();

            System.out.print("Enter Victim Name : ");
            victim.setName(sc.nextLine());

            System.out.print("Enter Age : ");
            victim.setAge(Integer.parseInt(sc.nextLine()));

            System.out.print("Enter Gender : ");
            victim.setGender(sc.nextLine());

            System.out.print("Enter Status : ");
            victim.setStatus(sc.nextLine());

            session.persist(victim);

            tx.commit();

            System.out.println("Victim Added Successfully.");

        }

        catch (Exception e) {

            if (tx != null)
                tx.rollback();

            System.out.println("Error : " + e.getMessage());
        }

        finally {

            session.close();
        }
    }

    // =========================================
    // GET VICTIM BY ID
    // =========================================

    @Override
    public Victim getVictimById(SessionFactory factory, long id) {

        Session session = factory.openSession();

        Victim victim = session.find(Victim.class, id);

        session.close();

        if (victim == null) {

            throw new VictimNotFoundException(id);
        }

        return victim;
    }

    // =========================================
    // GET ALL VICTIMS
    // =========================================

    @Override
    public List<Victim> getAllVictims(SessionFactory factory) {

        Session session = factory.openSession();

        List<Victim> victims =
                session.createQuery(
                        "from Victim",
                        Victim.class)
                        .list();

        session.close();

        return victims;
    }

    // =========================================
    // UPDATE VICTIM
    // =========================================

    @Override
    public void updateVictim(
            SessionFactory factory,
            long id,
            Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim victim =
                    session.find(Victim.class, id);

            if (victim == null) {

                throw new VictimNotFoundException(id);
            }

            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Gender");
            System.out.println("4. Update Status");

            System.out.print("Enter Choice : ");

            int choice =
                    Integer.parseInt(sc.nextLine());

            switch (choice) {

            case 1:

                System.out.print("Enter New Name : ");
                victim.setName(sc.nextLine());

                break;

            case 2:

                System.out.print("Enter New Age : ");
                victim.setAge(
                        Integer.parseInt(sc.nextLine()));

                break;

            case 3:

                System.out.print("Enter New Gender : ");
                victim.setGender(sc.nextLine());

                break;

            case 4:

                System.out.print("Enter New Status : ");
                victim.setStatus(sc.nextLine());

                break;

            default:

                System.out.println("Invalid Choice!");
            }

            session.merge(victim);

            tx.commit();

            System.out.println(
                    "Victim Updated Successfully.");
        }

        catch (VictimNotFoundException e) {

            if (tx != null)
                tx.rollback();

            System.out.println(e.getMessage());
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

    // =========================================
    // DELETE VICTIM
    // =========================================

    @Override
    public void deleteVictim(
            SessionFactory factory,
            long id) {

        Session session = factory.openSession();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Victim victim =
                    session.find(Victim.class, id);

            if (victim == null) {

                throw new VictimNotFoundException(id);
            }

            session.remove(victim);

            tx.commit();

            System.out.println(
                    "Victim Deleted Successfully.");
        }

        catch (VictimNotFoundException e) {

            if (tx != null)
                tx.rollback();

            System.out.println(e.getMessage());
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

	@Override
	public VictimExp createVictim(VictimExp victim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VictimExp getVictimById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VictimExp> getAllVictims() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VictimExp updateVictim(Long id, VictimExp updatedVictim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVictim(Long id) {
		// TODO Auto-generated method stub
		
	}
}