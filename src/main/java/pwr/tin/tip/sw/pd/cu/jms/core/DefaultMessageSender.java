package pwr.tin.tip.sw.pd.cu.jms.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.db.service.IScenerioService;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobResponse;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.MarshalException;
import pwr.tin.tip.sw.pd.cu.jms.model.utils.Marshaller;

@Component(value="defaultMessageSender")
public class DefaultMessageSender {

	private final static Logger log = LoggerFactory.getLogger(DefaultMessageSender.class);
	
	@Autowired(required=true)
	private JmsTemplate jmsTemplate;
	
	@Autowired(required=true)
	private IScenerioService scenerioService;
	
	@Autowired(required=true)
	private Marshaller marshaller;
	
	@Value("${esb.in.queue}") 
	private String esbInQueue;
	@Value("${cu.replay.queue}")
	private String cuReplayQueue;
	
	@Value("${central.unit.id}")
	private Integer cuId;
	
	/**
	 * Metoda wykorzystywana w testach
	 * 
	 * @param body tresc komunikatu
	 */
	public void sendJobTask(String body) {
		send(esbInQueue, getMessageFromBody(body));
		log.debug("Wiadomo¶æ wys³ana... {}", new Object[]{ body });
	}
	
	/**
	 * Metoda wykorzystywana w testach
	 * 
	 * @param body tresc komunikatu
	 */
	public void sendJobReplay(String body) {
		send(cuReplayQueue, getMessageFromBody(body));
	}
	
	public void sendJobTask(JobTask jobTask) {
		log.debug("Wysy³anie zadania id: {} dla sesji: {}", new Object[]{jobTask.getId(), jobTask.getSessionId()});
		send(esbInQueue, getMessageFromBody(getMessageFromJobTask(jobTask)));
		scenerioService.registerSendedJobTask(jobTask);
	}
	
	public void sendJobResponse(Job job) {
		JobResponse response = new JobResponse(job);
		send(cuReplayQueue, getMessageFromBody(getMessageFromJobResponse(response)));
		scenerioService.registarSendedJobReplay(response);
	}
	
	private void send(String queue, MessageCreator messageCreator) {
		jmsTemplate.send(queue, messageCreator);
	}
	
	private MessageCreator getMessageFromBody(final String body) {
		return new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(body);
				message.setIntProperty("cuId", cuId);
				return message;
			}
		};
	}
	
	private String getMessageFromJobResponse(JobResponse jobResponse) {
		try {
			String message = marshaller.marshal(jobResponse);
			jobResponse.setXml(message);
			return message;
		} catch (MarshalException e) {
			return null;
		}
	}
	
	private String getMessageFromJobTask(JobTask jobTask) {
		try {
			String message = marshaller.marshal(jobTask);
			jobTask.setXml(message);
			return message;
		} catch (MarshalException e) {
			return null;
		}
	}
}
