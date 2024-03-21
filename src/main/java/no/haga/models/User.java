package no.haga.models;

import lombok.Data;

@Data
public class User {
    private String fullName;
    private String address;
    private String postcode;
    private String email;
    private String password;
    private String mobileNumber;
    private String creditCardNumber;
}
