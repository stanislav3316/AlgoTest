package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    @Override
    public void push(Item item) {
        Node add = new Node<>(item, head);
        head = add;
        size++;
    }

    public Item peek() {
        return head.item;
    }

    @Override
    public Item pop() {
        if (!isEmpty()) {
            Node<Item> get = head;
            head = get.next;
            size--;
            return get.item;
        }
        return null;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        Node<Item> h = head;

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

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
