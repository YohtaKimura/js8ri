package ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

public class MyReentrantLock {
	public static void withLock(ReentrantLock myLock, Runnable r) {
		myLock.lock();
		try {
			r.run();
		} finally {
			myLock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final ReentrantLock myLock = new ReentrantLock();
		final String[] msgs = { "Hello", "Good morning", "Good afternoon", "Good evening", "See you" };

		// lockしない時の結果
		System.out.println("lockしない時の結果");
		for (String msg : msgs)
			new Thread(() -> {
				System.out.println(msg + ":first");
				System.out.println(msg + ":second");
				System.out.println(msg + ":third");
			}).start();
		Thread.sleep(1000);
		// lockした時の結果
		System.out.println("lockした時の結果");
		for (String msg : msgs)
			new Thread(() -> {
				withLock(myLock, () -> {
					System.out.println(msg + ":first");
					System.out.println(msg + ":second");
					System.out.println(msg + ":third");
				});
			}).start();
	}

}