package pwr.tin.tip.sw.pd.cu.jms.core.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component(value="jmsConnectionManager")
public class JMSConnectionManager {

	private final static Logger log = LoggerFactory.getLogger(JMSConnectionManager.class);

	@Autowired(required=true)
	private DefaultMessageListenerContainer workflowMessageContainer;

	public void stopConsumingMessagesFromWorkflow() {
		workflowMessageContainer.stop(); log.debug("Zatrzymanie pobierania komunikatów z kolejki... {}", new Object[] {workflowMessageContainer.getDestinationName()});
	}
	
	public void startConsumingMessagesFromWorkflow() {
		if (!workflowMessageContainer.isRunning()) {
			workflowMessageContainer.start(); log.debug("Wznowienie pobierania komunikatów z kolejki... {}", new Object[] {workflowMessageContainer.getDestinationName()});
		}
	}
}
