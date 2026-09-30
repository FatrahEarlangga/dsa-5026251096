package lw02.prelab;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String[]> transactions = new LinkedList<>();
        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("/lw02/prelab/transactions.txt"));
        while (scanner.hasNext()) {
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();
            transactions.add(new String[]{name, type, amount});
        }
        scanner.close();

        LinkedList<String[]> customers = new LinkedList<>();
        for (String[] trx : transactions) {
            String name = trx[0];
            boolean exists = false;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                customers.add(new String[]{name, "0"});
            }
        }

        Queue<String[]> queue = new LinkedList<>(transactions);

        Stack<String[]> failed = new Stack<>();

        while (!queue.isEmpty()) {
            String[] trx = queue.poll();
            String name = trx[0];
            String type = trx[1];
            int amount = Integer.parseInt(trx[2]);

            String[] customer = null;
            for (String[] c : customers) {
                if (c[0].equals(name)) {
                    customer = c;
                    break;
                }
            }

            int balance = Integer.parseInt(customer[1]);

            if (type.equals("DEPOSIT")) {
                balance += amount;
                customer[1] = String.valueOf(balance);
            } else if (type.equals("WITHDRAW")) {
                if (amount > balance) {
                    failed.push(trx);
                } else {
                    balance -= amount;
                    customer[1] = String.valueOf(balance);
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] c : customers) {
            System.out.println(c[0] + " : " + c[1]);
        }

        System.out.println("\n=== Failed Transactions ===");
        while (!failed.isEmpty()) {
            String[] trx = failed.pop();
            System.out.println(trx[0] + " " + trx[1] + " " + trx[2]);
        }
    }
}