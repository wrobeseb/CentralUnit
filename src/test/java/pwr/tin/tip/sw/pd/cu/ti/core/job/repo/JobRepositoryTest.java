package pwr.tin.tip.sw.pd.cu.ti.core.job.repo;

import org.junit.Test;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobRepository;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;

public class JobRepositoryTest {

	@Test
	public void synchronizationTest() {
		
		final JobRepository jobRepository = new JobRepository();
		final Job job = new Job();
		job.setSessionId(1);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				jobRepository.put(job);
			}
		}).start();
		
		jobRepository.delete(job);
	}
}
