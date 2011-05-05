package pwr.tin.tip.sw.pd.cu.core.job.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskReplay;

@Component(value="jobTaskReplaiesRepository")
public class JobTaskReplaiesRepository {
	private final Map<Integer, BlockingQueue<JobTaskReplay>> jobTaskReplaiesPerSession = Collections.synchronizedMap(new HashMap<Integer, BlockingQueue<JobTaskReplay>>());
	
	public void put(JobTaskReplay jobTaskReplay) throws InterruptedException {
		LinkedBlockingQueue<JobTaskReplay> jobTaskReplays = null;
		synchronized (jobTaskReplaiesPerSession) {
			if (!jobTaskReplaiesPerSession.containsKey(jobTaskReplay.getSessionId())) {
				jobTaskReplaiesPerSession.put(jobTaskReplay.getSessionId(), new LinkedBlockingQueue<JobTaskReplay>());
			}
			jobTaskReplays = (LinkedBlockingQueue<JobTaskReplay>)jobTaskReplaiesPerSession.get(jobTaskReplay.getSessionId());
			jobTaskReplays.put(jobTaskReplay);
		}
	}
	
	public JobTaskReplay take(JobTask jobTask) throws InterruptedException {
		LinkedBlockingQueue<JobTaskReplay> jobTaskReplays = null;
		synchronized (jobTaskReplaiesPerSession) {
			if (!jobTaskReplaiesPerSession.containsKey(jobTask.getSessionId())) {
				jobTaskReplaiesPerSession.put(jobTask.getSessionId(), new LinkedBlockingQueue<JobTaskReplay>());
			}
			jobTaskReplays = (LinkedBlockingQueue<JobTaskReplay>)jobTaskReplaiesPerSession.get(jobTask.getSessionId());
		}
		if (jobTaskReplays != null) {
			return jobTaskReplays.take();
		}
		return null;
	}
	
	
}
