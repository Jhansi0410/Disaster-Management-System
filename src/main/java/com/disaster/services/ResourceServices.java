package com.disaster.services;

import com.disaster.entity.Resource;
import org.hibernate.SessionFactory;

import java.util.List;

/** Service contract for Resource CRUD operations. */
public interface ResourceServices {

    void addResource(SessionFactory factory);

    Resource getResourceById(SessionFactory factory, int id);

    List<Resource> getAllResources(SessionFactory factory);

    void updateResource(SessionFactory factory, int id);

    void deleteResource(SessionFactory factory, int id);
}
