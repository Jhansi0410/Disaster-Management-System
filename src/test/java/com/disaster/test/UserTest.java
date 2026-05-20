package com.disaster.test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    @Test
    void user_Test() {

        String userName = "Ravi";
        String expectedName = "Ravi";

        Assertions.assertEquals(userName, expectedName);

        System.out.println("User Test Successful");
    }
}