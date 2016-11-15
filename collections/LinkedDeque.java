package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    @Override
    public void pushFront(Item item) {
        size++;

        if (head == null) {
            tail = head = new Node<>(item, null, null);
            return;
        }

        Node<Item> element = new Node<>(item, head, null);
        head.prev = element;
        head = element;
    }

    @Override
    public void pushBack(Item item) {
        size++;

        if (tail == null) {
            tail = head = new Node<>(item, null, null);
            return;
        }

        Node<Item> element = new Node<>(item, null, tail);
        tail.next = element;
        tail = element;
    }

    @Override
    public Item popFront() {
        size--;
        Node<Item> res = head;
        head = head.next;
        return res.item;
    }

    @Override
    public Item popBack() {
        size--;
        Node<Item> res = tail;
        tail = tail.prev;
        return res.item;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> h = head;

            @Override
            public boolean hasNext() {
                return h != null;
            }

            @Override
            public Item next() {
                Node<Item> temp = h;
                h = h.next;
                return temp.item;
            }
        };
    }
}