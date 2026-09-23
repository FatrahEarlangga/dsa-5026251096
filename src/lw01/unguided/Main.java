package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/lw01/unguided/washes.txt"));

        int n = scanner.nextInt();
        WashService[] services = new WashService[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                services[i] = new Motorcyclewash(id, days);
            } else if (type.equals("CAR")) {
                services[i] = new CarWash(id, days, units);
            }
        }

        scanner.close();

        for (WashService service : services) {
            System.out.println(service.summary());
        }
    }
}

