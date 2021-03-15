package tree;

import java.util.concurrent.Semaphore;

public class SemaphoreControl {

	public Semaphore semaphore = new Semaphore(5);

	public synchronized Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

}
