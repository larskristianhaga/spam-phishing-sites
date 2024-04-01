package no.haga.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String fullName;
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
    private String email;
    private String password;
    private String mobileNumber;
    private String creditCardNumber;
}
