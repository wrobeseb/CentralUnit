package pwr.tin.tip.sw.pd.cu.jms.core;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.Marshaller;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.UnMarshalException;

@Component(value="esbMessageListener")
public class EsbMessageListener {

	private final static Logger log = LoggerFactory.getLogger(EsbMessageListener.class);
	
	@Autowired(required=true)
	private IScenerioService scenerioService;
	
	@Autowired(required=true)
	private JobTaskResponseRepository jobTaskReplaiesRepository;
	
	@Autowired(required=true)
	private Marshaller marshaller;
	
	public void receive(TextMessage textMessage) throws InterruptedException, UnMarshalException, JMSException {
		JobTaskResponse response = (JobTaskResponse)marshaller.unmarshal(textMessage.getText());
		log.debug("Odpowiedz dla zadania id: {} sesja: {} odebrana.", new Object[]{response.getId(), response.getSessionId()});
		jobTaskReplaiesRepository.put(response);
		scenerioService.registerJobTaskReplayArrival(response);
	}
}
