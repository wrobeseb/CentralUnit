package pwr.tin.tip.sw.pd.cu.jms.core;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.repo.JobTaskResponseRepository;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskResponse;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.Marshaller;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.UnMarshalException;

@Component(value="esbMessageListener")
public class EsbMessageListener {

	@Autowired(required=true)
	private JobTaskResponseRepository jobTaskReplaiesRepository;
	
	@Autowired(required=true)
	private Marshaller marshaller;
	
	public void receive(TextMessage textMessage) throws InterruptedException, UnMarshalException, JMSException {
		jobTaskReplaiesRepository.put((JobTaskResponse)marshaller.unmarshal(textMessage.getText()));
	}
}
