package com.disaster.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Config {

    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {

        if (factory == null) {

            try {

                factory = new Configuration()
                        .configure("config.xml")
                        .buildSessionFactory();

            } catch (Exception e) {

                System.out.println("SessionFactory creation failed: " + e.getMessage());

                throw new RuntimeException("Failed to create SessionFactory");
            }
        }

        return factory;
    }

	public static void closeFactory() {
		// TODO Auto-generated method stub
		
	}

	public static void shutdown() {
		// TODO Auto-generated method stub
		
	}
}