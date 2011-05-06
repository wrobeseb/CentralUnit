package pwr.tin.tip.sw.pd.cu.core.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.core.DefaultMessageSender;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

public class JobWorker implements Runnable {

	private final static Logger log = LoggerFactory.getLogger(JobWorker.class);
	
	private final IScenerioService scenerioService;
	private final DefaultMessageSender defaultMessageSender;
	
	private final JobTaskResponseRepository jobTaskResponseRepository;
	
	private final Job job;
	
	public JobWorker(Job job, IScenerioService scenerioService, DefaultMessageSender defaultMessageSender, JobTaskResponseRepository jobTaskResponseRepository) {
		this.job = job;
		this.scenerioService = scenerioService;
		this.defaultMessageSender = defaultMessageSender;
		this.jobTaskResponseRepository = jobTaskResponseRepository;
	}
	
	@Override
	public void run() {
		try {
			waitForResponses(startUpFirst()); // Rekurencyjnie przechodzi przez wszystkie zadania...
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void waitForResponses(Integer waitCounter) throws InterruptedException {
		for (int i = 0; i < waitCounter; i++) {
			setProcessed(jobTaskResponseRepository.take(job.getId()));
			waitForResponses(checkForDependencies());
		}
	}
	
	private void setProcessed(JobTaskResponse response) {
		for (JobTask jobTask : job.getTasks()) {
			if (response.getId().equals(jobTask.getId())) {
				jobTask.setJobTaskResponse(response); break;
			}
		}
	}
	
	private Integer checkForDependencies() {
		Integer waitCounter = 0;
		for (JobTask jobTask : job.getTasks()) {
			if (!isProcessed(jobTask.getId()) && !jobTask.isSendedFlag()) {
				Boolean flag = false;
				for (Integer dependencyId : jobTask.getPrev()) {
					if (isProcessed(dependencyId)) {
						flag = true;
					}
					else {
						flag = false; break;
					}
				}
				if (flag) {
					defaultMessageSender.sendJobTask(jobTask);
					jobTask.setSendedFlag(true);
					waitCounter++;
				}
			}
		}
		return waitCounter;
	}

	private Integer startUpFirst() {
		Integer waitCounter = 0;
		for (Integer id : job.getFirst()) {
			for (JobTask jobTask : job.getTasks()) {
				if (id.equals(jobTask.getId())) {
					defaultMessageSender.sendJobTask(jobTask);
					jobTask.setSendedFlag(true);
					waitCounter++;
				}
			}
		}
		return waitCounter;
	}
	
	private Boolean isProcessed(Integer taskId) {
		for (JobTask jobTask : job.getTasks()) {
			if (taskId.equals(jobTask.getId())) {
				if (jobTask.getJobTaskResponse() != null) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return null;
	}
}
