package pwr.tin.tip.sw.pd.cu.core.job.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;

@Component(value="jobTaskResponsRepository")
public class JobTaskResponseRepository {
	private final Map<Integer, BlockingQueue<JobTaskResponse>> jobTaskResponsesPerSession = Collections.synchronizedMap(new HashMap<Integer, BlockingQueue<JobTaskResponse>>());
	
	public void put(JobTaskResponse jobTaskReplay) throws InterruptedException {
		LinkedBlockingQueue<JobTaskResponse> jobTaskReplays = null;
		synchronized (jobTaskResponsesPerSession) {
			if (!jobTaskResponsesPerSession.containsKey(jobTaskReplay.getSessionId())) {
				jobTaskResponsesPerSession.put(jobTaskReplay.getSessionId(), new LinkedBlockingQueue<JobTaskResponse>());
			}
			jobTaskReplays = (LinkedBlockingQueue<JobTaskResponse>)jobTaskResponsesPerSession.get(jobTaskReplay.getSessionId());
			jobTaskReplays.put(jobTaskReplay);
		}
	}
	
	public JobTaskResponse take(Integer sessionId) throws InterruptedException {
		LinkedBlockingQueue<JobTaskResponse> jobTaskReplays = null;
		synchronized (jobTaskResponsesPerSession) {
			if (!jobTaskResponsesPerSession.containsKey(sessionId)) {
				jobTaskResponsesPerSession.put(sessionId, new LinkedBlockingQueue<JobTaskResponse>());
			}
			jobTaskReplays = (LinkedBlockingQueue<JobTaskResponse>)jobTaskResponsesPerSession.get(sessionId);
		}
		if (jobTaskReplays != null) {
			return jobTaskReplays.take();
		}
		return null;
	}
	
	
}
