package com.disaster.services;

import com.disaster.entity.Operation;
import org.hibernate.SessionFactory;

import java.util.List;

/** Service contract for Operation CRUD operations. */
public interface OperationServices {

    void addOperation(SessionFactory factory);

    Operation getOperationById(SessionFactory factory, int id);

    List<Operation> getAllOperations(SessionFactory factory);

    void updateOperation(SessionFactory factory, int id);

    void deleteOperation(SessionFactory factory, int id);
}
