package pwr.tin.tip.sw.pd.cu.jms.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pwr.tin.tip.sw.pd.cu.jms.model.enums.Status;

@XmlRootElement(name="scenerioReplay")
@XmlType(propOrder = {"id", "name", "description", "status", "errorMsg", "warningMsg"})
public class JobResponse {

	private Integer id;
	private String name;
	private String description;
	private Status status;
	private String errorMsg;
	private String warningMsg;
	
	@XmlElement(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
