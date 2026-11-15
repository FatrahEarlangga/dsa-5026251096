package lw05.prelab;

import java.util.*;

public class Main {
    // Variabel global menyesuaikan deskripsi modul
    static int size;
    static String[] names;
    static int[][] graph;
    static List<Integer> dfsOrder = new ArrayList<>();

    // Method bawaan (sudah lengkap di template)
    public static void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight; // Undirected graph (dua arah)
    }

    // Method bawaan untuk nge-print urutan gedung
    public static void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // ==========================================
    // 1. BFS (Isi 3 bagian kosong)
    // ==========================================
    public static List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Blank 1: Masukkan node awal ke queue dan tandai visited
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            // Blank 2: Ambil node paling depan dari queue dan masukkan ke hasil urutan
            int current = queue.poll();
            order.add(current);

            // Cek tetangga dari index terkecil (0) ke terbesar
            for (int i = 0; i < size; i++) {
                // Blank 3: Jika ada jalan (> 0) dan belum dikunjungi
                if (graph[current][i] > 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    // ==========================================
    // 2. DFS (Isi 2 bagian kosong)
    // ==========================================
    public static void dfs(int current, Set<Integer> visited) {
        // Blank 1: Tandai node saat ini sudah dikunjungi dan masukkan ke list dfsOrder
        visited.add(current);
        dfsOrder.add(current);

        // Cek tetangga dari index terkecil ke terbesar
        for (int i = 0; i < size; i++) {
            // Blank 2: Jika ada jalan dan belum dikunjungi, panggil rekursif
            if (graph[current][i] > 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    // ==========================================
    // 3. DIJKSTRA (Isi 3 bagian kosong)
    // ==========================================
    public static void dijkstraAlg(int start, int destination) {
        int[] dist = new int[size];
        int[] prev = new int[size];
        boolean[] visited = new boolean[size];

        // Inisialisasi jarak awal tak hingga (pakai Integer.MAX_VALUE)
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);

        // Blank 1: Jarak ke titik awal adalah 0
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            // Blank 2: Cari node yang jaraknya paling kecil dan belum dikunjungi
            int u = findTheNextNode(dist, visited);
            if (u == -1) break;
            
            visited[u] = true;

            // Update jarak ke tetangga-tetangganya
            for (int v = 0; v < size; v++) {
                // Blank 3: Cek apakah belum dikunjungi, ada jalan, jarak u valid, dan jarak baru lebih pendek
                if (!visited[v] && graph[u][v] > 0 && dist[u] != Integer.MAX_VALUE) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                    }
                }
            }
        }

        // Print hasil sesuai format Exact Behaviour di modul
        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + " : " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[destination] + " ===");
        route(prev, destination);
        System.out.println();
        System.out.println("Total time: " + dist[destination] + " min");
    }

    // ==========================================
    // 4. HELPER: findTheNextNode (Isi 1 bagian kosong)
    // ==========================================
    public static int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < size; i++) {
            // Blank 1: Cari yang belum visited dan jaraknya paling kecil
            // Pakai '<' (bukan '<=') supaya kalau jaraknya sama, index lebih kecil tetap terpilih duluan
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    // ==========================================
    // 5. HELPER: route (Isi 1 bagian kosong)
    // ==========================================
    public static void route(int[] prev, int current) {
        // Blank 1: Rekursi mundur lewat array prev sampai mentok di start (-1)
        if (prev[current] != -1) {
            route(prev, prev[current]);
            System.out.print(" -> ");
        }
        System.out.print(names[current]);
    }

    // ==========================================
    // 6. MAIN METHOD (Tulis sendiri)
    // ==========================================
    public static void main(String[] args) {
        try {
            // Baca file campus.txt (pastikan letak file sesuai direktori project)
            Scanner sc = new Scanner(Main.class.getResourceAsStream("campus.txt"));

            // 1. Baca jumlah gedung (N)
            size = sc.nextInt();
            names = new String[size];
            graph = new int[size][size];

            // 2. Baca nama-nama gedung
            for (int i = 0; i < size; i++) {
                names[i] = sc.next();
            }

            // 3. Baca jumlah jalan (E) dan masing-masing bobotnya
            int edges = sc.nextInt();
            for (int i = 0; i < edges; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int minutes = sc.nextInt();
                addEdge(v1, v2, minutes);
            }

            // 4. Baca titik mulai (START) dan tujuan (DESTINATION)
            int start = sc.nextInt();
            int destination = sc.nextInt();
            sc.close();

            // Eksekusi BFS
            System.out.println("=== BFS from " + names[start] + " ===");
            List<Integer> bfsResult = bfs(start);
            printPath(bfsResult);

            // Eksekusi DFS
            System.out.println("=== DFS from " + names[start] + " ===");
            Set<Integer> visitedDfs = new HashSet<>();
            dfsOrder.clear(); // Jaga-jaga biar list bersih
            dfs(start, visitedDfs);
            printPath(dfsOrder);

            // Eksekusi Dijkstra
            dijkstraAlg(start, destination);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}