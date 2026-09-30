package lw02.Unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> stock = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        // Read file
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );
          while (scanner.hasNext()) {
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }
        scanner.close();

        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        queue.addAll(orders);

        LinkedList<String[]> successful = new LinkedList<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String customerName = order[0];
            String foodName = order[1];
            String drinkName = order[2];
            String table = order[3];

            String[] food = null;
            if (!foodName.equals ("-")){
                for (String[] f : foods) {
                    if (f[0].equals(foodName)) {
                        food = f;
                        break;
                    }
                }
            }

            String[] drink = null;
            if (!drinkName.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drinkName)) {
                        drink = d;
                        break;
                    }
                }
            }

            boolean Stockready = true;
            if (food != null && Integer.parseInt(food[1]) <= 0) {
                Stockready = false;
            }
            if (drink != null && Integer.parseInt(drink[1]) <= 0) {
                Stockready = false;
            }

            if (Stockready) {
                if (food != null) {
                    food[1] = String.valueOf(Integer.parseInt(food[1]) - 1);
                }
                if (drink != null) {
                    drink[1] = String.valueOf(Integer.parseInt(drink[1]) - 1);
                }
                successful.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");    
        for (String[] order : successful) {
            System.out.println(order[0] + " " + 
            order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println();

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println();

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println();   

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] order = failed.pop();
            System.out.println(
                order[0] + " " + 
                order[1] + " " + 
                order[2] + " " + 
                order[3]);
        }
    }
}
