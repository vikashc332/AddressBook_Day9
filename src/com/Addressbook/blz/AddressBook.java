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


         } else {
             System.out.println("First name doesn't exist");
         }
     }

     public void deleteContactDetail(String firstName) {
         boolean flag = false;
         if (contactDetails.isEmpty()) {
             System.out.println("------NO RECORDS------");
             return;
         }
         for (Contacts objPerson : contactDetails) {
             if (objPerson.getFirstName().equals(firstName)) {
                 contactDetails.remove(objPerson);
                 flag = true;
                 break;
             }
         }
         if (flag) {
             System.out.println("Record Deleted..");
         } else {
             System.out.println("First name doesn't exist");
         }
     }

     //display contact details
     public void displayContactDetails() {
         if (contactDetails.isEmpty()) {//check list are empty or not
             System.out.println("------NO RECORDS------");
             return;
         }
         System.out.println("do want to sort contacts on specific details");
         System.out.println("1. Name \t 2. City \t 3. State \t 4. Zip");
         int ch = input.nextInt();
         switch (ch) {
             case 2:
                 contactDetails.stream().sorted(Comparator.comparing(Contacts::getCity)).forEach(System.out::println);
                 break;
             case 3:
                 contactDetails.stream().sorted(Comparator.comparing(Contacts::getState)).forEach(System.out::println);
                 break;
             case 4:
                 contactDetails.stream().sorted(Comparator.comparing(Contacts::getZipCode)).forEach(System.out::println);
                 break;
             default:
                 contactDetails.stream().sorted(Comparator.comparing(Contacts::getFirstName)).forEach(System.out::println);
                 break;
         }
     }

     public static String inputString(String message) {
         System.out.println(message);
         return input.next().toLowerCase();
     }

     public static int inputInteger(String message) {
         System.out.println(message);
         return input.nextInt();
     }

   /* public static char inputChar(String message) {
        System.out.println(message);
        return input.next().toUpperCase().charAt(0);
    }*/

     public static void displayBooks() {
         for (String books : dictAddressBook.keySet()) {
             if (dictAddressBook.isEmpty())
                 System.out.println("Please Add Some Address Book");
             else
                 System.out.println("The Available Books are : ");
             System.out.println(books);
         }
     }

     public static void displayNames(AddressBook addressBook) {
         System.out.print("First Names: ");
         for (Contacts objPerson : contactDetails
         ) {
             System.out.print(objPerson.getFirstName() + ", ");
         }
         System.out.println();
     }

     public static void userOperation(String bookName, AddressBook addressBook) throws IOException {
         int choice;
         do {
             System.out.println("--------------------------");
             System.out.println("Accessing: " + bookName);
             System.out.println("1. Add Person Details\n2. Display Details\n3. Edit Detail\n4. Delete Detail\n0. Exit");
             choice = inputInteger("Enter Choice: ");
             switch (choice) {
                 case 1:
                     assert addressBook != null;
                     addressBook.addContactDetail();
                     break;
                 case 2:
                     addressBook.displayContactDetails();
                     break;
                 case 3:
                     displayNames(addressBook);
                     String fNameEdit = inputString("Enter First Name: ");
                     addressBook.editContactDetail(fNameEdit);
                     break;
                 case 4:
                     String fNameDelete = inputString("Enter First Name to delete: ");
                     addressBook.deleteContactDetail(fNameDelete);
                     break;
                 case 0:
                     break;
                 default:
                     System.out.println("Invalid input...");
             }
         } while (choice != 0);
     }

     public static void addressBookMenu() throws IOException {
         int ch;
         System.out.println("Welcome to Address Book");
         do {
             String bookName = "";
             AddressBook addressBook = new AddressBook();
             ch = inputInteger("1. Create New book\n2. Edit Existing book\n" +
                     "3. Edit AddressBook \n4. Display Books \n5. Search Options \n(0 to Close)");
             switch (ch) {
                 case 1:
                     bookName = inputString("Enter New Address Book Name: ");
                     dictAddressBook.put(bookName, addressBook);
                     userOperation(bookName, addressBook);
                     break;

                 case 2:
                     if (!dictAddressBook.isEmpty()) {
                         displayBooks();
                         bookName = inputString("Enter Address Book Name to Access: ");
                         addressBook = dictAddressBook.get(bookName);
                         userOperation(bookName, addressBook);
                     } else {
                         System.out.println("No Address Books are present");
                     }
                     break;
                 case 3:
                     if (!dictAddressBook.isEmpty()) {
                         String personName = inputString("Enter Person Name to edit: ");
                         editGlobalContact(personName);
                     } else {
                         System.out.println("No Address Books are present");
                     }
                     break;
                 case 4:
                     displayBooks();
                 case 5:
                     SearchOption();
             }
         } while (ch != 0);
     }

     private static void SearchOption() {
         int choice;
         do {
             System.out.println("Please Enter the Option ");
             System.out.println("1. Search By City \n2. Search By State \n3. View By City \n4. View By State \n5. Count by City \n6. Count by State \n(0 to Close)");
             choice = input.nextInt();

             switch (choice) {
                 case 1:
                     searchPersonCity();
                     break;
                 case 2:
                     searchPersonState();
                     break;
                 case 3:
                     System.out.println("Enter City name");
                     String city = input.next();
                     viewPersonCity(city);
                     break;
                 case 4:
                     System.out.println("Enter State name");
                     String state = input.next();
                     viewPersonState(state);
                     break;
                 case 5:
                     System.out.println("Enter city name");
                     String cityToCount = input.next();
                     countPersonByCity(cityToCount);
                     break;
                 case 6:
                     System.out.println("Enter state name");
                     String stateToCount = input.next();
                     countPersonByState(stateToCount);
                     break;


             }
         } while (choice != 0);
     }

     public static void editGlobalContact(String personName) {
         boolean flag = false;
         for (AddressBook addressBook : dictAddressBook.values()) {
             for (Contacts listContactDetail : contactDetails
             ) {
                 if (listContactDetail.getFirstName().equals(personName)) {
                     flag = true;
                     break;
                 }
             }
             if (flag) {
                 addressBook.editContactDetail(personName);
                 break;
             }
         }
     }

     public static void searchPersonCity() {
         System.out.println("Enter City name");
         String city = input.next();
         dictAddressBook.values().forEach(book -> book.contactDetails.stream().filter(person -> person.getCity().equals(city.toLowerCase())).forEach(System.out::println));
     }
     public static void searchPersonState() {
         System.out.println("Enter State name");
         String state = input.next();
         dictAddressBook.values().forEach(book -> book.contactDetails.stream().filter(person -> person.getState().equals(state.toLowerCase())).forEach(System.out::println));
     }
     public static void viewPersonCity(String city) {
         ArrayList<Contacts> personDetails = (ArrayList<Contacts>) dictCity.get(city);
         personDetails.stream().forEach(System.out::println);
     }
     public static void viewPersonState(String state) {

         ArrayList<Contacts> personDetails = (ArrayList<Contacts>) dictState.get(state);
         personDetails.stream().forEach(System.out::println);
     }

     public static void countPersonByCity(String city) {
         dictAddressBook.values().forEach(book -> book.contactDetails.stream().filter(person -> person.getState().equals(city.toLowerCase())).count());
         long count = contactDetails.stream().sorted(Comparator.comparing(Contacts::getState)).count();
         System.out.println("The Count of State is : " + count + " " + city);
     }

     public static void countPersonByState(String state) {
         dictAddressBook.values().forEach(book -> book.contactDetails.stream().filter(person -> person.getState().equals(state.toLowerCase())).count());
         long count = contactDetails.stream().sorted(Comparator.comparing(Contacts::getState)).count();
         System.out.println("The Count of State is : " + count + " " + state);
     }
 }












