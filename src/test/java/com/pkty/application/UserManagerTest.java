package com.pkty.application;


import com.pkty.domain.Role;
import com.pkty.domain.User;
import com.pkty.shared.ManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.util.DatabaseManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Users manager test class.
 */
class UserManagerTest {

    EntityManager<User> userManager;
    EntityManager<Role> roleManager;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userManager = ManagerFactory.getManager(User.class);
        roleManager = ManagerFactory.getManager(Role.class);

        DatabaseManager dbm = DatabaseManager.getInstance();
        dbm.runSQL("cleandb.sql");
    }

    /**
     * Test get user by id.
     */
    @Test
    void testGetUserById() {
        User user = userManager.get(1);
        String username = user.getUsername();
        assertEquals("tdombrowski", username );
    }

    /**
     * Test the get all users.
     */
    @Test
    void testGetAllUsers() {
        List<User> userList = userManager.getList();
        assertEquals(4, userList.size());
    }

    /**
     * Test the get all users by property.
     */
    @Test
    void testGetUsersEquals() {

        String username = "tdombrowski";
        List<User> userList = userManager.getListEquals("username", username);
        assertEquals(1, userList.size());
    }

    /**
     * Test the get all users by property.
     */
    /*
    @Test
    void testGetUserByRoleId() {

        String username = "tdombrowski";
        String roleId = "1";
        List<Users> userList = userManager.getListEquals("roles.getId()", roleId);
        assertEquals(1, userList.size());
    }*/


    /**
     * Test the get all users by Username.
     */
    @Test
    void testGetUsersLike() {

        String firstname = "es";
        List<User> userList = userManager.getListContains("firstName", firstname);
        assertEquals(2, userList.size());
    }


    /**
     * Test the user creation.
     */
    @Test
    void testCreateUser() {

        String firstName = "Jane";
        String lastName = "Smith";
        String userName = "jasmith";
        String password = "supersecret7";
        User newUser = new User(firstName, lastName, userName, password);
        User insertedUser = userManager.create(newUser);

        assertNotNull(insertedUser);
        assertEquals(userName, insertedUser.getUsername());
        assertEquals(firstName, insertedUser.getFirstName());
        assertEquals(password, insertedUser.getPassword());
        assertNotNull(insertedUser.getCreated());
    }

    /**
     * Test the user update.
     */
    @Test
    void testUpdateUser() {

        String newUserName = "yesper";
        int userId = 4;

        User userToUpdate = userManager.get(userId);
        userToUpdate.setUsername(newUserName);

        userManager.update(userToUpdate);
        User userUpdated = userManager.get(userId);

        assertEquals(userToUpdate, userUpdated);
    }

    /**
     * Test the user deletion.
     */
    @Test
    void testDeleteUser() {

        int userIdToDelete = 1;
        userManager.delete(userManager.get(userIdToDelete));
        assertNull(userManager.get(userIdToDelete));
    }

    /**
     * Test create user with one role.
     */
    @Test
    void testCreateUserWithRoles() {
        String firstName = "Jane";
        String lastName = "Smith";
        String userName = "jasmith";
        String password = "supersecret7";

        int adminRoleId = 1;
        int userRoleId = 2;
        int rolesAssigned = 0;

        User newUser = new User(firstName, lastName, userName, password);
        User insertedUser = userManager.create(newUser);

        Set<Role> roles = new HashSet<>();

        User userToUpdate = userManager.get(insertedUser.getId());
        Role adminRole = roleManager.get(adminRoleId);
        Role userRole = roleManager.get(userRoleId);

        insertedUser.addRole(userRole);
        insertedUser.addRole(adminRole);

        userManager.update(insertedUser);

        User userUpdated = userManager.get(insertedUser.getId());

        rolesAssigned = userUpdated.getRoles().size();
        Role firstRole = userUpdated.getRoles().stream().findFirst().get();

        assertNotNull(insertedUser);
        assertEquals(userName, insertedUser.getUsername());
        assertEquals(password, insertedUser.getPassword());
        assertNotNull(insertedUser.getCreated());
        assertEquals(2,rolesAssigned);
        assertEquals(adminRole, firstRole);
    }
}