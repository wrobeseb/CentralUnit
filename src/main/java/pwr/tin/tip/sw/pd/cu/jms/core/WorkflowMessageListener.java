package pwr.tin.tip.sw.pd.cu.jms.core;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.JobProcessor;
import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.Marshaller;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.UnMarshalException;

@Component(value="workflowMessageListener")
public class WorkflowMessageListener {
	
	@Autowired(required=true)
	private IScenerioService scenerioService;
	
	@Autowired(required=true)
	private JobProcessor jobProcessor;
	
	@Autowired(required=true)
	private Marshaller marshaller;
	
	@Value("${central.unit.id}")
	private Integer cuId;
	
	public void receive(TextMessage textMessage) throws UnMarshalException, JMSException {
		Job job = (Job) marshaller.unmarshal(textMessage.getText());
		job.setCuId(cuId);
		scenerioService.registerJobArrival(job);
		jobProcessor.launch(job);
	}
}
