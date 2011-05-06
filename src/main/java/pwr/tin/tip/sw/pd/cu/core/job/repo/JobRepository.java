package pwr.tin.tip.sw.pd.cu.core.job.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Component(value="jobRepository")
public class JobRepository {

	private final Map<Integer, Job> jobs = Collections.synchronizedMap(new HashMap<Integer, Job>());
	
	public void put(Job job) {
		synchronized (jobs) {
			jobs.put(job.getId(), job);
		}
	}
	
	public void delete(Job job) {
		synchronized (jobs) {
			jobs.remove(job.getId());
		}
	}
	
	public Boolean hasJob(Integer id) {
		synchronized (jobs) {
			return jobs.containsKey(id);
		}
	}
}
