package lw03.Unguided;

import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        Set<String> registrations = new LinkedHashSet<>();
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (sc1.hasNextLine()) {
        String id = sc1.nextLine();
        if (!id.isEmpty()) {
            if (!registrations.add(id)) {
        }
    }
}
        sc1.close();

        Set<String> checkedIn = new LinkedHashSet<>();
        int success = 0;
        int failed = 0;

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        
            System.out.println("===== Event Check-In Results =====");
            
        while (sc2.hasNextLine()) {
            String id = sc2.nextLine();
            
            if (!id.isEmpty()) {
                if (id.isEmpty()) {
                    continue;
                }
                if (!registrations.contains(id)) {
                    System.out.println(id + ": Rejected (not registered)");
                    failed++;
                } else if (!checkedIn.add(id)) {
                    System.out.println(id + ": Rejected (already checked in)");
                    failed++;
                } else {
                    checkedIn.add(id);
                    success++;
                    System.out.println(id + ": Checked in");
                }
            }
        }
        sc2.close();

        int absent = registrations.size() - success;

System.out.println();

    System.out.println("===== Final Event Summary =====");

        System.out.println("Registered students:  " + registrations.size());
        System.out.println("Successful check-ins: " + success);
        System.out.println("Absent students: " + absent);
        System.out.println("Failed check-ins: " + failed);
    }
}