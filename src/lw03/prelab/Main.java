package lw03.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {

        // Problem 1
        Scanner scp1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while (scp1.hasNext()) {
            String line = scp1.next();
            if (line.equals("ADD")) {
                String song = scp1.nextLine().trim();
                playlist.add(song);
            } else if (line.equals("REMOVE")) {
                String song = scp1.nextLine().trim();
                playlist.remove(playlist.indexOf(song));
            } else if (line.equals("INSERT")) {
                int index = scp1.nextInt();
                String song = scp1.nextLine().trim();
                playlist.add(index, song);
            }
    }   
    
    System.out.println("==== Problem 1 ====\nTotal songs: " + playlist.size());
    for(int i = 0; i < playlist.size(); i++){
        System.out.println(i + 1 + ": " + playlist.get(i));

    }
    scp1.close();
    System.out.println();

    //Problem 2
    Scanner scp2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
    Set<String> participants = new LinkedHashSet<>();

    int duplicate = 0;
    while (scp2.hasNext()) {
        String name = scp2.nextLine();
        if (participants.contains(name)) {
            duplicate++;
        } else {
            participants.add(name);
        }
    }
    
    System.out.println("==== Problem 2 ====\nUnique participants: " + participants.size());
    int count = 1;
    for (String participant : participants) {
        System.out.println(count++ + ". " + participant);
    }

    System.out.println("Duplicate registrations: " + duplicate);
    scp2.close();
    System.out.println();

    //Problem 3
    Scanner scp3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
    Map<String, Integer> inventory = new LinkedHashMap<>();

    int failed = 0;
    while (scp3.hasNext()) {
        String type = scp3.next();
        String product = scp3.next();
        int quantity = scp3.nextInt();

        if (type.equals("ADD")) {
            if (inventory.containsKey(product)) {
                inventory.put(product, inventory.get(product) + quantity);
            } else {
                inventory.put(product, quantity);
            }
        } else if (type.equals("SELL")) {
            if (inventory.containsKey(product) && inventory.get(product) >= quantity) {
                inventory.put(product, inventory.get(product) - quantity);
            } else {
                failed++;
            }
        }
    }
    System.out.println("==== Problem 3 ====");
    for(String key : inventory.keySet()){
        System.out.println(key + ": " + inventory.get(key));
        }
    System.out.println("Failed sales: " + failed);
    }
}