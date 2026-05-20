package com.disaster.services;
//================= USER SERVICES INTERFACE =================
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.disaster.entity.User;

import com.disaster.util.DuplicateUserException;

public interface UserServiceExp {

 void addUser(SessionFactory factory, Scanner sc)
         throws DuplicateUserException;

 User getUserById(SessionFactory factory, int id)
         throws DuplicateUserException;

 List<User> getAllUsers(SessionFactory factory);

 void updateUser(SessionFactory factory, int id)
         throws DuplicateUserException;

 void deleteUser(SessionFactory factory, int id)
         throws DuplicateUserException;
}