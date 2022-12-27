package com.Addressbook.blz;

import java.io.IOException;
import java.util.*;

 public class AddressBook {
     static List<Contacts> contactDetails = new ArrayList<>();
     static Scanner input = new Scanner(System.in);
     static HashMap<String, AddressBook> dictAddressBook = new HashMap<>();
     static Dictionary dictCity = new Hashtable<String, ArrayList<Contacts>>();
     static Dictionary dictState = new Hashtable<String, ArrayList<Contacts>>();


     public Contacts readContactDetail() {
         Contacts personContact = new Contacts();
         System.out.println("Enter Contact Details");
         System.out.println("----------------------");
         Scanner in = new Scanner(System.in);
         System.out.print("Enter First Name: ");
         personContact.setFirstName(in.next());
         System.out.print("Enter Last Name: ");
         personContact.setLastName(in.next());
         System.out.print("Enter City: ");
         personContact.setCity(in.next());
         System.out.print("Enter State: ");
         personContact.setState(in.next());
         System.out.print("Enter Zip: ");
         personContact.setZipCode(in.next());
         System.out.print("Enter Phone number: ");
         personContact.setPhNumber(in.next());
         System.out.print("Enter Email Id: ");
         personContact.setEMail(in.next());
         return personContact;
     }

     public void addContactDetail() throws IOException {
         boolean flag = false;
         Contacts personDetails = readContactDetail();
         for (Contacts objPerson : contactDetails) {
             if (objPerson.getFirstName().equals(personDetails.getFirstName())) {
                 flag = true;
             }
         }
         if (!flag) {
             contactDetails.add(personDetails);
             storePersonByCity((String) personDetails.getCity(), personDetails);//call store person details by city name
             storePersonByState((String) personDetails.getState(), personDetails);//call store person details by state name
         } else {
             System.out.println("First Name already exist..");
         }
     }

     //store persons detail in dict by city name
     @SuppressWarnings("unchecked")
     public void storePersonByCity(String cityName, Contacts personObject) {
         while (dictCity.keys().hasMoreElements()) {
             if (dictCity.keys().nextElement().equals(cityName)) {
                 ArrayList<Contacts> personDetailsArray = (ArrayList<Contacts>) dictCity.get(cityName);
                 personDetailsArray.add(personObject);
                 dictCity.put(cityName, personDetailsArray);
                 return;
             } else break;
         }
         ArrayList<Contacts> personDetailsArray = new ArrayList<>();
         personDetailsArray.add(personObject);
         dictCity.put(cityName, personDetailsArray);
     }

     //store persons detail in dict by state name
     @SuppressWarnings("unchecked")
     public void storePersonByState(String stateName, Contacts personObject) {
         while (dictState.keys().hasMoreElements()) {
             if (dictState.keys().nextElement().equals(stateName)) {
                 ArrayList<Contacts> personDetailsArray = (ArrayList<Contacts>) dictState.get(stateName);
                 personDetailsArray.add(personObject);
                 dictState.put(stateName, personDetailsArray);
                 return;
             } else break;
         }
         ArrayList<Contacts> personDetailsArray = new ArrayList<>();
         personDetailsArray.add(personObject);
         dictState.put(stateName, personDetailsArray);
     }

     public void editContactDetail(String firstName) {
         if (contactDetails.isEmpty()) {
             System.out.println("------NO RECORDS------");
             return;
         }

         boolean flag = false;
         Contacts newPersonContact = null;
         for (Contacts objPerson : contactDetails) {
             if (objPerson.getFirstName().equals(firstName)) {
                 newPersonContact = objPerson;
                 flag = true;
                 break;
             }
         }

         if (flag) {
             int choice;
             do {
                 System.out.println("---------------------------");
                 System.out.println("What you want to edit");
                 System.out.println("1. First Name\n2. Last Name\n3. Address\n4. City\n5. State\n6. Zip\n7. Phone Number\n8. Email Id\n0. Cancel\n");
                 System.out.print("Enter choice: ");
                 Scanner in = new Scanner(System.in);
                 choice = in.nextInt();
                 //use switch for edit specific details
                 switch (choice) {
                     case 1:
                         System.out.println("Old: " + newPersonContact.getFirstName());
                         System.out.print("New: ");
                         String newFirstName = in.next();
                         newPersonContact.setFirstName(newFirstName);
                         break;

                     case 2:
                         System.out.println("Old: " + newPersonContact.getLastName());
                         System.out.print("New: ");
                         String newLastName = in.next();
                         newPersonContact.setLastName(newLastName);
                         break;
                     case 3:
                         System.out.println("Old: " + newPersonContact.getCity());
                         System.out.print("New: ");
                         String newCity = in.next();
                         newPersonContact.setCity(newCity);
                         break;
                     case 4:
                         System.out.println("Old: " + newPersonContact.getState());
                         System.out.print("New: ");
                         String newState = in.next();
                         newPersonContact.setState(newState);
                         break;
                     case 5:
                         System.out.println("Old: " + newPersonContact.getZipCode());
                         System.out.print("New: ");
                         String newZip = in.next();
                         newPersonContact.setZipCode(newZip);
                         break;
                     case 6:
                         System.out.println("Old: " + newPersonContact.getPhNumber());
                         System.out.print("New: ");
                         String newPhone = in.next();
                         newPersonContact.setPhNumber(newPhone);
                         break;
                     case 7:
                         System.out.println("Old: " + newPersonContact.getEMail());
                         System.out.print("New: ");
                         String newEmail = in.next();
                         newPersonContact.setEMail(newEmail);
                         break;
                     case 0:
                         break;
                     default:
                         System.out.println("invalid option....");
                         break;
                 }
             } while (choice != 0);
         }
     }


}


