package pwr.tin.tip.sw.pd.cu.core.job;

public class JobTask implements Runnable {

	public JobTask(/* TODO */) {
		/* TODO */
	}
	
	@Override
	public void run() {
		/* TODO */
		stop(10000);
	}
	
	private Object obj = new Object();
	
	public void stop(long delay) {
		synchronized (obj) { try { obj.wait(delay); } catch (InterruptedException e) {} }
	}
}
