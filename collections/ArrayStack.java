package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<Item> implements IStack<Item> {
    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        this.elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void push(Item item) {
        grow();
        elementData[size++] = item;
    }

    @Override
    public Item pop() {
        if (!isEmpty()) {
            shrink();
            return elementData[--size];
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

    private void grow() {
        if (size() == elementData.length) {
            int newSize = size * 3 / 2;
            changeCapacity(newSize);
        }
    }

    private void shrink() {
        if (size() == elementData.length / 4) {
            int newSize = elementData.length / 2;
            if (newSize < DEFAULT_CAPACITY) {
                newSize = DEFAULT_CAPACITY;
            }
            changeCapacity(newSize);
        }
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<Item> {
        private int currentPosition = size;

        @Override
        public boolean hasNext() {
            return currentPosition != 0;
        }

        @Override
        public Item next() {
            return elementData[--currentPosition];
        }
    }

}