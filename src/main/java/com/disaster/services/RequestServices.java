package com.disaster.services;



import org.hibernate.SessionFactory;

import com.disaster.entity.Request;

import java.util.List;

/** Service contract for Request CRUD operations. */
public interface RequestServices {

    /**
     * Reads request details from console and persists a new Request.
     * @throws InvalidRequestException if required fields are empty/invalid.
     */
    void addRequest(SessionFactory factory);

    Request getRequestById(SessionFactory factory, int id);

    List<Request> getAllRequests(SessionFactory factory);

    void updateRequest(SessionFactory factory, int id);

    void deleteRequest(SessionFactory factory, int id);


}
