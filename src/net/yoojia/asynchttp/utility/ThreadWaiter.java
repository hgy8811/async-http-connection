package net.yoojia.asynchttp.utility;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadWaiter {

	private static final ConcurrentHashMap<Long, Object> threadIDsMapping = new ConcurrentHashMap<Long, Object>();
	
	/**
	 * 当一个线程完成时，调用此方法通知ThreadWaiter
	 */
	public static void threadFinished(){
		long threadid = Thread.currentThread().getId();
		threadIDsMapping.remove(threadid);
	}
	
	/**
	 * 当一个线程开始后，调用此方法通知ThreadWaiter
	 */
	public static void threadStarted(){
		long threadid = Thread.currentThread().getId();
		threadIDsMapping.put(threadid, threadid);
	}
	
	/**
	 * 等待全部线程完成
	 */
	public static void waitingThreads() {
		for(;;){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(threadIDsMapping.isEmpty()) break;
		}
	}
	
	/**
	 * 等待全部线程完成，并退出程序。
	 */
	public static void waitingThreadsAndExit(){
		waitingThreads();
		System.exit(0);
	}
}
