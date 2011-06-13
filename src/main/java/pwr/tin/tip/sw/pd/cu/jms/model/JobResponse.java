package pwr.tin.tip.sw.pd.cu.jms.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import pwr.tin.tip.sw.pd.cu.jms.model.enums.Status;

@XmlRootElement(name="scenerioReplay")
@XmlType(propOrder = {"sessionId", "name", "description", "status", "errorMsg", "warningMsg"})
public class JobResponse implements IXmlUtil {

	private Integer id;
	
	private Integer sessionId;
	private String name;
	private String description;
	private Status status;
	private String errorMsg;
	private String warningMsg;
	
	private String xml;
	
	public JobResponse() {}
	
	public JobResponse(Job job) {
		this.id = job.getId();
		this.sessionId = job.getSessionId();
		this.name = job.getName();
		this.description = job.getDescription();
		for (JobTask jobTask : job.getTasks()) {
			if (jobTask.getJobTaskResponse() != null) {
				if (jobTask.getJobTaskResponse().getStatus().equals(Status.ERROR)) {
					this.status = Status.ERROR; this.errorMsg = "Wystapil blad!"; break;
				}
				else 
					if (jobTask.getJobTaskResponse().getStatus().equals(Status.WARNING)) {
						this.status = Status.WARNING; this.warningMsg = "Wystapilo ostrzezenie!"; break;
					}
					else {
						if (this.status == null) {
							this.status = Status.PROCESSED;
						}
					}
			}
			else {
				this.status = Status.SEVERITY; this.errorMsg = "Wystapil blad powodujacy nie zakonczenie procesowania scenariusza...!"; break;
			}
		}
	}
	
	
	@XmlTransient
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name="id")
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer id) {
		this.sessionId = id;
	}
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
