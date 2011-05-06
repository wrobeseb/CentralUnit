package pwr.tin.tip.sw.pd.cu.ti.core.job.repo;

import org.junit.Test;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskNotFoundException;
import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

public class JobTasksRepositoryTest {

	@Test
	public void synchronizationTest() throws JobTaskNotFoundException, InterruptedException {
		final JobTaskResponseRepository jobTasksRepository = new JobTaskResponseRepository();
		final JobTask jobTask1 = new JobTask(1, 1, null, null);
		final JobTask jobTask2 = new JobTask(2, 1, null, null);
		final JobTaskResponse jobTaskReplay1 = new JobTaskResponse(1, 1);
		final JobTaskResponse jobTaskReplay2 = new JobTaskResponse(2, 1);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					jobTasksRepository.take(jobTask1.getSessionId());
					jobTasksRepository.take(jobTask2.getSessionId());
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					jobTasksRepository.put(jobTaskReplay1);
				} 
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		jobTasksRepository.put(jobTaskReplay2);
	}
}
