import java.util.Random;
import java.util.Scanner;

public class HeapSort {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Digite quantos valores deseja ordenar: ");

        int[] list = new int[scan.nextInt()];
        Random random = new Random();
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextInt();
        }
        long start = System.currentTimeMillis();

        // Ordena a lista com o algoritmo HeapSort
        heapSort(list);

        // Imprime o tempo de execução
        long end = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (end - start) + "ms");
    }

    private static void heapSort(int[] list) {
        // Constrói um heap com a lista
        for (int i = list.length / 2 - 1; i >= 0; i--) {
            heapify(list, i, list.length);
        }

        // Remove os elementos do heap, um a um, para ordená-los
        for (int i = list.length - 1; i >= 0; i--) {
            // Remove o elemento do topo do heap
            int temp = list[0];
            list[0] = list[i];
            list[i] = temp;

            // Reconstrói o heap sem o elemento removido
            heapify(list, 0, i);
        }
    }

    private static void heapify(int[] list, int i, int n) {
        // O filho esquerdo do nó i
        int left = 2 * i + 1;

        // O filho direito do nó i
        int right = 2 * i + 2;

        // O nó com o maior valor entre o nó i, seu filho esquerdo e seu filho direito
        int largest = i;

        // Se o filho esquerdo do nó i for maior que ele, atualiza o nó com o maior valor
        if (left < n && list[left] > list[largest]) {
            largest = left;
        }

        // Se o filho direito do nó i for maior que ele, atualiza o nó com o maior valor
        if (right < n && list[right] > list[largest]) {
            largest = right;
        }

        // Se o nó com o maior valor não for o nó i, troca os valores entre eles
        if (largest != i) {
            int temp = list[i];
            list[i] = list[largest];
            list[largest] = temp;

            // Reconstrói o heap com o nó com o maior valor no lugar correto
            heapify(list, largest, n);
        }
    }
}
