package pwr.tin.tip.sw.pd.cu.jms.core;

import org.springframework.jms.core.JmsTemplate;

public class DefaultMessageSender {

	private JmsTemplate jmsTemplate;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	
}
