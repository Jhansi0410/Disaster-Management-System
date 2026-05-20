package com.disaster.services;

import com.disaster.entity.User;
import com.disaster.util.DuplicateUserException;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public interface UserServices {

    /** Prompts for user details via Scanner and persists a new User. */
    void addUser(SessionFactory factory);

    
    User getUserById(SessionFactory factory, int id) throws DuplicateUserException;

    /** Returns every User in the database. */
    List<User> getAllUsers(SessionFactory factory);

   
    void updateUser(SessionFactory factory, int id) throws DuplicateUserException;

   
    void deleteUser(SessionFactory factory, int id) throws DuplicateUserException;


	void addUser(SessionFactory factory, Scanner sc);


	void updateUser(SessionFactory factory, int id, Scanner sc) throws DuplicateUserException;
}
