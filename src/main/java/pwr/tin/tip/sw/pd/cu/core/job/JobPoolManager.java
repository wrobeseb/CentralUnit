package pwr.tin.tip.sw.pd.cu.core.job;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.repo.BlockedJobsRepository;
import pwr.tin.tip.sw.pd.cu.jms.core.manager.JMSConnectionManager;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Component(value="jobPoolManager")
public class JobPoolManager {
	
	@Autowired(required=true)
	private JMSConnectionManager jmsConnectionManager;
	
	@Autowired(required=true)
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired(required=true)
	private BlockedJobsRepository blockedJobsRepository;
	
	@Autowired(required=true)
	private JobProcessor jobProcessor;

	public void checkIfQueueIsFull() {
		ThreadPoolExecutor executor = taskExecutor.getThreadPoolExecutor();
		if (executor.getQueue().size() == 0) {
			if (blockedJobsRepository.capacity() != 0) {
				Job job;
				while ((job = blockedJobsRepository.pull()) != null) {
					if (executor.getQueue().size() == 0) {
						jobProcessor.launch(job);
					}
					else {
						blockedJobsRepository.put(job);
					}
				}
				if (blockedJobsRepository.capacity() == 0) {
					jmsConnectionManager.startConsumingMessagesFromWorkflow();
				}
			}
		}
	}
}
