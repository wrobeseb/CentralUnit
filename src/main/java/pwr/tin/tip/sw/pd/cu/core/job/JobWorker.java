package pwr.tin.tip.sw.pd.cu.core.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskReplaiesRepository;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.core.DefaultMessageSender;

public class JobWorker implements Runnable {

	private final static Logger log = LoggerFactory.getLogger(JobWorker.class);
	
	private final IScenerioService scenerioService;
	private final DefaultMessageSender defaultMessageSender;
	
	private final JobTaskReplaiesRepository jobTaskReplaiesRepository;
	
	public JobWorker(IScenerioService scenerioService, DefaultMessageSender defaultMessageSender, JobTaskReplaiesRepository jobTaskReplaiesRepository) {
		this.scenerioService = scenerioService;
		this.defaultMessageSender = defaultMessageSender;
		this.jobTaskReplaiesRepository = jobTaskReplaiesRepository;
	}
	
	@Override
	public void run() {
		/* TODO */
		stop(10000);
	}
	
	private Object obj = new Object();
	
	public void stop(long delay) {
		synchronized (obj) { try { obj.wait(delay); } catch (InterruptedException e) {} }
	}
}
