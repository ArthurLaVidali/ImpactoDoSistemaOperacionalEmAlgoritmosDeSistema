import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite quantos números deseja ordenar: ");
        int n = scanner.nextInt();
        int minValor = 1;
        int maxValor = 9999999;

        // Registra o tempo de início
        long start_time = System.currentTimeMillis();

        LinkedList<Integer> list = gerarListaAleatoria(n, minValor, maxValor);
        System.out.print("Lista aleatória: ");
        imprimirLista(list);

        quickSort(list);

        System.out.print("Lista ordenada: ");
        imprimirLista(list);

        // Registra o tempo de término
        long end_time = System.currentTimeMillis();

        // Calcula a diferença de tempo em milissegundos
        long elapsed_time = end_time - start_time;

        // Converte para o formato HH:MM:SS:mm
        long elapsed_seconds = elapsed_time / 1000;
        long hours = elapsed_seconds / 3600;
        long minutes = (elapsed_seconds % 3600) / 60;
        long seconds = elapsed_seconds % 60;
        long milliseconds = elapsed_time % 1000;

        System.out.printf("Tempo decorrido: %02d:%02d:%02d:%03d%n", hours, minutes, seconds, milliseconds);
    }

    public static LinkedList<Integer> gerarListaAleatoria(int n, int minValor, int maxValor) {
        LinkedList<Integer> list = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            int randomNumber = rand.nextInt((maxValor - minValor) + 1) + minValor;
            list.add(randomNumber);
        }
        return list;
    }

    public static void quickSort(LinkedList<Integer> list) {
        if (list.size() <= 1) {
            return;
        }
        int pivot = list.removeLast();
        LinkedList<Integer> lesser = new LinkedList<>();
        LinkedList<Integer> greater = new LinkedList<>();
        for (int number : list) {
            if (number <= pivot) {
                lesser.add(number);
            } else {
                greater.add(number);
            }
        }
        quickSort(lesser);
        quickSort(greater);
        list.clear();
        list.addAll(lesser);
        list.add(pivot);
        list.addAll(greater);
    }

    public static void imprimirLista(LinkedList<Integer> list) {
        for (int number : list) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
