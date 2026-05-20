package com.disaster.services;

import com.disaster.entity.Location;
import org.hibernate.SessionFactory;

import java.util.List;

/** Service contract for Location CRUD operations. */
public interface LocationServices {

    void addLocation(SessionFactory factory);

    Location getLocationById(SessionFactory factory, int id);

    List<Location> getAllLocations(SessionFactory factory);

    void updateLocation(SessionFactory factory, int id);

    void deleteLocation(SessionFactory factory, int id);
}
