package pwr.tin.tip.sw.pd.cu.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pwr.tin.tip.sw.pd.cu.db.dao.IScenerioDao;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobReplay;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskReplay;

@Service(value="scenerioService")
public class ScenerioServiceImpl implements IScenerioService {

	@Autowired(required=true)
	private IScenerioDao scenerioDao;
	
	@Override
	public void registerJobArrival(Job job) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerStartedJob(Job job) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerEndedJob(Job job) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void registerEndedJobWithErrors(Job job) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registarSendedJobReplay(JobReplay jobReplay) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerSendedJobTask(JobTask jobTask) {
		// TODO Auto-generated method stub
	}

	@Override
	public void registerJobTaskReplayArrival(JobTaskReplay jobTaskReplay) {
		// TODO Auto-generated method stub
	}
}
