package com.Addressbook.blz;

import lombok.*;

@Setter
@Getter
@Data
public class Contacts {
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zip;
    private String phoneNo;
    private String eMail;

}

