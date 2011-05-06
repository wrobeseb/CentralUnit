package pwr.tin.tip.sw.pd.cu.db.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pwr.tin.tip.sw.pd.cu.db.dao.IScenerioDao;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobResponse;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

@Service(value="scenerioService")
public class ScenerioServiceImpl implements IScenerioService {

	private final static Logger log = LoggerFactory.getLogger(ScenerioServiceImpl.class);
	
	@Autowired(required=true)
	private IScenerioDao scenerioDao;
	
	@Override
	public void registerJobArrival(Job job) {
		// TODO Zapisanie nadejscia wiadomosci zawierajacej scenariusz...
		log.debug("Zdarzenie nadejscia wiadomosci zawierajacej scenariusz id: {} utrwalone w bd.", new Object[] {job.getId()});
	}

	@Override
	public void registerStartedJob(Job job) {
		log.debug("Zapisanie zdarzenia wystartowania zadania procesujacego sceneariusz id: {}", new Object[] {job.getId()});
	}

	@Override
	public void registerEndedJob(Job job) {
		log.debug("Zapisanie zdarzenia zakonczenia zadania (bez bledow) procesowania sceneariusza o id: {}", new Object[] {job.getId()});
	}
	
	@Override
	public void registerEndedJobWithErrors(Job job) {
		log.debug("Zapisanie zdarzenia zakonczenia zadania (z bledami) procesowania sceneariusza o id: {}", new Object[] {job.getId()});
	}

	@Override
	public void registarSendedJobReplay(JobResponse jobReplay) {
		log.debug("Zapisanie zdarzenia wyslania komunikatu zwrotnego dla zadania o id: {}", new Object[] {jobReplay.getId()});
	}

	@Override
	public void registerSendedJobTask(JobTask jobTask) {
		log.debug("Zapisanie zdarzenia wyslania rzadania do EU z info o algorytmie id: {} ze sceneariusza o id: {}", new Object[] {jobTask.getId(), jobTask.getSessionId()});
	}

	@Override
	public void registerJobTaskReplayArrival(JobTaskResponse jobTaskReplay) {
		log.debug("Zapisanie zdarzenia nadejscia wiadomosci zwrotnej z EU dla algorytmu id: {} ze sceneariusza o id: {}", new Object[] {jobTaskReplay.getId(), jobTaskReplay.getSessionId()});
	}
}
