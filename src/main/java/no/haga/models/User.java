package no.haga.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String fullName;
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
    private String email;
    private String password;
    private String phoneNumber;
    private String idNumber;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardCvc;
    private String creditCardType;
    private String creditCardHolder;
}
