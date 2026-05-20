// =============================
// VictimServices.java
// =============================

package com.disaster.services;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.disaster.entity.Victim;
import com.disaster.entity.VictimExp;

public interface VictimServiceExp {

    void addVictim(SessionFactory factory, Scanner sc);

    Victim getVictimById(SessionFactory factory, long id);

    List<Victim> getAllVictims(SessionFactory factory);

    void updateVictim(SessionFactory factory, long id, Scanner sc);

    void deleteVictim(SessionFactory factory, long id);

	VictimExp getVictimById(Long id);

	List<VictimExp> getAllVictims();

	VictimExp updateVictim(Long id, VictimExp updatedVictim);

	void deleteVictim(Long id);

	VictimExp createVictim(VictimExp victim);
}