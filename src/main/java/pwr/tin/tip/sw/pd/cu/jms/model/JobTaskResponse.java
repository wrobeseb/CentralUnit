package pwr.tin.tip.sw.pd.cu.jms.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pwr.tin.tip.sw.pd.cu.jms.model.enums.Status;

@XmlRootElement(name="algorithmResponse")
public class JobTaskResponse implements IXmlUtil {

	private Integer id;
	private Integer sessionId;
	private Status status;
	private String errorMsg;
	private String warningMsg;
	
	private String xml;
	
	public JobTaskResponse() {}
	
	/**
	 * Konstruktor na potrzeby testowania...
	 * 
	 * @param id identyfikator zadania
	 * @param sessionId identyfikator sesji
	 */
	public JobTaskResponse(Integer id, Integer sessionId) {
		this.id = id;
		this.sessionId = sessionId;
	}
	
	@XmlElement(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement(name="sessionId")	
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	
	@XmlElement(name="status")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@XmlElement(name="errorMsg", required=false)
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@XmlElement(name="warningMsg", required=false)
	public String getWarningMsg() {
		return warningMsg;
	}
	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

	@XmlTransient
	public String getXml() {
		return xml;
	}
	@Override
	public void setXml(String xml) {
		this.xml = xml;
	}

	@Override
	public String toString() {
		return this.xml;
	}
}
