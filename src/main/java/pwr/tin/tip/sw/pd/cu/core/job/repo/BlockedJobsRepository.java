package pwr.tin.tip.sw.pd.cu.core.job.repo;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Component(value="blockedJobsRepository")
public class BlockedJobsRepository {
	
	private final Queue<Job> blockedJobs = new LinkedBlockingQueue<Job>();
	
	public Job pull() {
		synchronized (blockedJobs) {
			return blockedJobs.poll();
		}
	}
	
	public void put(Job job) {
		synchronized (blockedJobs) {
			blockedJobs.add(job);
		}
	}
	
	public Integer capacity() {
		synchronized (blockedJobs) {
			return blockedJobs.size();
		}
	}
}
