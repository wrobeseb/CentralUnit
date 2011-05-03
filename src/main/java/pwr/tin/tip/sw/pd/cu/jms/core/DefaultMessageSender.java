package pwr.tin.tip.sw.pd.cu.jms.core;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import pwr.tin.tip.sw.pd.cu.jms.model.JobReplay;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;

public class DefaultMessageSender {

	private final static Logger log = LoggerFactory.getLogger(DefaultMessageSender.class);
	
	private JmsTemplate jmsTemplate;
	
	private String esbInQueue;
	private String cuReplayQueue;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public void setEsbInQueue(String esbInQueue) {
		this.esbInQueue = esbInQueue;
	}
	public void setCuReplayQueue(String cuReplayQueue) {
		this.cuReplayQueue = cuReplayQueue;
	}
	
	public void sendJobTask(String body) {
		send(esbInQueue, getMessageFromBody(body));
		log.debug("Wiadomoœæ wys³ana... {}", new Object[]{ body });
	}
	
	public void sendJobReplay(String body) {
		send(cuReplayQueue, getMessageFromBody(body));
	}
	
	public void sendJobTask(JobTask jobTask) {
		send(esbInQueue, getMessageFromObject(jobTask));
	}
	
	public void sendJobReplay(JobReplay jobReplay) {
		send(cuReplayQueue, getMessageFromObject(jobReplay));
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
			if (!(obj instanceof JobReplay)) {
				log.warn("Wiadomoœæ nie zostanie wys³ana! Nie prawid³owy objekt, dopuszczalne JobTask, JobReplay");
			}
		}
		return getMessageFromBody(obj.toString());
	}
}
