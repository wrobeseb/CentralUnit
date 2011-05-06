package pwr.tin.tip.sw.pd.cu.unit.core.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Before;
import org.junit.Test;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

public class JobWorkerTest {

	private Job job;
	private final JobTaskResponseRepository jobTaskResponseRepository = new JobTaskResponseRepository();
	
	private final BlockingQueue<JobTask> jobTasksQueue = new LinkedBlockingQueue<JobTask>();
	
	@Before
	public void initJob() {
		job = new Job();
		job.setId(1);
		job.setFirst(new ArrayList<Integer>(Arrays.asList(1,2)));
		job.setLast(new ArrayList<Integer>(Arrays.asList(5)));
		
		JobTask jobTask1 = new JobTask(1,1,null,null); jobTask1.setNext(new ArrayList<Integer>(Arrays.asList(3)));
		JobTask jobTask2 = new JobTask(2,1,null,null); jobTask2.setNext(new ArrayList<Integer>(Arrays.asList(4)));
		JobTask jobTask3 = new JobTask(3,1,null,null); jobTask3.setNext(new ArrayList<Integer>(Arrays.asList(5)));
		jobTask3.setPrev(new ArrayList<Integer>(Arrays.asList(1)));
		JobTask jobTask4 = new JobTask(4,1,null,null); jobTask4.setNext(new ArrayList<Integer>(Arrays.asList(5)));
		jobTask4.setPrev(new ArrayList<Integer>(Arrays.asList(2)));
		JobTask jobTask5 = new JobTask(5,1,null,null);
		jobTask5.setPrev(new ArrayList<Integer>(Arrays.asList(3,4)));
		
		job.setTasks(new ArrayList<JobTask>(Arrays.asList(jobTask1, jobTask2, jobTask3, jobTask4, jobTask5)));
	}
	
	@Test
	public void parellelJobScenerio() throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Integer counter = 0;
				while (counter <= 5) {
					try {
						JobTask jobTask = jobTasksQueue.take();
						jobTaskResponseRepository.put(new JobTaskResponse(jobTask.getId(), jobTask.getSessionId()));
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		waitForResponses(startUpFirst());
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
					jobTasksQueue.add(jobTask);
					//defaultMessageSender.sendJobTask(jobTask);
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
					jobTasksQueue.add(jobTask);
					//defaultMessageSender.sendJobTask(jobTask);
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
