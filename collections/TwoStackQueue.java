package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {
    private IStack<Item> stack1;
    private IStack<Item> stack2;
    private boolean needSwitch = true;

    public TwoStackQueue() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    @Override
    public void enqueue(Item item) {
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        if (needSwitch) {
            int len = stack1.size() - 1;
            for (int i = 1; i <= len; i++) {
                stack2.push(stack1.pop());
            }

            needSwitch = false;
            return stack1.pop();
        }

        if (!stack2.isEmpty()) {
            return stack2.pop();
        } else {
            needSwitch = true;
            return dequeue();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }


    // ДА, МНЕ ОЧЕНЬ СТЫДНО ЗА ИТЕРАТОР (НО ЭТО РАБОТАЕТ)
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Iterator<Item> it1 = stack1.iterator();
            private Iterator<Item> it2 = stack2.iterator();
            private IStack<Item> stack1Local = new LinkedStack<>();
            private IStack<Item> stack2Local = new LinkedStack<>();
            private IStack<Item> temp = new LinkedStack<>();

            private boolean needSwitchLocal = needSwitch;
            private boolean init = false;

            @Override
            public boolean hasNext() {
                if (!init) {
                    init();
                    init = true;
                }

                return stack1Local.size() + stack2Local.size() > 0;
            }

            @Override
            public Item next() {
                return dequeueLocal();
            }

            private void init() {
                while(it1.hasNext()) {
                    temp.push(it1.next());
                }

                Iterator<Item> tempIt = temp.iterator();
                while (tempIt.hasNext()) {
                    stack1Local.push(tempIt.next());
                }

                temp = new LinkedStack<>();
                while(it2.hasNext()) {
                    temp.push(it2.next());
                }

                tempIt = temp.iterator();
                while (tempIt.hasNext()) {
                    stack2Local.push(tempIt.next());
                }
            }

            public Item dequeueLocal() {
                if (needSwitchLocal) {
                    int len = stack1Local.size() - 1;
                    for (int i = 1; i <= len; i++) {
                        stack2Local.push(stack1Local.pop());
                    }

                    needSwitchLocal = false;
                    return stack1Local.pop();
                }

                if (!stack2Local.isEmpty()) {
                    return stack2Local.pop();
                } else {
                    needSwitchLocal = true;
                    return dequeueLocal();
                }
            }
        };
    }
}