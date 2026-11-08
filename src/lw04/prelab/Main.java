package lw04.prelab;

import java.util.Scanner;

public class Main {

    // Insertion sort descending, berhenti setelah sejumlah "passes"
    static void insertionSort(int[] a, int passes) {
        for (int i = 1; i <= passes; i++) {
            int key = a[i];
            int j = i - 1;
            // geser elemen di kiri yang lebih kecil dari key ke kanan
            while (j >= 0 && a[j] < key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // Linear search: return index kalau ketemu, -1 kalau tidak
    static int linearSearch(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        int n = sc.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }
        sc.close();

        insertionSort(branches, 3);

        // tampilkan array setelah 3 pass
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(branches[i]);
        }
        System.out.println();

        // cari tiap target sesuai urutan di file
        for (int i = 0; i < m; i++) {
            int idx = linearSearch(branches, targets[i]);
            if (idx == -1) {
                System.out.println(targets[i] + ": NOT FOUND");
            } else {
                System.out.println(targets[i] + ": FOUND at index " + idx);
            }
        }
    }
}