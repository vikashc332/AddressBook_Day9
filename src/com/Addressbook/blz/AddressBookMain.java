package com.Addressbook.blz;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("welcome to Address Book ");
        Contacts contact = new Contacts();
        AddressBook addressBook = new AddressBook();
        addressBook.addContacts();
        addressBook.deleteContact();
    }
    }



