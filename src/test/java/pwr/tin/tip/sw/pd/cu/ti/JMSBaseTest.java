package pwr.tin.tip.sw.pd.cu.ti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import pwr.tin.tip.sw.pd.cu.jms.core.DefaultMessageSender;

@ContextConfiguration
public abstract class JMSBaseTest extends BaseTest {

	@Autowired(required=true)
	private DefaultMessageSender defaultMessageSender;
	
	public void sendJobTask(String body) {
		defaultMessageSender.sendJobTask(body);
	}
	
	public void sendJobReplay(String body) {
		defaultMessageSender.sendJobReplay(body);
	}
}
