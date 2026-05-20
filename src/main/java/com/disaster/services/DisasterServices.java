package com.disaster.services;

import com.disaster.entity.Disaster;
import org.hibernate.SessionFactory;

import java.util.List;

/** Service contract for Disaster CRUD operations. */
public interface DisasterServices {

    void addDisaster(SessionFactory factory);

    Disaster getDisasterById(SessionFactory factory, int id);

    List<Disaster> getAllDisasters(SessionFactory factory);

    void updateDisaster(SessionFactory factory, int id);

    void deleteDisaster(SessionFactory factory, int id);
}
