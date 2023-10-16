import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TimSort {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Digite quantos valores deseja ordenar: ");
        int[] data = new int[scan.nextInt()];
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt();
        }

        // Inicia o timer
        long start = System.currentTimeMillis();

        // Ordena a lista usando o algoritmo TimSort
        timSort(data);

        // Imprime o tempo de execução
        long end = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (end - start) + " ms");
    }

    public static void timSort(int[] data) {
        // Divide a lista em pequenos blocos
        int minRun = 32;
        for (int i = 0; i < data.length; i += minRun) {
            int lo = i;
            int hi = Math.min(i + minRun - 1, data.length - 1);
            insertionSort(data, lo, hi);
        }

        // Junta os blocos ordenados
        for (int i = minRun; i < data.length; i *= 2) {
            for (int lo = 0; lo < data.length - i; lo += 2 * i) {
                int mid = lo + i;
                int hi = Math.min(lo + 2 * i - 1, data.length - 1);

                // Merge os dois blocos
                merge(data, lo, mid, hi);
            }
        }
    }

    private static void merge(int[] data, int lo, int mid, int hi) {
        // Cria dois arrays para armazenar os dois blocos
        int[] left = Arrays.copyOfRange(data, lo, mid + 1);
        int[] right = Arrays.copyOfRange(data, mid + 1, hi + 1);

        // Itera sobre os dois blocos, comparando os elementos
        int i = 0;
        int j = 0;
        int k = lo;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                data[k++] = left[i++];
            } else {
                data[k++] = right[j++];
            }
        }

        // Copia os elementos restantes do bloco não vazio
        while (i < left.length) {
            data[k++] = left[i++];
        }

        while (j < right.length) {
            data[k++] = right[j++];
        }
    }

    private static void insertionSort(int[] data, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = data[i];
            int j = i - 1;

            while (j >= lo && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }

            data[j + 1] = key;
        }
    }
}
