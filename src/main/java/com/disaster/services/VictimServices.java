package com.disaster.services;

import com.disaster.entity.Victim;
import com.disaster.util.VictimNotFoundException;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

/** Service contract for Victim CRUD operations. */
public interface VictimServices {

	void addVictim(SessionFactory factory, Scanner sc);

    Victim getVictimById(SessionFactory factory, int id) throws VictimNotFoundException;

    List<Victim> getAllVictims(SessionFactory factory);
    

    void updateVictim(SessionFactory factory, int id) throws VictimNotFoundException;

    void deleteVictim(SessionFactory factory, int id) throws VictimNotFoundException;

	void deleteVictim(SessionFactory factory, long l) throws VictimNotFoundException;

	/** Retrieves Victim by ID, throws VictimNotFoundException if missing. */
	Victim getVictimById(SessionFactory factory, long l) throws VictimNotFoundException;

	
}
