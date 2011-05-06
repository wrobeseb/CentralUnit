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

@Component(value="defaultMessageSender")
public class DefaultMessageSender {

	private final static Logger log = LoggerFactory.getLogger(DefaultMessageSender.class);
	
	@Autowired(required=true)
	private JmsTemplate jmsTemplate;
	
	@Autowired(required=true)
	private IScenerioService scenerioService;
	
	@Value("${esb.in.queue}") 
	private String esbInQueue;
	@Value("${cu.replay.queue}")
	private String cuReplayQueue;
	
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
		send(esbInQueue, getMessageFromObject(jobTask));
		scenerioService.registerSendedJobTask(jobTask);
	}
	
	public void sendJobResponse(Job job) {
		JobResponse response = new JobResponse(job);
		send(cuReplayQueue, getMessageFromObject(response));
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
				return message;
			}
		};
	}
	
	private MessageCreator getMessageFromObject(Object obj) {
		if (!(obj instanceof JobTask)) {
			if (!(obj instanceof JobResponse)) {
				log.warn("Wiadomo¶æ nie zostanie wys³ana! Nie prawid³owy objekt, dopuszczalne JobTask, JobReplay");
			}
		}
		return getMessageFromBody(obj.toString());
	}
}
