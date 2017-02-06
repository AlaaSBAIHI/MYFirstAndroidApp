package com.example.redi.MyFirstAndroidApp.models.entities;

import java.util.UUID;

/**
 * Created by ReDI on 2/5/2017.
 */


public class User {

    private UUID id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    private String description;


    public User(String email, String password, String firstName, String lastName, String address, String description) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }


/*

    private Set<Contact> contacts;
    private Set<Course> courses;
*/

}
