package pwr.tin.tip.sw.pd.cu.core.job;

import java.util.concurrent.RejectedExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.repo.BlockedJobsRepository;
import pwr.tin.tip.sw.pd.cu.core.job.repo.JobRepository;
import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.core.DefaultMessageSender;
import pwr.tin.tip.sw.pd.cu.jms.core.manager.JMSConnectionManager;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Component(value="jobProcessor")
public class JobProcessor {

	private final static Logger log = LoggerFactory.getLogger(JobProcessor.class);
	
	@Autowired(required=true)
	private JMSConnectionManager jmsConnectionManager;
	
	@Autowired(required=true)
	private JobRepository jobRepository;
	
	@Autowired(required=true)
	private BlockedJobsRepository blockedJobsRepository;
	
	@Autowired(required=true)
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired(required=true)
	private IScenerioService scenerioService;
	
	@Autowired(required=true)
	private DefaultMessageSender defaultMessageSender;
	
	@Autowired(required=true)
	private JobTaskResponseRepository jobTaskResponsRepository;
	
	public void launch(Job job) {
		log.debug("Próba rozpoczêcia zadania id: {}.", new Object[] { job.getId() });
		try {
			taskExecutor.execute(new JobWorker(job, scenerioService, defaultMessageSender, jobTaskResponsRepository));
			jobRepository.put(job);
			log.debug("Zadanie id: {} rozpoczête.", new Object[] { job.getId() });
		}
		catch(RejectedExecutionException reEx) {
			log.info("Kolejka przepelniona... zadanie id: {} zostaje umieszczone w kolejce zablokowanych.", new Object[] { job.getId() });
			blockedJobsRepository.put(job);
			jmsConnectionManager.stopConsumingMessagesFromWorkflow();
		}
	}
}
