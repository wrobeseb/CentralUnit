package pwr.tin.tip.sw.pd.cu.db.service;

import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobResponse;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

public interface IScenerioService {

	public void registerJobArrival(Job job);
	
	public void registerStartedJob(Job job);
	
	public void registerEndedJob(Job job);
	
	public void registerEndedJobWithErrors(Job job);
	
	public void registarSendedJobReplay(JobResponse jobReplay);
	
	public void registerSendedJobTask(JobTask jobTask);
	
	public void registerJobTaskReplayArrival(JobTaskResponse jobTaskReplay);
}
