package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    @Override
    public void enqueue(Item item) {
        size++;

        if (tail == null) {
            head = tail = new Node<>(item);
            return;
        }

        Node<Item> element = new Node<Item>(item);
        tail.next = element;
        tail = element;
    }

    @Override
    public Item dequeue() {
        size--;
        Node<Item> res = head;
        head = head.next;
        return res.item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {
        private Node<Item> h = head;

        @Override
        public boolean hasNext() {
            return h != null;
        }

        @Override
        public Item next() {
            Item res = h.item;
            h = h.next;
            return res;
        }
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}