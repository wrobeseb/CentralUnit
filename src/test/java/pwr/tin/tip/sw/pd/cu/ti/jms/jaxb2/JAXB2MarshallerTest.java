package pwr.tin.tip.sw.pd.cu.ti.jms.jaxb2;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.DocumentSource;
import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.ContextConfiguration;

import pwr.tin.tip.sw.pd.cu.jms.model.Job;
import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;
import pwr.tin.tip.sw.pd.cu.ti.BaseTest;
import pwr.tin.tip.sw.pd.cu.ti.jms.themes.Themes;

@ContextConfiguration
public class JAXB2MarshallerTest extends BaseTest {

	@Autowired(required=true)
	private Jaxb2Marshaller marshaller;
	
	@Test
	public void marshallTest() throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {
		Job job = new Job();
		job.setSessionId(1);
		job.setName("Test");
		job.setDescription("Test Desc");
		
		List<Integer> start = new ArrayList<Integer>();
		start.add(1);
		start.add(2);
		
		job.setFirst(start);
		
		List<Integer> end = new ArrayList<Integer>();
		end.add(1);
		end.add(2);
		
		job.setLast(end);
		
		List<JobTask> tasks = new ArrayList<JobTask>();
		tasks.add(new JobTask(1, 1, "source/file/path", "result/file/path"));
		tasks.add(new JobTask(2, 1, "source/file/path", "result/file/path"));
		tasks.add(new JobTask(3, 1, "source/file/path", "result/file/path"));
		
		job.setTasks(tasks);
		
		DOMResult result = new DOMResult();
		marshaller.marshal(job, result);
		
		StringWriter writer = new StringWriter();
		TransformerFactory.newInstance().newTransformer().transform(new DOMSource(result.getNode()), new StreamResult(writer));

		String textResult = writer.toString();
		
		assertNotNull(textResult);
	}
	
	@Test
	public void unmarshallTest() throws XmlMappingException, DocumentException {
		Object obj = marshaller.unmarshal(new DocumentSource(DocumentHelper.parseText(Themes.scenerioTemplate)));
		assertNotNull(obj);
	}
}
