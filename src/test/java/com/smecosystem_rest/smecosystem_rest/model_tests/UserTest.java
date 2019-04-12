package com.smecosystem_rest.smecosystem_rest.model_tests;

import com.smecosystem_rest.smecosystem_rest.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void userMethods() {
        User tester = new User(); // MyClass is tested

        tester.setFirstName("peter");
        tester.setLastName("de wolf");
        tester.setEmailAddress("peter.dewolf13@gmail.com");

        // assert statements
        assertEquals("peter", tester.getFirstName(), "peter");
        assertEquals("de wolf", tester.getLastName(), "de wolf");
        assertEquals("peter.dewolf13@gmail.com", tester.getEmailAddress(), "peter.dewolf13@gmail.com");
    }

}
