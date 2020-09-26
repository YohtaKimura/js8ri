package ch08.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public final class CheckedQueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        Queue checkedQueue = Collections.checkedQueue(queue, String.class);

        checkedQueue.add(10);
    }
}
