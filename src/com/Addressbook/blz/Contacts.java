package com.Addressbook.blz;

public class Contacts <T extends Comparable<T>> {
    private T firstName;
    private T lastName;
    private T city;
    private T state;
    private T EMail;
    private T zipCode;
    private T phNumber;

    public T getFirstName() {
        return firstName;
    }

    public void setFirstName(T firstName) {
        this.firstName = firstName;
    }

    public T getLastName() {
        return lastName;
    }

    public void setLastName(T lastName) {
        this.lastName = lastName;
    }

    public T getCity() {
        return city;
    }

    public void setCity(T city) {
        this.city = city;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public T getEMail() {
        return EMail;
    }

    public void setEMail(T EMail) {
        this.EMail = EMail;
    }

    public T getZipCode() {
        return zipCode;
    }

    public void setZipCode(T zipCode) {
        this.zipCode = zipCode;
    }

    public T getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(T phNumber) {
        this.phNumber = phNumber;
    }

    public void displayPersonContactDetails() {
        System.out.println("First Name: " + firstName + "\nLast Name: " + lastName +"\nCity: " + city + "\nState: " + state + "\nZip: " + zipCode + "\nPhone Number: " + phNumber + "\nEmail Id: " + EMail);
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", email='" + EMail + '\'' +
                ", zip='" + zipCode + '\'' +
                ", phone='" + phNumber + '\'' +
                '}';
    }
}