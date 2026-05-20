package com.disaster.main;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.disaster.config.Config;
import com.disaster.servicesImpl.AdminServicesImpl;
import com.disaster.servicesImpl.DisasterServicesImpl;
import com.disaster.servicesImpl.LocationServicesImpl;
import com.disaster.servicesImpl.OperationServicesImpl;
import com.disaster.servicesImpl.RequestServicesImpl;
import com.disaster.servicesImpl.ResourceServicesImpl;
import com.disaster.servicesImpl.RescueTeamServicesImpl;
import com.disaster.servicesImpl.UserServicesImpl;
import com.disaster.servicesImpl.VictimServicesImpl;
import com.disaster.util.DuplicateUserException;
import com.disaster.util.VictimNotFoundException;

public class DisasterMain {

    public static void main(String[] args) {

        SessionFactory factory =
                Config.getSessionFactory();

        Scanner sc = new Scanner(System.in);

        UserServicesImpl userService =
                new UserServicesImpl();

        AdminServicesImpl adminService =
                new AdminServicesImpl();

        DisasterServicesImpl disasterService =
                new DisasterServicesImpl();

        LocationServicesImpl locationService =
                new LocationServicesImpl();

        RescueTeamServicesImpl teamService =
                new RescueTeamServicesImpl();

        OperationServicesImpl operationService =
                new OperationServicesImpl();

        RequestServicesImpl requestService =
                new RequestServicesImpl();

        ResourceServicesImpl resourceService =
                new ResourceServicesImpl();

        VictimServicesImpl victimService =
                new VictimServicesImpl();

        int choice;

        do {

            System.out.println(
                    "\n===== DISASTER MANAGEMENT SYSTEM =====");

            System.out.println("1. User Management");
            System.out.println("2. Admin Management");
            System.out.println("3. Disaster Management");
            System.out.println("4. Location Management");
            System.out.println("5. Rescue Team Management");
            System.out.println("6. Operation Management");
            System.out.println("7. Request Management");
            System.out.println("8. Resource Management");
            System.out.println("9. Victim Management");
            System.out.println("0. Exit");

            System.out.print("Enter Choice: ");

            choice =
                    Integer.parseInt(sc.nextLine());

            switch (choice) {

            // ============================================
            // USER
            // ============================================

            case 1:

                System.out.println("\n1. Add User");
                System.out.println("2. Get User");
                System.out.println("3. Get All Users");
                System.out.println("4. Update User");
                System.out.println("5. Delete User");

                System.out.print("Enter Choice: ");

                int userChoice =
                        Integer.parseInt(sc.nextLine());

                switch (userChoice) {

                case 1:

                    try {

                        userService.addUser(factory, sc);

                    }

                    catch (DuplicateUserException e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 2:

                    try {

                        System.out.print(
                                "Enter User ID: ");

                        System.out.println(
                                userService.getUserById(
                                        factory,
                                        Integer.parseInt(
                                                sc.nextLine())));

                    }

                    catch (Exception e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 3:

                    userService.getAllUsers(factory)
                               .forEach(System.out::println);

                    break;

                case 4:

                    try {

                        System.out.print(
                                "Enter User ID: ");

                        userService.updateUser(
                                factory,
                                Integer.parseInt(
                                        sc.nextLine()),
                                sc);

                    }

                    catch (Exception e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 5:

                    try {

                        System.out.print(
                                "Enter User ID: ");

                        userService.deleteUser(
                                factory,
                                Integer.parseInt(
                                        sc.nextLine()));

                    }

                    catch (Exception e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // ADMIN
            // ============================================

            case 2:

                System.out.println("\n1. Add Admin");
                System.out.println("2. Get Admin");
                System.out.println("3. Get All Admins");
                System.out.println("4. Update Admin");
                System.out.println("5. Delete Admin");

                System.out.print("Enter Choice: ");

                int adminChoice =
                        Integer.parseInt(sc.nextLine());

                switch (adminChoice) {

                case 1:

                    adminService.addAdmin(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Admin ID: ");

                    System.out.println(
                            adminService.getAdminById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    adminService.getAllAdmins(factory)
                                .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Admin ID: ");

                    adminService.updateAdmin(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Admin ID: ");

                    adminService.deleteAdmin(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // DISASTER
            // ============================================

            case 3:

                System.out.println("\n1. Add Disaster");
                System.out.println("2. Get Disaster");
                System.out.println("3. Get All Disasters");
                System.out.println("4. Update Disaster");
                System.out.println("5. Delete Disaster");

                System.out.print("Enter Choice: ");

                int disasterChoice =
                        Integer.parseInt(sc.nextLine());

                switch (disasterChoice) {

                case 1:

                    disasterService.addDisaster(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Disaster ID: ");

                    System.out.println(
                            disasterService.getDisasterById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    disasterService.getAllDisasters(factory)
                                   .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Disaster ID: ");

                    disasterService.updateDisaster(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Disaster ID: ");

                    disasterService.deleteDisaster(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // LOCATION
            // ============================================

            case 4:

                System.out.println("\n1. Add Location");
                System.out.println("2. Get Location");
                System.out.println("3. Get All Locations");
                System.out.println("4. Update Location");
                System.out.println("5. Delete Location");

                System.out.print("Enter Choice: ");

                int locationChoice =
                        Integer.parseInt(sc.nextLine());

                switch (locationChoice) {

                case 1:

                    locationService.addLocation(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Location ID: ");

                    System.out.println(
                            locationService.getLocationById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    locationService.getAllLocations(factory)
                                   .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Location ID: ");

                    locationService.updateLocation(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Location ID: ");

                    locationService.deleteLocation(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // RESCUE TEAM
            // ============================================

            case 5:

                System.out.println("\n1. Add Team");
                System.out.println("2. Get Team");
                System.out.println("3. Get All Teams");
                System.out.println("4. Update Team");
                System.out.println("5. Delete Team");

                System.out.print("Enter Choice: ");

                int teamChoice =
                        Integer.parseInt(sc.nextLine());

                switch (teamChoice) {

                case 1:

                    teamService.addTeam(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Team ID: ");

                    System.out.println(
                            teamService.getTeamById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    teamService.getAllTeams(factory)
                               .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Team ID: ");

                    teamService.updateTeam(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Team ID: ");

                    teamService.deleteTeam(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // OPERATION
            // ============================================

            case 6:

                System.out.println("\n1. Add Operation");
                System.out.println("2. Get Operation");
                System.out.println("3. Get All Operations");
                System.out.println("4. Update Operation");
                System.out.println("5. Delete Operation");

                System.out.print("Enter Choice: ");

                int operationChoice =
                        Integer.parseInt(sc.nextLine());

                switch (operationChoice) {

                case 1:

                    operationService.addOperation(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Operation ID: ");

                    System.out.println(
                            operationService.getOperationById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    operationService.getAllOperations(factory)
                                    .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Operation ID: ");

                    operationService.updateOperation(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Operation ID: ");

                    operationService.deleteOperation(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // REQUEST
            // ============================================

            case 7:

                System.out.println("\n1. Add Request");
                System.out.println("2. Get Request");
                System.out.println("3. Get All Requests");
                System.out.println("4. Update Request");
                System.out.println("5. Delete Request");

                System.out.print("Enter Choice: ");

                int requestChoice =
                        Integer.parseInt(sc.nextLine());

                switch (requestChoice) {

                case 1:

                    requestService.addRequest(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Request ID: ");

                    System.out.println(
                            requestService.getRequestById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    requestService.getAllRequests(factory)
                                  .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Request ID: ");

                    requestService.updateRequest(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Request ID: ");

                    requestService.deleteRequest(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // RESOURCE
            // ============================================

            case 8:

                System.out.println("\n1. Add Resource");
                System.out.println("2. Get Resource");
                System.out.println("3. Get All Resources");
                System.out.println("4. Update Resource");
                System.out.println("5. Delete Resource");

                System.out.print("Enter Choice: ");

                int resourceChoice =
                        Integer.parseInt(sc.nextLine());

                switch (resourceChoice) {

                case 1:

                    resourceService.addResource(factory);

                    break;

                case 2:

                    System.out.print(
                            "Enter Resource ID: ");

                    System.out.println(
                            resourceService.getResourceById(
                                    factory,
                                    Integer.parseInt(
                                            sc.nextLine())));

                    break;

                case 3:

                    resourceService.getAllResources(factory)
                                   .forEach(System.out::println);

                    break;

                case 4:

                    System.out.print(
                            "Enter Resource ID: ");

                    resourceService.updateResource(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                case 5:

                    System.out.print(
                            "Enter Resource ID: ");

                    resourceService.deleteResource(
                            factory,
                            Integer.parseInt(
                                    sc.nextLine()));

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // VICTIM
            // ============================================

            case 9:

                System.out.println("\n1. Add Victim");
                System.out.println("2. Get Victim");
                System.out.println("3. Get All Victims");
                System.out.println("4. Update Victim");
                System.out.println("5. Delete Victim");

                System.out.print("Enter Choice: ");

                int victimChoice =
                        Integer.parseInt(sc.nextLine());

                switch (victimChoice) {

                case 1:

                    victimService.addVictim(factory, sc);

                    break;

                case 2:

                    try {

                        System.out.print(
                                "Enter Victim ID: ");

                        System.out.println(
                                victimService.getVictimById(
                                        factory,
                                        Long.parseLong(
                                                sc.nextLine())));

                    }

                    catch (VictimNotFoundException e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 3:

                    victimService.getAllVictims(factory)
                                 .forEach(System.out::println);

                    break;

                case 4:

                    try {

                        System.out.print(
                                "Enter Victim ID: ");

                        victimService.updateVictim(
                                factory,
                                Long.parseLong(
                                        sc.nextLine()),
                                sc);

                    }

                    catch (VictimNotFoundException e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                case 5:

                    try {

                        System.out.print(
                                "Enter Victim ID: ");

                        victimService.deleteVictim(
                                factory,
                                Long.parseLong(
                                        sc.nextLine()));

                    }

                    catch (VictimNotFoundException e) {

                        System.out.println(
                                e.getMessage());
                    }

                    break;

                default:

                    System.out.println(
                            "Invalid Choice!");
                }

                break;

            // ============================================
            // EXIT
            // ============================================

            case 0:

                System.out.println(
                        "Exiting System...");
                break;

            default:

                System.out.println(
                        "Invalid Choice!");
            }

        } while (choice != 0);

        sc.close();

        Config.closeFactory();
    }
}