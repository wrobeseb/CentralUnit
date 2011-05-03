package pwr.tin.tip.sw.pd.cu.ti.jms;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import pwr.tin.tip.sw.pd.cu.ti.JMSBaseTest;
import pwr.tin.tip.sw.pd.cu.ti.jms.themes.Themes;

@ContextConfiguration
public class MessageEnqueueingTest extends JMSBaseTest {

	@Test
	public void sendJobTaskMessageTest(){
		sendJobTask(Themes.esbIn);
	}
}
