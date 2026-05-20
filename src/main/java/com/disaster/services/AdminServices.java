package com.disaster.services;

import com.disaster.entity.Admin;
import org.hibernate.SessionFactory;

import java.util.List;

/** Service contract for Admin CRUD operations. */
public interface AdminServices {

    void addAdmin(SessionFactory factory);

    Admin getAdminById(SessionFactory factory, int id);

    List<Admin> getAllAdmins(SessionFactory factory);

    void updateAdmin(SessionFactory factory, int id);

    void deleteAdmin(SessionFactory factory, int id);
}
