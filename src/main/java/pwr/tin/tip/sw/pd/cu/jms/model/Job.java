package pwr.tin.tip.sw.pd.cu.jms.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="scenerio")
@XmlType(propOrder = {"sessionId", "name", "description", "first", "tasks", "last"})
public class Job implements IXmlUtil {

	private Integer id;
	
	private Integer cuId;
	private Integer sessionId;
	private String name;
	private String description;
	
	private List<Integer> first;
	
 	private List<Integer> last;

	private List<JobTask> tasks;
	
	private String xml;
	
	
	@XmlTransient
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlTransient
	public Integer getCuId() {
		return cuId;
	}
	public void setCuId(Integer cuId) {
		this.cuId = cuId;
	}
	
	@XmlElement(name="id")
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
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

	@XmlElementWrapper(name="end")
	@XmlElement(name="prev")
	public List<Integer> getLast() {
		return last;
	}
	public void setLast(List<Integer> last) {
		this.last = last;
	}

	@XmlElementWrapper(name="start")
	@XmlElement(name="next")
	public List<Integer> getFirst() {
		return first;
	}
	public void setFirst(List<Integer> first) {
		this.first = first;
	}

	@XmlElementWrapper(name="components")
	@XmlElement(name="algorithm") 
	public List<JobTask> getTasks() {
		return tasks;
	}
	public void setTasks(List<JobTask> tasks) {
		this.tasks = tasks;
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
