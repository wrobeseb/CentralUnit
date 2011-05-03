package pwr.tin.tip.sw.pd.cu.jms.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="algorithm")
@XmlType(propOrder = {"id", "sessionId", "sourceFilePath", "resultFilePath", "next"})
public class JobTask {

	private Integer id;
	private Integer sessionId;
	private String sourceFilePath;
	private String resultFilePath;
	
	private List<Integer> next;
	
	public JobTask() {}
	
	public JobTask(Integer id, Integer sessionId, String sourceFilePath,
			String resultFilePath) {
		this.id = id;
		this.sessionId = sessionId;
		this.sourceFilePath = sourceFilePath;
		this.resultFilePath = resultFilePath;
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

	@XmlElement(name="sourceFilePath", required=false)
	public String getSourceFilePath() {
		return sourceFilePath;
	}
	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}
	
	@XmlElement(name="resultFilePath", required=false)
	public String getResultFilePath() {
		return resultFilePath;
	}
	public void setResultFilePath(String resultFilePath) {
		this.resultFilePath = resultFilePath;
	}

	@XmlElement(name="next", required=false)
	public List<Integer> getNext() {
		return next;
	}
	public void setNext(List<Integer> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
