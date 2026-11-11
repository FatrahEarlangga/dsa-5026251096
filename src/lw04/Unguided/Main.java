package lw04.Unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Membaca file parcels.txt
            Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

            int n = sc.nextInt();
            int[] shelf = new int[n];
            for (int i = 0; i < n; i++) {
                shelf[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();

            // Array cadangan untuk menyimpan kondisi setelah 2 pass
            int[] afterTwoPasses = new int[n];

            // 2. Bubble Sort Descending
            // Total pass yang dibutuhkan adalah n - 1
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    // Tukar jika elemen kiri lebih kecil dari elemen kanan (karena descending)
                    if (shelf[j] < shelf[j + 1]) {
                        int temp = shelf[j];
                        shelf[j] = shelf[j + 1];
                        shelf[j + 1] = temp;
                    }
                }

                // Saat baterai habis (tepat setelah pass ke-2, yaitu i = 1)
                if (i == 1) {
                    for (int k = 0; k < n; k++) {
                        afterTwoPasses[k] = shelf[k];
                    }
                }
            }

            // 3. Tampilkan shelf setelah 2 pass
            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < n; i++) {
                System.out.print(afterTwoPasses[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            // 4. Tampilkan shelf yang sudah fully sorted
            System.out.println("==== Fully Sorted ====");
            for (int i = 0; i < n; i++) {
                System.out.print(shelf[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            // 5. Binary Search untuk setiap target (Descending)
            System.out.println("==== Search Results ====");
            for (int t = 0; t < m; t++) {
                int target = targets[t];
                int low = 0;
                int high = n - 1;
                int steps = 0;
                int foundIndex = -1;

                while (low <= high) {
                    steps++;
                    int mid = (low + high) / 2;

                    if (shelf[mid] == target) {
                        foundIndex = mid;
                        break;
                    } else if (shelf[mid] < target) {
                        // Karena descending, angka lebih besar berada di sebelah kiri
                        high = mid - 1;
                    } else {
                        // Angka lebih kecil berada di sebelah kanan
                        low = mid + 1;
                    }
                }

                if (foundIndex != -1) {
                    System.out.println(target + ": FOUND at index " + foundIndex + " (steps: " + steps + ")");
                } else {
                    System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}