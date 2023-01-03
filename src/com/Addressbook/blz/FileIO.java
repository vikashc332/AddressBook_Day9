package com.Addressbook.blz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileIO {
    public static void write(List<Contacts> personDetailsList) {
        StringBuffer personContactBuffer = new StringBuffer();
        personDetailsList.forEach(person -> {
            String personDataString = person.toString().concat("\n");
            personContactBuffer.append(personDataString);
        });

        try {
            Files.write(Paths.get("person-name.txt"), personContactBuffer.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printData() {
        try {
            Files.lines(new File("person-name.txt").toPath()).forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public long countEntries() {
        long count = 0;
        try {
            count = Files.lines(new File("person-name.txt").toPath()).count();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return count;
    }
}

