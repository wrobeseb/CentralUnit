package pwr.tin.tip.sw.pd.cu.ti.core.job.repo;

import org.junit.Test;

import pwr.tin.tip.sw.pd.cu.core.job.repo.BlockedJobsRepository;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;

public class BlockedJobsRepositoryTest {
	
	@Test
	public void synchronizationTest() {
		
		final BlockedJobsRepository jobsRepository = new BlockedJobsRepository();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				jobsRepository.put(new Job());
			}
		}).start();
		
		jobsRepository.pull();
	}
}
