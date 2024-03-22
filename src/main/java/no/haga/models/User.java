package no.haga.models;

import lombok.Data;

@Data
public class User {
    private String fullName;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String email;
    private String password;
    private String mobileNumber;
    private String creditCardNumber;
}
