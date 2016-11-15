package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {
    private IncreasingIterator first;
    private IncreasingIterator second;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean hasNext() {
        return first.hasNext() || second.hasNext();
    }

    @Override
    public Integer next() {
        if (first.hasNext() && second.hasNext()) {
            return (first.curr > second.curr)? first.next() : second.next();
        }

        if (first.hasNext()) {
            return first.next();
        } else {
            return second.next();
        }
    }
}