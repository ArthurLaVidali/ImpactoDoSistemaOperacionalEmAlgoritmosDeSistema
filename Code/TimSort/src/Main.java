import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite quantos números deseja ordenar: ");
        int tamanho = scan.nextInt();
        DoublyLinkedList<Integer> arr = gerarArray(tamanho);

        // Cria uma lista duplamente encadeada para armazenar os números do array
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();

        for (int i = 0; i < arr.size(); i++) {
            linkedList.add(arr.get(i));
        }

        // Ordena a lista usando o algoritmo timSort
        timSort(linkedList);

        // Imprime a lista ordenada
        System.out.println(linkedList);
    }

    public static void timSort(DoublyLinkedList<Integer> linkedList) {
        // Divide a lista em runs de tamanho 32
        int runLength = 32;
        for (int i = 0; i < linkedList.size(); i += runLength) {
            // Ordena cada run usando insertion sort
            insertionSort(linkedList, i, Math.min(linkedList.size(), i + runLength));
        }

        // Merge as runs até que a lista esteja ordenada
        while (runLength < linkedList.size()) {
            for (int i = 0; i < linkedList.size() - runLength; i += 2 * runLength) {
                // Merge as runs
                merge(linkedList, i, i + runLength, i + 2 * runLength);
            }

            runLength *= 2;
        }
    }

    public static void insertionSort(DoublyLinkedList<Integer> linkedList, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            int key = linkedList.get(i);
            int j = i - 1;

            while (j >= start && linkedList.get(j) > key) {
                linkedList.set(j + 1, linkedList.get(j));
                j--;
            }

            linkedList.set(j + 1, key);
        }
    }

    public static void merge(DoublyLinkedList<Integer> linkedList, int start1, int end1, int start2) {
        int end2 = 0;
        DoublyLinkedList<Integer> aux = new DoublyLinkedList<>();

        int i = start1;
        int j = start2;

        while (i < end1 && j < end2) {
            if (linkedList.get(i) <= linkedList.get(j)) {
                aux.add(linkedList.get(i));
                i++;
            } else {
                aux.add(linkedList.get(j));
                j++;
            }
        }

        // Copia o resto das runs para o array auxiliar
        while (i < end1) {
            aux.add(linkedList.get(i));
            i++;
        }

        while (j < end2) {
            aux.add(linkedList.get(j));
            j++;
        }

        // Copia o array auxiliar de volta para a lista original
        for (int p = 0; p < aux.size(); p++) {
            linkedList.add(aux.get(p));
        }
    }

    public static DoublyLinkedList<Integer> gerarArray(int tamanho) {
        // Cria um objeto Random
        Random random = new Random();

        // Cria uma lista duplamente encadeada para armazenar os números do array
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();

        // Gera números aleatórios para a lista
        for (int i = 0; i < tamanho; i++) {
            linkedList.add(random.nextInt());
        }

        return linkedList;
    }
}

