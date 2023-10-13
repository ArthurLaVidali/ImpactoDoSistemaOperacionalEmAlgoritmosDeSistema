public class DoublyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void add(E element) {
        Node<E> node = new Node<>(element);

        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.previous = tail;
            tail = node;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.value;
    }
    public void set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        node.value = element;
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.previous = null;
            }
        } else if (index == size() - 1) {
            tail = tail.previous;
            tail.next = null;
        } else {
            Node<E> node = head;
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }

            node.next.previous = node.previous;
            node.previous.next = node.next;
        }
    }

    public int size() {
        int size = 0;
        Node<E> node = head;
        while (node != null) {
            size++;
            node = node.next;
        }

        return size;
    }

    private static class Node<E> {

        private E value;
        private Node<E> previous;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }
}
