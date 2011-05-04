package pwr.tin.tip.sw.pd.cu.jms.model.utils;

import java.io.StringWriter;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.ti.jms.themes.Themes;

@Component(value="marshaller")
public class Marshaller {
	
	@Autowired(required=true)
	private Jaxb2Marshaller jaxb2marshaller;
	
	public String marshal(Object obj) throws MarshalException {
		try {
			DOMResult result = new DOMResult();
			jaxb2marshaller.marshal(obj, result);
		
			StringWriter writer = new StringWriter();
		
			TransformerFactory.newInstance().newTransformer().transform(new DOMSource(result.getNode()), new StreamResult(writer));
		
			return writer.toString();
		} 
		catch (TransformerConfigurationException e) {
			throw new MarshalException(e);
		}
		catch (TransformerException e) {
			throw new MarshalException(e);
		} 
		catch (TransformerFactoryConfigurationError e) {
			throw new MarshalException(e);
		}
	}
	
	public Object unmarshal(String body) throws UnMarshalException {
		try {
			return jaxb2marshaller.unmarshal(new DocumentSource(DocumentHelper.parseText(Themes.scenerioTemplate)));
		} 
		catch (XmlMappingException e) {
			throw new UnMarshalException(e);
		} 
		catch (DocumentException e) {
			throw new UnMarshalException(e);
		}
	}
}
