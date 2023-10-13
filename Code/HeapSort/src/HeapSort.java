import java.util.LinkedList;
import java.util.Random;

public class HeapSort {

    public static void main(String[] args) {
        int n = Integer.parseInt(System.console().readLine("Digite quantos números você deseja ordenar: "));

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Random().nextInt(1000));
        }

        System.out.println("Lista original: " + list);

        heapSort(list);

        System.out.println("Lista ordenada: " + list);
    }

    private static void heapSort(LinkedList<Integer> list) {
        // Converte a lista em um heap
        for (int i = list.size() / 2 - 1; i >= 0; i--) {
            heapify(list, i, i - 1);
        }

        // Remove os elementos do heap
        for (int i = list.size() - 1; i > 0; i--) {
            // Move o elemento máximo para a última posição
            int temp = list.removeLast();
            list.addFirst(temp);

            // Remove o elemento máximo do heap
            heapify(list, 0, i - 1);
        }

    }

    private static void heapify(LinkedList<Integer> list, int i, int i1) {
        // O maior elemento é o atual
        int largest = i;

        // Verifica se o filho esquerdo é maior que o atual
        int left = 2 * i + 1;
        if (left < list.size() && list.get(left) > list.get(largest)) {
            largest = left;
        }

        // Verifica se o filho direito é maior que o atual
        int right = 2 * i + 2;
        if (right < list.size() && list.get(right) > list.get(largest)) {
            largest = right;
        }

        // Se o maior elemento não for o atual, troca-os
        if (largest != i) {
            int temp = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, temp);
            heapify(list, largest, i - 1);
        }
    }
}
